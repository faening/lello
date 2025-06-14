package io.github.faening.lello.feature.journal.sleep.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import io.github.faening.lello.core.designsystem.component.LelloTextField
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.domain.mock.LocationOptionMock
import io.github.faening.lello.core.domain.mock.SleepActivityOptionMock
import io.github.faening.lello.core.domain.mock.SleepQualityOptionMock
import io.github.faening.lello.core.model.journal.LocationOption
import io.github.faening.lello.core.model.journal.SleepActivityOption
import io.github.faening.lello.core.model.journal.SleepQualityOption
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

    LelloTheme(scheme = LelloColorScheme.DEFAULT) {
        SleepJournalDetailsContainer(
            sleepQualityOptions = sleepQualityOptions,
            onSleepQualityOptionToggle = viewModel::toggleSleepQualitySelection,
            onOpenSleepQualityOptionSettings = onOpenSleepQualityOptionSettings,
            sleepActivityOptions = sleepActivityOptions,
            onSleepActivityOptionToggle = viewModel::toggleSleepActivitySelection,
            onOpenSleepActivityOptionSettings = onOpenSleepActivityOptionSettings,
            locationOptions = locationOptions,
            onLocationOptionToggle = viewModel::toggleLocationSelection,
            onOpenLocationOptionSettings = onOpenLocationOptionSettings,
            sleeplessTime = viewModel.sleeplessTime.collectAsState().value,
            onSleeplessTimeChange = viewModel::updateSleeplessTime,
            onSave = viewModel::saveSleepJournal,
            onBack = onBack,
            onFinish = onFinish
        )
    }
}

@Composable
private fun SleepJournalDetailsContainer(
    sleepQualityOptions: List<SleepQualityOption>,
    onSleepQualityOptionToggle: (String) -> Unit,
    onOpenSleepQualityOptionSettings: () -> Unit,
    sleepActivityOptions: List<SleepActivityOption>,
    onSleepActivityOptionToggle: (String) -> Unit,
    onOpenSleepActivityOptionSettings: () -> Unit,
    locationOptions: List<LocationOption>,
    onLocationOptionToggle: (String) -> Unit,
    onOpenLocationOptionSettings: () -> Unit,
    sleeplessTime: String,
    onSleeplessTimeChange: (String) -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
    onFinish: () -> Unit
) {
    Scaffold(
        topBar = { SleepJournalDetailsTopBar(onBack) },
        bottomBar = { SleepJournalDetailsBottomBar(onSave, onFinish) }
    ) { paddingValues ->
        SleepJournalDetailsContent(
            sleepQualityOptions = sleepQualityOptions,
            onSleepQualityOptionToggle = onSleepQualityOptionToggle,
            onOpenSleepQualityOptionSettings = onOpenSleepQualityOptionSettings,
            sleepActivityOptions = sleepActivityOptions,
            onSleepActivityOptionToggle = onSleepActivityOptionToggle,
            onOpenSleepActivityOptionSettings = onOpenSleepActivityOptionSettings,
            locationOptions = locationOptions,
            onLocationOptionToggle = onLocationOptionToggle,
            onOpenLocationOptionSettings = onOpenLocationOptionSettings,
            sleeplessTime = sleeplessTime,
            onSleeplessTimeChange = onSleeplessTimeChange,
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
            .padding(Dimension.Medium)
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
    sleepQualityOptions: List<SleepQualityOption>,
    onSleepQualityOptionToggle: (String) -> Unit,
    onOpenSleepQualityOptionSettings: () -> Unit,
    sleepActivityOptions: List<SleepActivityOption>,
    onSleepActivityOptionToggle: (String) -> Unit,
    onOpenSleepActivityOptionSettings: () -> Unit,
    locationOptions: List<LocationOption>,
    onLocationOptionToggle: (String) -> Unit,
    onOpenLocationOptionSettings: () -> Unit,
    sleeplessTime: String,
    onSleeplessTimeChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(Dimension.Medium)
    ) {
        Text(
            text = "Gostaria de adicionar mais detalhes sobre o seu sono?",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(Dimension.ExtraLarge))

        LelloOptionPillSelector(
            title = "Como você descreve seu sono?",
            options = sleepQualityOptions,
            isSelected = { it.selected },
            onToggle = { option -> onSleepQualityOptionToggle(option.description) },
            onOpenSettings = onOpenSleepQualityOptionSettings,
            getLabel = { it.description }
        )
        Spacer(modifier = Modifier.height(Dimension.Large))

        LelloOptionPillSelector(
            title = "O que você fez antes de dormir?",
            options = sleepActivityOptions,
            isSelected = { it.selected },
            onToggle = { option -> onSleepActivityOptionToggle(option.description) },
            onOpenSettings = onOpenSleepActivityOptionSettings,
            getLabel = { it.description }
        )
        Spacer(modifier = Modifier.height(Dimension.Large))

        LelloOptionPillSelector(
            title = "Onde você dormiu?",
            options = locationOptions,
            isSelected = { it.selected },
            onToggle = { option ->  onLocationOptionToggle(option.description) },
            onOpenSettings = onOpenLocationOptionSettings,
            getLabel = { it.description }
        )
        Spacer(modifier = Modifier.height(Dimension.Large))

        Text(
            text = "Quanto tempo você ficou acordado?",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = Dimension.Medium)
        )
        LelloTextField(
            value = sleeplessTime,
            onValueChange = onSleeplessTimeChange,
            placeholder = "Ex: 30min",
            maxLength = 10,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
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
            sleepQualityOptions = SleepQualityOptionMock.list,
            onSleepQualityOptionToggle = { _ -> },
            onOpenSleepQualityOptionSettings = {},
            sleepActivityOptions = SleepActivityOptionMock.list,
            onSleepActivityOptionToggle = { _ -> },
            onOpenSleepActivityOptionSettings = {},
            locationOptions = LocationOptionMock.list,
            onLocationOptionToggle = { _ -> },
            onOpenLocationOptionSettings = {},
            sleeplessTime = "2h",
            onSleeplessTimeChange = {},
            onSave = {},
            onBack = {},
            onFinish = {}
        )
    }
}