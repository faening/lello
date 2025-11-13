package io.github.faening.lello.feature.diary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.core.navigation.customComposable
import io.github.faening.lello.feature.diary.screen.DiaryMealDetailsScreen
import io.github.faening.lello.feature.diary.screen.DiaryMedicationDetailsScreen
import io.github.faening.lello.feature.diary.screen.DiaryMoodDetailsScreen
import io.github.faening.lello.feature.diary.screen.DiaryScreen
import io.github.faening.lello.feature.diary.screen.DiarySleepDetailsScreen

object DiaryDestinations {
    const val GRAPH = "diary_graph"
    const val HOME = "diary_home"
    const val MEAL_DETAILS = "diary_meal_details"
    const val MEDICATION_DETAILS = "diary_medication_details"
    const val MOOD_DETAILS = "diary_mood_details"
    const val SLEEP_DETAILS = "diary_sleep_details"
}

fun NavGraphBuilder.diaryGraph(navController: NavHostController) {
    navigation(
        startDestination = DiaryDestinations.HOME,
        route = DiaryDestinations.GRAPH
    ) {
        customComposable(
            route = DiaryDestinations.HOME
        ) { backStackEntry ->
            val viewModel = sharedDiaryViewModel(navController, backStackEntry)

            DiaryScreen(
                viewModel = viewModel,
                onMealJournalClick = { journalId ->
                    viewModel.setSelectedMealJournal(journalId)
                    navController.navigate(DiaryDestinations.MEAL_DETAILS)
                },
                onMedicationJournalClick = { journalId ->
                    viewModel.setSelectedMedicationJournal(journalId)
                    navController.navigate(DiaryDestinations.MEDICATION_DETAILS)
                },
                onMoodJournalClick = { journalId ->
                    viewModel.setSelectedMoodJournal(journalId)
                    navController.navigate(DiaryDestinations.MOOD_DETAILS)
                },
                onSleepJournalClick = { journalId ->
                    viewModel.setSelectedSleepJournal(journalId)
                    navController.navigate(DiaryDestinations.SLEEP_DETAILS)
                }
            )
        }

        customComposable(
            route = DiaryDestinations.MEAL_DETAILS
        ) { backStackEntry ->
            val viewModel = sharedDiaryViewModel(navController, backStackEntry)

            DiaryMealDetailsScreen(
                viewModel = viewModel,
                onBackClick = { navController.navigateUp() }
            )
        }

        customComposable(
            route = DiaryDestinations.MEDICATION_DETAILS
        ) { backStackEntry ->
            val viewModel = sharedDiaryViewModel(navController, backStackEntry)

            DiaryMedicationDetailsScreen(
                viewModel = viewModel,
                onBackClick = { navController.navigateUp() }
            )
        }

        customComposable(
            route = DiaryDestinations.MOOD_DETAILS
        ) { backStackEntry ->
            val viewModel = sharedDiaryViewModel(navController, backStackEntry)

            DiaryMoodDetailsScreen(
                viewModel = viewModel,
                onBackClick = { navController.navigateUp() }
            )
        }

        customComposable(
            route = DiaryDestinations.SLEEP_DETAILS
        ) { backStackEntry ->
            val viewModel = sharedDiaryViewModel(navController, backStackEntry)

            DiarySleepDetailsScreen(
                viewModel = viewModel,
                onBackClick = { navController.navigateUp() }
            )
        }
    }
}

@Composable
fun sharedDiaryViewModel(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
): DiaryViewModel {
    val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(DiaryDestinations.GRAPH)
    }
    return hiltViewModel(parentEntry)
}