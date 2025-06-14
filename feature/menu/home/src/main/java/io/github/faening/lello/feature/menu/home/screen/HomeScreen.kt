package io.github.faening.lello.feature.menu.home.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.faening.lello.core.designsystem.component.JournalCategoryCard
import io.github.faening.lello.core.designsystem.component.JournalCategoryCardConfig
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.journal.JournalCategory
import io.github.faening.lello.feature.journal.meal.JournalMealDestinations
import io.github.faening.lello.feature.journal.medication.navigation.JournalMedicationDestinations
import io.github.faening.lello.feature.journal.mood.MoodJournalDestinations
import io.github.faening.lello.feature.journal.sleep.SleepJournalDestinations
import io.github.faening.lello.feature.menu.home.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onNavigateToModule: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val journalCategories by viewModel.journalCategories.collectAsState()

    Scaffold(
        topBar = {
            HomeScreenTopAppBar()
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            JournalContent(
                journalCategories = journalCategories,
                onNavigateToModule = onNavigateToModule
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeScreenTopAppBar() {
    val title = "Hoje"
    LelloTopAppBar(
        title = TopAppBarTitle(text = title)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun JournalContent(
    journalCategories: List<JournalCategory> = emptyList(),
    onNavigateToModule: (String) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = "Meus diários",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if (journalCategories.isEmpty()) {
            Text("Carregando...")
        } else {
            journalCategories.forEach { category ->
                JournalCategoryCard(
                    title = category.name,
                    description = category.shortDescription,
                    configuration = JournalCategoryCardConfig.fromName(category.name),
                    onClick = {
                        when (category.name) {
                            "Diário de Humor" -> onNavigateToModule(MoodJournalDestinations.GRAPH)
                            "Diário de Sono" -> onNavigateToModule(SleepJournalDestinations.GRAPH)
                            "Diário de Medicamentos" -> onNavigateToModule(JournalMedicationDestinations.GRAPH)
                            "Diário de Alimentação" -> onNavigateToModule(JournalMealDestinations.GRAPH)

                        }
                    }
                )

                Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("HomeScren - Light Theme")
@Composable
private fun PreviewHomeScreen() {
    LelloTheme(darkTheme = false) {
        HomeScreen(
            onNavigateToModule = {},
        )
    }
}