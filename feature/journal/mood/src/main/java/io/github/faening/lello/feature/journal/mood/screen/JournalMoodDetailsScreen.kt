package io.github.faening.lello.feature.journal.mood.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.component.LelloOptionPillSelector
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.domain.mock.ClimateOptionMock
import io.github.faening.lello.core.domain.mock.LocationOptionMock
import io.github.faening.lello.core.domain.mock.SocialOptionMock
import io.github.faening.lello.core.model.journal.ClimateOption
import io.github.faening.lello.core.model.journal.LocationOption
import io.github.faening.lello.core.model.journal.SocialOption
import io.github.faening.lello.feature.journal.mood.JournalMoodViewModel
import io.github.faening.lello.core.designsystem.R as designsystemR

@Composable
internal fun JournalMoodDetailsScreen(
    viewModel: JournalMoodViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    onOpenClimateOptionSettings: () -> Unit,
    onOpenLocationOptionSettings: () -> Unit,
    onOpenSocialOptionSettings: () -> Unit
) {
    val mood by viewModel.currentMood.collectAsState()
    val entryTime by viewModel.entryTimeFormatted.collectAsState()
    val climateOptions by viewModel.climateOptions.collectAsState()
    val locationOptions by viewModel.locationOptions.collectAsState()
    val socialOptions by viewModel.socialOptions.collectAsState()

    LelloTheme(scheme = mood.colorScheme) {
        JournalMoodDetailsContainer(
            entryTime = entryTime,
            climateOptions = climateOptions,
            onClimateOptionToggle = viewModel::toggleClimateSelection,
            onOpenClimateOptionSettings = onOpenClimateOptionSettings,
            locationOptions = locationOptions,
            onLocationOptionToggle = viewModel::toggleLocationSelection,
            onOpenLocationOptionSettings = onOpenLocationOptionSettings,
            socialOptions = socialOptions,
            onSocialOptionToggle = viewModel::toggleSocialSelection,
            onBack = onBack,
            onNext = onNext,
            onFinish = onFinish,
            onOpenSocialOptionSettings = onOpenSocialOptionSettings
        )
    }
}

@Composable
private fun JournalMoodDetailsContainer(
    entryTime: String,
    climateOptions: List<ClimateOption>,
    onClimateOptionToggle: (String) -> Unit,
    onOpenClimateOptionSettings: () -> Unit,
    locationOptions: List<LocationOption>,
    onLocationOptionToggle: (String) -> Unit,
    onOpenLocationOptionSettings: () -> Unit,
    socialOptions: List<SocialOption>,
    onSocialOptionToggle: (String) -> Unit,
    onOpenSocialOptionSettings: () -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit
) {
    Scaffold(
        topBar = { JournalMoodDetailsTopBar(entryTime, onBack) },
        bottomBar = { JournalMoodDetailsBottomBar(onNext, onFinish) }
    ) { paddingValues ->
        JournalMoodDetailsContent(
            climateOptions = climateOptions,
            onClimateOptionToggle = onClimateOptionToggle,
            onOpenClimateOptionSettings = onOpenClimateOptionSettings,
            locationOptions = locationOptions,
            onLocationOptionToggle = onLocationOptionToggle,
            onOpenLocationOptionSettings = onOpenLocationOptionSettings,
            socialOptions = socialOptions,
            onSocialOptionToggle = onSocialOptionToggle,
            onOpenSocialOptionSettings = onOpenSocialOptionSettings,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun JournalMoodDetailsTopBar(
    entryTime: String,
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Hoje, $entryTime"),
        navigateUp = TopAppBarAction(onClick = onBack)
    )
}

@Composable
private fun JournalMoodDetailsBottomBar(
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
            onClick = onFinish,
            modifier = Modifier.weight(1f)
        )

        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = onNext
        )
    }
}

@Composable
private fun JournalMoodDetailsContent(
    climateOptions: List<ClimateOption>,
    onClimateOptionToggle: (String) -> Unit,
    onOpenClimateOptionSettings: () -> Unit,
    locationOptions: List<LocationOption>,
    onLocationOptionToggle: (String) -> Unit,
    onOpenLocationOptionSettings: () -> Unit,
    socialOptions: List<SocialOption>,
    onSocialOptionToggle: (String) -> Unit,
    onOpenSocialOptionSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(Dimension.Medium)
    ) {
        Text(
            text = "Gostaria de adicionar mais detalhes sobre o seu humor?",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(Dimension.ExtraLarge))

        LelloOptionPillSelector(
            title = "Como está o clima agora?",
            options = climateOptions,
            isSelected = { it.selected },
            onToggle = { option -> onClimateOptionToggle(option.description) },
            onOpenSettings = onOpenClimateOptionSettings,
            getLabel = { it.description }
        )
        Spacer(modifier = Modifier.height(Dimension.Large))

        LelloOptionPillSelector(
            title = "Onde você está?",
            options = locationOptions,
            isSelected = { it.selected },
            onToggle = { option ->  onLocationOptionToggle(option.description) },
            onOpenSettings = onOpenLocationOptionSettings,
            getLabel = { it.description }
        )
        Spacer(modifier = Modifier.height(Dimension.Large))

        LelloOptionPillSelector(
            title = "Com quem você está agora?",
            options = socialOptions,
            isSelected = { it.selected },
            onToggle = { option -> onSocialOptionToggle(option.description) },
            onOpenSettings = onOpenSocialOptionSettings,
            getLabel = { it.description },
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
private fun JournalMoodDetailsScreenPreview() {
    LelloTheme {
        JournalMoodDetailsContainer(
            entryTime = "09:41",
            climateOptions = ClimateOptionMock.list,
            onClimateOptionToggle = { _ -> },
            onOpenClimateOptionSettings = {},
            locationOptions = LocationOptionMock.list,
            onLocationOptionToggle = { _ -> },
            onOpenLocationOptionSettings = {},
            socialOptions = SocialOptionMock.list,
            onSocialOptionToggle = { _ -> },
            onOpenSocialOptionSettings = {},
            onBack = {},
            onNext = {},
            onFinish = {}
        )
    }
}