package io.github.faening.lello.feature.diary.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.pill.LelloFilledPill
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.domain.mock.MealJournalMock
import io.github.faening.lello.core.domain.util.toLocalDateTime
import io.github.faening.lello.core.model.journal.MealJournal
import io.github.faening.lello.feature.diary.DiaryViewModel
import java.time.format.DateTimeFormatter

@Composable
fun DiaryMealDetailsScreen(
    viewModel: DiaryViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val selectedMealJournal by viewModel.selectedMealJournal.collectAsState()

    DiaryMealDetailsScreenContent(
        mealJournal = selectedMealJournal ?: return,
        onBackClick = onBackClick
    )
}

@Composable
private fun DiaryMealDetailsScreenContent(
    mealJournal: MealJournal,
    onBackClick: () -> Unit,
) {
    LelloTheme {
        Scaffold(
            topBar = { TopAppBarSection(onBackClick) }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(Dimension.spacingMedium)
                    .verticalScroll(rememberScrollState())
            ) {
                mealJournal.let { meal ->
                    Column {
                        MealTimestampSection(meal)
                        MealTimeSection(meal)
                        MealOptionsSection(meal)
                        AppetiteOptionsSection(meal)
                        FoodOptionsSection(meal)
                        PortionOptionsSection(meal)
                        LocationOptionsSection(meal)
                        SocialOptionsSection(meal)
                    }
                }
            }
        }
    }
}

@Composable
private fun TopAppBarSection(
    onBackClick: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Diário de Alimentação"),
        navigateUp = TopAppBarAction(onClick = onBackClick),
        moodColor = MoodColor.INVERSE
    )
}

@Composable
private fun MealTimestampSection(
    meal: MealJournal
) {
    DetailSection(
        title = "Data e hora",
        content = {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH'h' mm'm'")
            Text(
                text = formatter.format(meal.createdAt.toLocalDateTime()),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    )
}

@Composable
private fun MealTimeSection(
    meal: MealJournal
) {
    DetailSection(
        title = "Horário da refeição",
        content = {
            val formatter = DateTimeFormatter.ofPattern("HH'h' mm'm'")
            Text(
                text = formatter.format(meal.mealTime.toLocalDateTime()),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    )
}

@Composable
private fun MealOptionsSection(
    meal: MealJournal
) {
    DetailSection(
        title = "Tipo de refeição",
        content = {
            if (meal.mealOptions.isEmpty()) {
                LelloFilledPill("Não especificado")
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimension.spacingSmall),
                    verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
                ) {
                    meal.mealOptions.forEach {
                        LelloFilledPill(it.description)
                    }
                }
            }
        }
    )
}

@Composable
private fun AppetiteOptionsSection(
    meal: MealJournal
) {
    DetailSection(
        title = "Como estava seu apetite?",
        content = {
            if (meal.appetiteOptions.isEmpty()) {
                LelloFilledPill("Não especificado")
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimension.spacingSmall),
                    verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
                ) {
                    meal.appetiteOptions.forEach {
                        LelloFilledPill(it.description)
                    }
                }
            }
        }
    )
}

@Composable
private fun FoodOptionsSection(
    meal: MealJournal
) {
    DetailSection(
        title = "O que você comeu?",
        content = {
            if (meal.foodOptions.isEmpty()) {
                LelloFilledPill("Não especificado")
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimension.spacingSmall),
                    verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
                ) {
                    meal.foodOptions.forEach {
                        LelloFilledPill(it.description)
                    }
                }
            }
        }
    )
}

@Composable
private fun PortionOptionsSection(
    meal: MealJournal
) {
    DetailSection(
        title = "Qual foi o tamanho da porção?",
        content = {
            if (meal.portionOptions.isEmpty()) {
                LelloFilledPill("Não especificado")
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimension.spacingSmall),
                    verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
                ) {
                    meal.portionOptions.forEach {
                        LelloFilledPill(it.description)
                    }
                }
            }
        }
    )
}

@Composable
private fun LocationOptionsSection(
    meal: MealJournal
) {
    DetailSection(
        title = "Onde você estava?",
        content = {
            if (meal.locationOptions.isEmpty()) {
                LelloFilledPill("Não especificado")
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimension.spacingSmall),
                    verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
                ) {
                    meal.locationOptions.forEach {
                        LelloFilledPill(it.description)
                    }
                }
            }
        }
    )
}

@Composable
private fun SocialOptionsSection(
    meal: MealJournal
) {
    DetailSection(
        title = "Com quem você estava?",
        content = {
            if (meal.socialOptions.isEmpty()) {
                LelloFilledPill("Não especificado")
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimension.spacingSmall),
                    verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
                ) {
                    meal.socialOptions.forEach {
                        LelloFilledPill(it.description)
                    }
                }
            }
        }
    )
}

@Composable
private fun DetailSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimension.spacingLarge),
        verticalArrangement = Arrangement.spacedBy(Dimension.spacingMedium)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
        content()
    }
}

// region Previews

@Preview(
    name = "Default",
    group = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun DiaryMealDetailsScreenPreview_LightMode() {
    DiaryMealDetailsScreenContent(
        mealJournal = MealJournalMock.list.first(),
        onBackClick = {},
    )
}

@Preview(
    name = "Default",
    group = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun DiaryMealDetailsScreenPreview_DarkMode() {
    DiaryMealDetailsScreenContent(
        mealJournal = MealJournalMock.list.first(),
        onBackClick = {},
    )
}

// endregion Previews
