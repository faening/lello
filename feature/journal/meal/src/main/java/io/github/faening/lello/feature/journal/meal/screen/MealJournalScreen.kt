package io.github.faening.lello.feature.journal.meal.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import io.github.faening.lello.core.designsystem.component.button.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.component.pill.LelloOptionPillSelector
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.domain.mock.MealOptionMock
import io.github.faening.lello.core.model.option.MealOption
import io.github.faening.lello.feature.journal.meal.MealJournalViewModel

@Composable
internal fun MealJournalScreen(
    viewModel: MealJournalViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onOpenMealOptionSettings: () -> Unit,
) {
    val mealOptions by viewModel.mealOptions.collectAsState()

    LelloTheme {
        MealJournalContainer(
            mealOptions = mealOptions,
            onMealOptionToggle = viewModel::toggleMealSelection,
            onOpenMealOptionSettings = onOpenMealOptionSettings,
            onBack = onBack,
            onNext = onNext
        )
    }
}

@Composable
private fun MealJournalContainer(
    mealOptions: List<MealOption>,
    onMealOptionToggle: (String) -> Unit,
    onOpenMealOptionSettings: () -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    val anySelected = mealOptions.any { it.selected }

    Scaffold(
        topBar = { MealJournalTopBar(onBack) },
        bottomBar = { MealJournalBottomBar(anySelected, onNext) }
    ) { paddingValues ->
        MealJournalContent(
            mealOptions = mealOptions,
            onMealOptionToggle = onMealOptionToggle,
            onOpenMealOptionSettings = onOpenMealOptionSettings,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun MealJournalTopBar(
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Alimentação"),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
private fun MealJournalBottomBar(
    enabled: Boolean,
    onNext: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End)
            .padding(Dimension.spacingRegular)
    ) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(LelloIcons.Outlined.ArrowRightLarge.resId),
            contentDescription = "Próximo",
            enabled = enabled,
            onClick = onNext
        )
    }
}

@Composable
private fun MealJournalContent(
    mealOptions: List<MealOption>,
    onMealOptionToggle: (String) -> Unit,
    onOpenMealOptionSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimension.spacingRegular)
    ) {
        // Header
        Text(
            text = "Qual refeição você fez?",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(Dimension.spacingExtraLarge))

        // Scrollable area
        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            LelloOptionPillSelector(
                title = null,
                options = mealOptions,
                isSelected = { it.selected },
                onToggle = { option -> onMealOptionToggle(option.description) },
                onOpenSettings = onOpenMealOptionSettings,
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
private fun MealJournalScreenPreview() {
    LelloTheme {
        MealJournalContainer(
            mealOptions = MealOptionMock.list,
            onMealOptionToggle = { _ -> },
            onOpenMealOptionSettings = {},
            onBack = {},
            onNext = {},
        )
    }
}