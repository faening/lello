package io.github.faening.lello.feature.journal.sleep.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.component.LelloOptionPillSelector
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.domain.mock.SleepSensationOptionMock
import io.github.faening.lello.core.model.option.SleepSensationOption
import io.github.faening.lello.feature.journal.sleep.SleepJournalViewModel
import io.github.faening.lello.core.designsystem.R as designsystemR

@Composable
internal fun SleepJournalMoodScreen(
    viewModel: SleepJournalViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    onOpenSleepSensationOptionSettings: () -> Unit
) {
    val sleepSensationOptions by viewModel.sleepSensationOptions.collectAsState()
    val coinsAcquired by viewModel.coinsAcquired.collectAsState()

    LelloTheme(scheme = LelloColorScheme.DEFAULT) {
        SleepJournalMoodContainer(
            coinsAcquired = coinsAcquired,
            sleepSensationOptions = sleepSensationOptions,
            onSleepSensationOptionToggle = viewModel::toggleSleepSensationSelection,
            onOpenSleepSensationOptionSettings = onOpenSleepSensationOptionSettings,
            onSave = viewModel::saveSleepJournal,
            onBack = onBack,
            onNext = onNext,
            onFinish = onFinish
        )
    }
}

@Composable
private fun SleepJournalMoodContainer(
    coinsAcquired: Int,
    sleepSensationOptions: List<SleepSensationOption>,
    onSleepSensationOptionToggle: (String) -> Unit,
    onOpenSleepSensationOptionSettings: () -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit
) {
    val anySelected = sleepSensationOptions.any { it.selected }

    Scaffold(
        topBar = { SleepJournalMoodTopBar(onBack) },
        bottomBar = {
            SleepJournaBottomBar(
            enabled = anySelected,
            onSave = onSave,
            onNext = onNext,
            onFinish = onFinish
            )
        }
    ) { paddingValues ->
        SleepJournalMoodContent(
            coinsAcquired = coinsAcquired,
            sleepSensationOptions = sleepSensationOptions,
            onSleepSensationOptionToggle = onSleepSensationOptionToggle,
            onOpenSleepSensationOptionSettings = onOpenSleepSensationOptionSettings,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun SleepJournalMoodTopBar(
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Sono"),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
private fun SleepJournaBottomBar(
    enabled: Boolean,
    onSave: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.Medium),
        horizontalArrangement = Arrangement.spacedBy(Dimension.Medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LelloFilledButton(
            label = "Concluir",
            enabled = enabled,
            onClick = {
                onSave()
                onFinish()
            },
            modifier = Modifier.weight(1f)
        )

        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            enabled = enabled,
            onClick = onNext
        )
    }
}

@Composable
private fun SleepJournalMoodContent(
    coinsAcquired: Int,
    sleepSensationOptions: List<SleepSensationOption>,
    onSleepSensationOptionToggle: (String) -> Unit,
    onOpenSleepSensationOptionSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimension.Medium)
    ) {
        // Header
        Text(
            text = "Como você se sentiu ao acordar?",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(Dimension.Medium))

        Text(
            text = "Ganhe $coinsAcquired moeads ao concluir",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(Dimension.ExtraLarge))

        // Scrollable area
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            LelloOptionPillSelector(
                title = null,
                options = sleepSensationOptions,
                isSelected = { it.selected },
                onToggle = { option -> onSleepSensationOptionToggle(option.description) },
                onOpenSettings = onOpenSleepSensationOptionSettings,
                getLabel = { it.description }
            )
        }
    }
}

@Composable
@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
private fun SleepJournalMoodScreenPreview() {
    LelloTheme {
        SleepJournalMoodContainer(
            coinsAcquired = 100,
            sleepSensationOptions = SleepSensationOptionMock.list,
            onSleepSensationOptionToggle = { _ -> },
            onOpenSleepSensationOptionSettings = {},
            onSave = {},
            onBack = {},
            onNext = {},
            onFinish = {}
        )
    }
}