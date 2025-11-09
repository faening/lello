package io.github.faening.lello.feature.journal.medication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.navigation.NavigationTransitions
import io.github.faening.lello.core.navigation.customComposable
import io.github.faening.lello.feature.journal.medication.screen.MedicationJournalScreen
import io.github.faening.lello.feature.journal.medication.screen.MedicationJournalSkipReasonScreen
import io.github.faening.lello.feature.journal.medication.screen.MedicationJournalSummaryScreen
import io.github.faening.lello.feature.journal.settings.SettingsJournalDestinations
import io.github.faening.lello.feature.journal.settings.model.JournalOptionType

object JournalMedicationDestinations {
    const val GRAPH = "journal_medication_graph"
    const val HOME = "journal_medication_home"
    const val SKIP_REASON = "journal_medication_skip_reason"
    const val SUMMARY = "journal_medication_summary"
}

fun NavGraphBuilder.medicationJournalGraph(navController: NavHostController) {
    navigation(
        startDestination = JournalMedicationDestinations.HOME,
        route = JournalMedicationDestinations.GRAPH
    ) {

        customComposable(
            route = JournalMedicationDestinations.HOME,
            enterTransition = NavigationTransitions.fadeIn()
        ) { backStackEntry ->
            val viewModel = sharedMedicationJournalViewModel(navController, backStackEntry)

            MedicationJournalScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onRegister = {
                    // TODO: Navigate to medication registration screen
                },
                onNext = { medication, dosageIndex ->
                    viewModel.setSelectedDosage(medication, dosageIndex)
                    navController.navigate(JournalMedicationDestinations.SKIP_REASON)
                }
            )
        }

        customComposable(
            route = JournalMedicationDestinations.SKIP_REASON
        ) { backStackEntry ->
            val viewModel = sharedMedicationJournalViewModel(navController, backStackEntry)

            MedicationJournalSkipReasonScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onFinish = { navController.navigate(JournalMedicationDestinations.SUMMARY) },
                onOpenSettings = {
                    navController.navigate(
                        SettingsJournalDestinations.listRoute(
                            type = JournalOptionType.MEDICATION_SKIP_REASON,
                            scheme = MoodColor.DEFAULT
                        )
                    )
                }
            )
        }

        customComposable(
            route = JournalMedicationDestinations.SUMMARY,
            exitTransition = NavigationTransitions.fadeOut(),
            popExitTransition = NavigationTransitions.fadeOut()
        ) { backStackEntry ->
            val viewModel = sharedMedicationJournalViewModel(navController, backStackEntry)

            MedicationJournalSummaryScreen(
                viewModel = viewModel,
                onExit = {
                    navController.popBackStack(JournalMedicationDestinations.GRAPH, inclusive = true)
                }
            )
        }
    }
}

@Composable
fun sharedMedicationJournalViewModel(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
): MedicationJournalViewModel {
    val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(JournalMedicationDestinations.GRAPH)
    }
    return hiltViewModel(parentEntry)
}