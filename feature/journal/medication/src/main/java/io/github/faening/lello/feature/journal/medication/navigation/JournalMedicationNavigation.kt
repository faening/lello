package io.github.faening.lello.feature.journal.medication.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.journal.medication.screen.JournalMedicationScreen

object JournalMedicationDestinations {
    const val GRAPH = "journal_medication_graph"
    const val HOME = "journal_medication_home"
}

fun NavGraphBuilder.journalMedicationGraph(navController: NavHostController) {
    navigation(
        startDestination = JournalMedicationDestinations.HOME,
        route = JournalMedicationDestinations.GRAPH
    ) {
        composable(route = JournalMedicationDestinations.HOME) {
            JournalMedicationScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}