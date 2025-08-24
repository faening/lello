package io.github.faening.lello.feature.journal.sleep.screen

import android.content.res.Configuration
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.LelloOptionPillSelector
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.domain.mock.LocationOptionMock
import io.github.faening.lello.core.domain.mock.SleepActivityOptionMock
import io.github.faening.lello.core.domain.mock.SleepQualityOptionMock
import io.github.faening.lello.core.model.option.LocationOption
import io.github.faening.lello.core.model.option.SleepActivityOption
import io.github.faening.lello.core.model.option.SleepQualityOption
import io.github.faening.lello.core.model.option.SleeplessTimeOption
import io.github.faening.lello.core.model.option.SleeplessTimeOptions
import io.github.faening.lello.feature.journal.sleep.SleepJournalViewModel

@Composable
internal fun SleepJournalDetailsScreen(
    viewModel: SleepJournalViewModel,
    onBack: () -> Unit,
    onFinish: () -> Unit,
    onOpenSleepQualityOptionSettings: () -> Unit,
    onOpenSleepActivityOptionSettings: () -> Unit,
    onOpenLocationOptionSettings: () -> Unit,
) {
    val sleepQualityOptions by viewModel.sleepQualityOptions.collectAsState()
    val sleepActivityOptions by viewModel.sleepActivityOptions.collectAsState()
    val locationOptions by viewModel.locationOptions.collectAsState()
    val sleeplessTimeOption by viewModel.sleeplessTimeOption.collectAsState()
    val coinsAcquired by viewModel.coinsAcquired.collectAsState()

    LelloTheme(moodColor = MoodColor.DEFAULT) {
        SleepJournalDetailsContainer(
            coinsAcquired = coinsAcquired,
            sleepQualityOptions = sleepQualityOptions,
            onSleepQualityOptionToggle = viewModel::toggleSleepQualitySelection,
            onOpenSleepQualityOptionSettings = onOpenSleepQualityOptionSettings,
            sleepActivityOptions = sleepActivityOptions,
            onSleepActivityOptionToggle = viewModel::toggleSleepActivitySelection,
            onOpenSleepActivityOptionSettings = onOpenSleepActivityOptionSettings,
            locationOptions = locationOptions,
            onLocationOptionToggle = viewModel::toggleLocationSelection,
            onOpenLocationOptionSettings = onOpenLocationOptionSettings,
            sleeplessTimeOption = sleeplessTimeOption,
            onSleeplessTimeOptionToggle = viewModel::toggleSleeplessTimeOption,
            onSave = viewModel::saveSleepJournal,
            onBack = onBack,
            onFinish = onFinish
        )
    }
}

@Composable
private fun SleepJournalDetailsContainer(
    coinsAcquired: Int,
    sleepQualityOptions: List<SleepQualityOption>,
    onSleepQualityOptionToggle: (String) -> Unit,
    onOpenSleepQualityOptionSettings: () -> Unit,
    sleepActivityOptions: List<SleepActivityOption>,
    onSleepActivityOptionToggle: (String) -> Unit,
    onOpenSleepActivityOptionSettings: () -> Unit,
    locationOptions: List<LocationOption>,
    onLocationOptionToggle: (String) -> Unit,
    onOpenLocationOptionSettings: () -> Unit,
    sleeplessTimeOption: SleeplessTimeOption?,
    onSleeplessTimeOptionToggle: (SleeplessTimeOption) -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
    onFinish: () -> Unit
) {
    Scaffold(
        topBar = { SleepJournalDetailsTopBar(onBack) },
        bottomBar = { SleepJournalDetailsBottomBar(onSave, onFinish) }
    ) { paddingValues ->
        SleepJournalDetailsContent(
            coinsAcquired = coinsAcquired,
            sleepQualityOptions = sleepQualityOptions,
            onSleepQualityOptionToggle = onSleepQualityOptionToggle,
            onOpenSleepQualityOptionSettings = onOpenSleepQualityOptionSettings,
            sleepActivityOptions = sleepActivityOptions,
            onSleepActivityOptionToggle = onSleepActivityOptionToggle,
            onOpenSleepActivityOptionSettings = onOpenSleepActivityOptionSettings,
            locationOptions = locationOptions,
            onLocationOptionToggle = onLocationOptionToggle,
            onOpenLocationOptionSettings = onOpenLocationOptionSettings,
            sleeplessTimeOption = sleeplessTimeOption,
            onSleeplessTimeOptionToggle = onSleeplessTimeOptionToggle,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun SleepJournalDetailsTopBar(
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Sono"),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
private fun SleepJournalDetailsBottomBar(
    onSave: () -> Unit,
    onFinish: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.spacingRegular)
    ) {
        LelloFilledButton(
            label = "Concluir",
            onClick = {
                onSave()
                onFinish()
            }
        )
    }
}

@Composable
private fun SleepJournalDetailsContent(
    coinsAcquired: Int,
    sleepQualityOptions: List<SleepQualityOption>,
    onSleepQualityOptionToggle: (String) -> Unit,
    onOpenSleepQualityOptionSettings: () -> Unit,
    sleepActivityOptions: List<SleepActivityOption>,
    onSleepActivityOptionToggle: (String) -> Unit,
    onOpenSleepActivityOptionSettings: () -> Unit,
    locationOptions: List<LocationOption>,
    onLocationOptionToggle: (String) -> Unit,
    onOpenLocationOptionSettings: () -> Unit,
    sleeplessTimeOption: SleeplessTimeOption?,
    onSleeplessTimeOptionToggle: (SleeplessTimeOption) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimension.spacingRegular)
    ) {
        // Header
        Text(
            text = "Gostaria de adicionar mais detalhes sobre o seu sono?",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(Dimension.spacingRegular))

        Text(
            text = "Ganhe $coinsAcquired moeads ao concluir",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(Dimension.spacingExtraLarge))

        // Scrollable area
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            LelloOptionPillSelector(
                title = "Como você descreve seu sono?",
                options = sleepQualityOptions,
                isSelected = { it.selected },
                onToggle = { option -> onSleepQualityOptionToggle(option.description) },
                onOpenSettings = onOpenSleepQualityOptionSettings,
                getLabel = { it.description }
            )
            Spacer(modifier = Modifier.height(Dimension.spacingLarge))

            LelloOptionPillSelector(
                title = "O que você fez antes de dormir?",
                options = sleepActivityOptions,
                isSelected = { it.selected },
                onToggle = { option -> onSleepActivityOptionToggle(option.description) },
                onOpenSettings = onOpenSleepActivityOptionSettings,
                getLabel = { it.description }
            )
            Spacer(modifier = Modifier.height(Dimension.spacingLarge))

            LelloOptionPillSelector(
                title = "Onde você dormiu?",
                options = locationOptions,
                isSelected = { it.selected },
                onToggle = { option ->  onLocationOptionToggle(option.description) },
                onOpenSettings = onOpenLocationOptionSettings,
                getLabel = { it.description }
            )
            Spacer(modifier = Modifier.height(Dimension.spacingLarge))

            LelloOptionPillSelector(
                title = "Quanto tempo você ficou acordado?",
                options = SleeplessTimeOptions,
                isSelected = { it == sleeplessTimeOption },
                onToggle = { option -> onSleeplessTimeOptionToggle(option) },
                getLabel = { it.label }
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
private fun SleepJournalDetailsScreenPreview() {
    LelloTheme {
        SleepJournalDetailsContainer(
            coinsAcquired = 100,
            sleepQualityOptions = SleepQualityOptionMock.list,
            onSleepQualityOptionToggle = { _ -> },
            onOpenSleepQualityOptionSettings = {},
            sleepActivityOptions = SleepActivityOptionMock.list,
            onSleepActivityOptionToggle = { _ -> },
            onOpenSleepActivityOptionSettings = {},
            locationOptions = LocationOptionMock.list,
            onLocationOptionToggle = { _ -> },
            onOpenLocationOptionSettings = {},
            sleeplessTimeOption = SleeplessTimeOptions.first(),
            onSleeplessTimeOptionToggle = {},
            onSave = {},
            onBack = {},
            onFinish = {}
        )
    }
}