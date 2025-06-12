package io.github.faening.lello.feature.journal.meal.screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.LelloOptionPillSelector
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.journal.FoodOption
import io.github.faening.lello.core.model.journal.LocationOption
import io.github.faening.lello.core.model.journal.PortionOption
import io.github.faening.lello.core.model.journal.SocialOption
import io.github.faening.lello.feature.journal.meal.JournalMealViewModel

@Composable
internal fun JournalMealDetailsScreen(
    viewModel: JournalMealViewModel,
    onBack: () -> Unit,
    onFinish: () -> Unit,
    onOpenFoodOptionSettings: () -> Unit,
    onOpenPortionOptionSettings: () -> Unit,
    onOpenLocationOptionSettings: () -> Unit,
    onOpenSocialOptionSettings: () -> Unit,
) {
    val foodOptions = mutableListOf<FoodOption>()
    val portionOptions = mutableListOf<PortionOption>()
    val locationOptions = mutableListOf<LocationOption>()
    val socialOptions = mutableListOf<SocialOption>()

    LelloTheme {
        JournalMealDetailsContainer(
            foodOptions = foodOptions,
            onFoodOptionToggle = { _ -> },
            onOpenFoodOptionSettings = onOpenFoodOptionSettings,
            portionOptions = portionOptions,
            onPortionOptionToggle = { _ -> },
            onOpenPortionOptionSettings = onOpenPortionOptionSettings,
            locationOptions = locationOptions,
            onLocationOptionToggle = { _ -> },
            onOpenLocationOptionSettings = onOpenLocationOptionSettings,
            socialOptions = socialOptions,
            onSocialOptionToggle = { _ -> },
            onOpenSocialOptionSettings = onOpenSocialOptionSettings,
            onBack = onBack,
            onFinish = onFinish
        )
    }
}

@Composable
private fun JournalMealDetailsContainer(
    foodOptions: List<FoodOption>,
    onFoodOptionToggle: (String) -> Unit,
    onOpenFoodOptionSettings: () -> Unit,
    portionOptions: List<PortionOption>,
    onPortionOptionToggle: (String) -> Unit,
    onOpenPortionOptionSettings: () -> Unit,
    locationOptions: List<LocationOption>,
    onLocationOptionToggle: (String) -> Unit,
    onOpenLocationOptionSettings: () -> Unit,
    socialOptions: List<SocialOption>,
    onSocialOptionToggle: (String) -> Unit,
    onOpenSocialOptionSettings: () -> Unit,
    onBack: () -> Unit,
    onFinish: () -> Unit,
) {
    Scaffold(
        topBar = { JournalMealDetailsTopBar(onBack) },
        bottomBar = { JournalMealDetailsBottomBar(onFinish) }
    ) { paddingValues ->
        JournalMealDetailsContent(
            foodOptions = foodOptions,
            onFoodOptionToggle = onFoodOptionToggle,
            onOpenFoodOptionSettings = onOpenFoodOptionSettings,
            portionOptions = portionOptions,
            onPortionOptionToggle = onPortionOptionToggle,
            onOpenPortionOptionSettings = onOpenPortionOptionSettings,
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
private fun JournalMealDetailsTopBar(
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Alimentação"),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
private fun JournalMealDetailsBottomBar(
    onFinish: () -> Unit
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
    }
}

@Composable
private fun JournalMealDetailsContent(
    foodOptions: List<FoodOption>,
    onFoodOptionToggle: (String) -> Unit,
    onOpenFoodOptionSettings: () -> Unit,
    portionOptions: List<PortionOption>,
    onPortionOptionToggle: (String) -> Unit,
    onOpenPortionOptionSettings: () -> Unit,
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
            text = "Gostaria de adicionar mais detalhes sobre a sua alimentação?",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(Dimension.ExtraLarge))

        LelloOptionPillSelector(
            title = "Qual foi o tipo de alimento?",
            options = foodOptions,
            isSelected = { it.selected },
            onToggle = { option -> onFoodOptionToggle(option.description) },
            onOpenSettings = onOpenFoodOptionSettings,
            getLabel = { it.description }
        )
        Spacer(modifier = Modifier.height(Dimension.Large))

        LelloOptionPillSelector(
            title = "Quanto você comeu?",
            options = portionOptions,
            isSelected = { it.selected },
            onToggle = { option -> onPortionOptionToggle(option.description) },
            onOpenSettings = onOpenPortionOptionSettings,
            getLabel = { it.description }
        )
        Spacer(modifier = Modifier.height(Dimension.Large))

        LelloOptionPillSelector(
            title = "Onde você estava?",
            options = locationOptions,
            isSelected = { it.selected },
            onToggle = { option -> onLocationOptionToggle(option.description) },
            onOpenSettings = onOpenLocationOptionSettings,
            getLabel = { it.description }
        )
        Spacer(modifier = Modifier.height(Dimension.Large))

        LelloOptionPillSelector(
            title = "Com quem você estava?",
            options = socialOptions,
            isSelected = { it.selected },
            onToggle = { option -> onSocialOptionToggle(option.description) },
            onOpenSettings = onOpenSocialOptionSettings,
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
fun JournalMealDetailsScreenPreview() {
    LelloTheme {
        JournalMealDetailsContainer(
            foodOptions = mutableListOf(),
            onFoodOptionToggle = { _ -> },
            onOpenFoodOptionSettings = {},
            portionOptions = mutableListOf(),
            onPortionOptionToggle = { _ -> },
            onOpenPortionOptionSettings = {},
            locationOptions = mutableListOf(),
            onLocationOptionToggle = { _ -> },
            onOpenLocationOptionSettings = {},
            socialOptions = mutableListOf(),
            onSocialOptionToggle = { _ -> },
            onOpenSocialOptionSettings = {},
            onBack = {},
            onFinish = {},
        )
    }
}