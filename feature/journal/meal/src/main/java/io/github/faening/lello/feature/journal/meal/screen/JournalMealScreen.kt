package io.github.faening.lello.feature.journal.meal.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.component.LelloOptionPillSelector
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.journal.MealOption
import io.github.faening.lello.feature.journal.meal.JournalMealViewModel
import io.github.faening.lello.core.designsystem.R as designsystemR

@Composable
internal fun JournalMealScreen(
    viewModel: JournalMealViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onOpenMealOptionSettings: () -> Unit,
) {
    val mealOptions = mutableListOf<MealOption>()

    LelloTheme {
        JournalMealContainer(
            mealOptions = mealOptions,
            onMealOptionToggle = { _ -> },
            onOpenMealOptionSettings = onOpenMealOptionSettings,
            onBack = onBack,
            onNext = onNext
        )
    }
}

@Composable
private fun JournalMealContainer(
    mealOptions: List<MealOption>,
    onMealOptionToggle: (String) -> Unit,
    onOpenMealOptionSettings: () -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    Scaffold(
        topBar = { JournalMealTopBar(onBack) },
        bottomBar = { JournalMealBottomBar(onNext) }
    ) { paddingValues ->
        JournalMealContent(
            mealOptions = mealOptions,
            onMealOptionToggle = onMealOptionToggle,
            onOpenMealOptionSettings = onOpenMealOptionSettings,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun JournalMealTopBar(
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Alimentação"),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
private fun JournalMealBottomBar(
    onNext: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End)
            .padding(Dimension.Medium)
    ) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = onNext
        )
    }
}

@Composable
private fun JournalMealContent(
    mealOptions: List<MealOption>,
    onMealOptionToggle: (String) -> Unit,
    onOpenMealOptionSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(Dimension.Medium)
    ) {
        Text(
            text = "Qual refeição você fez?",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(Dimension.ExtraLarge))

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

@Composable
@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
private fun JournalMealScreenPreview() {
    LelloTheme {
        JournalMealContainer(
            mealOptions = mutableListOf(),
            onMealOptionToggle = { _ -> },
            onOpenMealOptionSettings = {},
            onBack = {},
            onNext = {},
        )
    }
}