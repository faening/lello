package io.github.faening.lello.feature.journal.mood.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.button.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.component.pill.LelloOptionPillSelector
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.domain.mock.ClimateOptionMock
import io.github.faening.lello.core.domain.mock.HealthOptionMock
import io.github.faening.lello.core.domain.mock.LocationOptionMock
import io.github.faening.lello.core.domain.mock.SocialOptionMock
import io.github.faening.lello.core.model.option.ClimateOption
import io.github.faening.lello.core.model.option.HealthOption
import io.github.faening.lello.core.model.option.LocationOption
import io.github.faening.lello.core.model.option.SocialOption
import io.github.faening.lello.feature.journal.mood.MoodJournalViewModel

@Composable
internal fun MoodJournalDetailsScreen(
    viewModel: MoodJournalViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    onOpenHealthOptionSettings: () -> Unit,
    onOpenClimateOptionSettings: () -> Unit,
    onOpenLocationOptionSettings: () -> Unit,
    onOpenSocialOptionSettings: () -> Unit
) {
    val moodColor by viewModel.currentMood.collectAsState()
    val entryTime by viewModel.entryDateTime.collectAsState()
    val healthOptions by viewModel.healthOptions.collectAsState()
    val climateOptions by viewModel.climateOptions.collectAsState()
    val locationOptions by viewModel.locationOptions.collectAsState()
    val socialOptions by viewModel.socialOptions.collectAsState()
    val coinsAcquired by viewModel.coinsAcquired.collectAsState()

    MoodJournalDetailsContent(
        entryTime = entryTime,
        healthOptions = healthOptions,
        onHealthOptionToggle = viewModel::toggleHealthSelection,
        onOpenHealthOptionSettings = onOpenHealthOptionSettings,
        climateOptions = climateOptions,
        onClimateOptionToggle = viewModel::toggleClimateSelection,
        onOpenClimateOptionSettings = onOpenClimateOptionSettings,
        locationOptions = locationOptions,
        onLocationOptionToggle = viewModel::toggleLocationSelection,
        onOpenLocationOptionSettings = onOpenLocationOptionSettings,
        socialOptions = socialOptions,
        onSocialOptionToggle = viewModel::toggleSocialSelection,
        onSave = viewModel::saveMoodJournal,
        onBack = onBack,
        onNext = onNext,
        onFinish = onFinish,
        onOpenSocialOptionSettings = onOpenSocialOptionSettings,
        coinsAcquired = coinsAcquired,
        moodColor = moodColor
    )
}

@Composable
private fun MoodJournalDetailsContent(
    entryTime: String,
    healthOptions: List<HealthOption>,
    onHealthOptionToggle: (String) -> Unit,
    onOpenHealthOptionSettings: () -> Unit,
    climateOptions: List<ClimateOption>,
    onClimateOptionToggle: (String) -> Unit,
    onOpenClimateOptionSettings: () -> Unit,
    locationOptions: List<LocationOption>,
    onLocationOptionToggle: (String) -> Unit,
    onOpenLocationOptionSettings: () -> Unit,
    socialOptions: List<SocialOption>,
    onSocialOptionToggle: (String) -> Unit,
    onOpenSocialOptionSettings: () -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    coinsAcquired: Int,
    moodColor: MoodColor
) {
    LelloTheme(moodColor = moodColor) {
        Scaffold(
            topBar = {
                TopAppBarSection(
                    entryTime = entryTime,
                    moodColor = moodColor,
                    onBack = onBack
                )
            },
            bottomBar = {
                BottomBarSection(
                    moodColor = moodColor,
                    onSave = onSave,
                    onNext = onNext,
                    onFinish = onFinish
                )
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(horizontal = Dimension.spacingRegular)
            ) {
                // Header
                Text(
                    text = "Gostaria de adicionar mais detalhes sobre o seu humor?",
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = Dimension.spacingRegular)
                )
                Text(
                    text = "Ganhe $coinsAcquired moeads ao concluir",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = Dimension.spacingRegular)
                )

                // Content
                Column(
                    modifier = Modifier
                        .weight(1f)
                        .verticalScroll(rememberScrollState())
                ) {
                    LelloOptionPillSelector(
                        title = "Como está a sua saúde?",
                        options = healthOptions,
                        isSelected = { it.selected },
                        onToggle = { option -> onHealthOptionToggle(option.description) },
                        onOpenSettings = onOpenHealthOptionSettings,
                        getLabel = { it.description },
                        moodColor = moodColor,
                        modifier = Modifier.padding(bottom = Dimension.spacingLarge)
                    )

                    LelloOptionPillSelector(
                        title = "Como está o clima?",
                        options = climateOptions,
                        isSelected = { it.selected },
                        onToggle = { option -> onClimateOptionToggle(option.description) },
                        onOpenSettings = onOpenClimateOptionSettings,
                        getLabel = { it.description },
                        moodColor = moodColor,
                        modifier = Modifier.padding(bottom = Dimension.spacingLarge)
                    )

                    LelloOptionPillSelector(
                        title = "Onde você está?",
                        options = locationOptions,
                        isSelected = { it.selected },
                        onToggle = { option ->  onLocationOptionToggle(option.description) },
                        onOpenSettings = onOpenLocationOptionSettings,
                        getLabel = { it.description },
                        moodColor = moodColor,
                        modifier = Modifier.padding(bottom = Dimension.spacingLarge)
                    )

                    LelloOptionPillSelector(
                        title = "Com quem você está agora?",
                        options = socialOptions,
                        isSelected = { it.selected },
                        onToggle = { option -> onSocialOptionToggle(option.description) },
                        onOpenSettings = onOpenSocialOptionSettings,
                        moodColor = moodColor,
                        getLabel = { it.description }
                    )
                }
            }
        }
    }
}

@Composable
private fun TopAppBarSection(
    entryTime: String,
    moodColor: MoodColor,
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Hoje, $entryTime"),
        navigateUp = TopAppBarAction(onClick = onBack),
        moodColor = moodColor
    )
}

@Composable
private fun BottomBarSection(
    moodColor: MoodColor,
    onSave: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.spacingRegular),
        horizontalArrangement = Arrangement.spacedBy(Dimension.spacingRegular),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LelloFilledButton(
            label = "Concluir",
            onClick = {
                onSave()
                onFinish()
            },
            moodColor = moodColor,
            modifier = Modifier.weight(1f)
        )
        LelloFloatingActionButton(
            icon = LelloIcons.Outlined.ArrowRightLarge.imageVector,
            contentDescription = "Próximo",
            moodColor = moodColor,
            onClick = onNext
        )
    }
}

// region Previews

@Composable
@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
private fun MoodJournalDetailsScreenPreview_LightMode() {
    MoodJournalDetailsContent(
        entryTime = "09:41",
        healthOptions = HealthOptionMock.list,
        onHealthOptionToggle = { _ -> },
        onOpenHealthOptionSettings = {},
        climateOptions = ClimateOptionMock.list,
        onClimateOptionToggle = { _ -> },
        onOpenClimateOptionSettings = {},
        locationOptions = LocationOptionMock.list,
        onLocationOptionToggle = { _ -> },
        onOpenLocationOptionSettings = {},
        socialOptions = SocialOptionMock.list,
        onSocialOptionToggle = { _ -> },
        onOpenSocialOptionSettings = {},
        onSave = {},
        onBack = {},
        onNext = {},
        onFinish = {},
        coinsAcquired = 100,
        moodColor = MoodColor.DEFAULT
    )
}

// endregion Previews