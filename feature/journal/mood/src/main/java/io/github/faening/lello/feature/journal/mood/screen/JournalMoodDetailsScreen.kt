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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import io.github.faening.lello.core.model.journal.ClimateOption
import io.github.faening.lello.core.model.journal.LocationOption
import io.github.faening.lello.core.model.journal.SocialOption
import io.github.faening.lello.feature.journal.mood.JournalMoodViewModel
import io.github.faening.lello.core.designsystem.R as designsystemR

/**
 * Step 3: Detalhes adicionais sobre o humor do usuário.
 */
@Composable
internal fun JournalMoodDetailsScreen(
    viewModel: JournalMoodViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    onOpenClimateSettings: () -> Unit,
    onOpenLocationSettings: () -> Unit,
    onOpenSocialSettings: () -> Unit
) {
    val mood by viewModel.currentMood.collectAsState()
    val entryTime by viewModel.entryTimeFormatted.collectAsState()
    val climates by viewModel.climates.collectAsState()
    val locations by viewModel.locations.collectAsState()
    val socials by viewModel.socials.collectAsState()

    LelloTheme(scheme = mood.colorScheme) {
        JournalMoodDetailsContainer(
            entryTime = entryTime,
            climates = climates,
            locations = locations,
            socials = socials,
            onBack = onBack,
            onNext = onNext,
            onFinish = onFinish,
            onOpenClimateSettings = onOpenClimateSettings,
            onOpenLocationSettings = onOpenLocationSettings,
            onOpenSocialSettings = onOpenSocialSettings
        )
    }
}

@Composable
private fun JournalMoodDetailsContainer(
    entryTime: String,
    climates: List<ClimateOption>,
    locations: List<LocationOption>,
    socials: List<SocialOption>,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    onOpenClimateSettings: () -> Unit,
    onOpenLocationSettings: () -> Unit,
    onOpenSocialSettings: () -> Unit
) {
    Scaffold(
        topBar = { JournalMoodDetailsTopBar(entryTime, onBack) },
        bottomBar = { JournalMoodDetailsBottomBar(onNext, onFinish) }
    ) { paddingValues ->
        JournalMoodDetailsContent(
            climates = climates,
            locations = locations,
            socials = socials,
            onOpenClimateSettings = onOpenClimateSettings,
            onOpenLocationSettings = onOpenLocationSettings,
            onOpenSocialSettings = onOpenSocialSettings,
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
    climates: List<ClimateOption>,
    locations: List<LocationOption>,
    socials: List<SocialOption>,
    onOpenClimateSettings: () -> Unit,
    onOpenLocationSettings: () -> Unit,
    onOpenSocialSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    var selectedClimates by remember { mutableStateOf(setOf<String>()) }
    var selectedLocations by remember { mutableStateOf(setOf<String>()) }
    var selectedSocials by remember { mutableStateOf(setOf<String>()) }

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
            options = climates,
            isSelected = { selectedClimates.contains(it.description) },
            onToggle = { option ->
                selectedClimates = if (selectedClimates.contains(option.description))
                    selectedClimates - option.description
                else
                    selectedClimates + option.description
            },
            onOpenSettings = onOpenClimateSettings,
            getLabel = { it.description }
        )
        Spacer(modifier = Modifier.height(Dimension.ExtraLarge))

        LelloOptionPillSelector(
            title = "Onde você está?",
            options = locations,
            isSelected = { selectedLocations.contains(it.description) },
            onToggle = { option ->
                selectedLocations = if (selectedLocations.contains(option.description))
                    selectedLocations - option.description
                else
                    selectedLocations + option.description
            },
            onOpenSettings = onOpenLocationSettings,
            getLabel = { it.description }
        )
        Spacer(modifier = Modifier.height(Dimension.ExtraLarge))

        LelloOptionPillSelector(
            title = "Com quem você está agora?",
            options = socials,
            isSelected = { selectedSocials.contains(it.description) },
            onToggle = { option ->
                selectedSocials = if (selectedSocials.contains(option.description))
                    selectedSocials - option.description
                else
                    selectedSocials + option.description
            },
            onOpenSettings = onOpenSocialSettings,
            getLabel = { it.description }
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
    val climates = listOf(
        ClimateOption(id = 1, description = "Céu Limpo", blocked = false, active = true),
        ClimateOption(id = 2, description = "Chuvoso", blocked = false, active = true),
        ClimateOption(id = 3, description = "Empoeirado", blocked = false, active = true),
    )
    val locations = listOf(
        LocationOption(id = 1, description = "Academia", blocked = false, active = true),
        LocationOption(id = 2, description = "Aeroporto", blocked = false, active = true),
        LocationOption(id = 3, description = "Bar", blocked = false, active = true),
    )
    val socials = listOf(
        SocialOption(id = 1, description = "Amigo(a)", blocked = false, active = true),
        SocialOption(id = 2, description = "Família(a)", blocked = false, active = true),
        SocialOption(id = 3, description = "Pet", blocked = false, active = true),
    )

    LelloTheme {
        JournalMoodDetailsContainer(
            entryTime = "09:41",
            climates = climates,
            locations = locations,
            socials = socials,
            onBack = {},
            onNext = {},
            onFinish = {},
            onOpenClimateSettings = {},
            onOpenLocationSettings = {},
            onOpenSocialSettings = {}
        )
    }
}