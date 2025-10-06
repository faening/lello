package io.github.faening.lello.feature.journal.meal.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.LelloOptionPillSelector
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.textfield.LelloTextField
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.domain.mock.FoodOptionMock
import io.github.faening.lello.core.domain.mock.LocationOptionMock
import io.github.faening.lello.core.domain.mock.PortionOptionMock
import io.github.faening.lello.core.domain.mock.SocialOptionMock
import io.github.faening.lello.core.model.option.FoodOption
import io.github.faening.lello.core.model.option.LocationOption
import io.github.faening.lello.core.model.option.PortionOption
import io.github.faening.lello.core.model.option.SocialOption
import io.github.faening.lello.feature.journal.meal.MealJournalViewModel

@Composable
internal fun MealJournalDetailsScreen(
    viewModel: MealJournalViewModel,
    onBack: () -> Unit,
    onFinish: () -> Unit,
    onOpenFoodOptionSettings: () -> Unit,
    onOpenPortionOptionSettings: () -> Unit,
    onOpenLocationOptionSettings: () -> Unit,
    onOpenSocialOptionSettings: () -> Unit,
) {
    val coinsAcquired by viewModel.coinsAcquired.collectAsState()
    val foodOptions by viewModel.foodOptions.collectAsState()
    val portionOptions by viewModel.portionOptions.collectAsState()
    val locationOptions by viewModel.locationOptions.collectAsState()
    val socialOptions by viewModel.socialOptions.collectAsState()

    LelloTheme {
        MealJournalDetailsContainer(
            coinsAcquired = coinsAcquired,
            mealTime = viewModel.mealTime.collectAsState().value,
            onMealTimeChange = viewModel::updateMealTime,
            foodOptions = foodOptions,
            onFoodOptionToggle = viewModel::toggleFoodSelection,
            onOpenFoodOptionSettings = onOpenFoodOptionSettings,
            portionOptions = portionOptions,
            onPortionOptionToggle = viewModel::togglePortionSelection,
            onOpenPortionOptionSettings = onOpenPortionOptionSettings,
            locationOptions = locationOptions,
            onLocationOptionToggle = viewModel::toggleLocationSelection,
            onOpenLocationOptionSettings = onOpenLocationOptionSettings,
            socialOptions = socialOptions,
            onSocialOptionToggle = viewModel::toggleSocialSelection,
            onOpenSocialOptionSettings = onOpenSocialOptionSettings,
            onSave = viewModel::saveMealJournal,
            onBack = onBack,
            onFinish = onFinish
        )
    }
}

@Composable
private fun MealJournalDetailsContainer(
    coinsAcquired: Int,
    mealTime: String,
    onMealTimeChange: (String) -> Unit,
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
    onSave: () -> Unit,
    onBack: () -> Unit,
    onFinish: () -> Unit,
) {
    Scaffold(
        topBar = { MealJournalDetailsTopBar(onBack) },
        bottomBar = { MealJournalDetailsBottomBar(onSave, onFinish) }
    ) { paddingValues ->
        MealJournalDetailsContent(
            coinsAcquired = coinsAcquired,
            mealTime = mealTime,
            onMealTimeChange = onMealTimeChange,
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
private fun MealJournalDetailsTopBar(
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Alimentação"),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
private fun MealJournalDetailsBottomBar(
    onSave: () -> Unit,
    onFinish: () -> Unit
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
        )
    }
}

@Composable
private fun MealJournalDetailsContent(
    coinsAcquired: Int,
    mealTime: String,
    onMealTimeChange: (String) -> Unit,
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
            .fillMaxSize()
            .padding(Dimension.spacingRegular)
    ) {
        // Header
        Text(
            text = "Gostaria de adicionar mais detalhes sobre a sua alimentação?",
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
            Text(
                text = "Que horas foi a refeição?",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(bottom = Dimension.spacingRegular)
            )
            LelloTextField(
                value = mealTime,
                onValueChange = onMealTimeChange,
                placeholder = "Ex: 30min",
                maxLength = 10,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(Dimension.spacingExtraLarge))

            LelloOptionPillSelector(
                title = "Qual foi o tipo de alimento?",
                options = foodOptions,
                isSelected = { it.selected },
                onToggle = { option -> onFoodOptionToggle(option.description) },
                onOpenSettings = onOpenFoodOptionSettings,
                getLabel = { it.description }
            )
            Spacer(modifier = Modifier.height(Dimension.spacingLarge))

            LelloOptionPillSelector(
                title = "Quanto você comeu?",
                options = portionOptions,
                isSelected = { it.selected },
                onToggle = { option -> onPortionOptionToggle(option.description) },
                onOpenSettings = onOpenPortionOptionSettings,
                getLabel = { it.description }
            )
            Spacer(modifier = Modifier.height(Dimension.spacingLarge))

            LelloOptionPillSelector(
                title = "Onde você estava?",
                options = locationOptions,
                isSelected = { it.selected },
                onToggle = { option -> onLocationOptionToggle(option.description) },
                onOpenSettings = onOpenLocationOptionSettings,
                getLabel = { it.description }
            )
            Spacer(modifier = Modifier.height(Dimension.spacingLarge))

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
}

@Composable
@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
fun MealJournalDetailsScreenPreview() {
    LelloTheme {
        MealJournalDetailsContainer(
            coinsAcquired = 100,
            mealTime = "",
            onMealTimeChange = { _ -> },
            foodOptions = FoodOptionMock.list,
            onFoodOptionToggle = { _ -> },
            onOpenFoodOptionSettings = {},
            portionOptions = PortionOptionMock.list,
            onPortionOptionToggle = { _ -> },
            onOpenPortionOptionSettings = {},
            locationOptions = LocationOptionMock.list,
            onLocationOptionToggle = { _ -> },
            onOpenLocationOptionSettings = {},
            socialOptions = SocialOptionMock.list,
            onSocialOptionToggle = { _ -> },
            onOpenSocialOptionSettings = {},
            onSave = {},
            onBack = {},
            onFinish = {},
        )
    }
}