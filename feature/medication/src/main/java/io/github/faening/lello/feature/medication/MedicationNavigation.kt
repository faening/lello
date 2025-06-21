package io.github.faening.lello.feature.medication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.medication.screen.MedicationScreen

object MedicationDestinations {
    const val GRAPH = "medication_graph"
    const val HOME = "medication_home"
}

fun NavGraphBuilder.medicationGraph(navController: NavHostController) {
    navigation(
        startDestination = MedicationDestinations.HOME,
        route = MedicationDestinations.GRAPH
    ) {
        composable(MedicationDestinations.HOME) { backStackEntry ->
            val viewModel = sharedMedicationViewModel(navController, backStackEntry)
            MedicationScreen(
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun sharedMedicationViewModel(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
): MedicationViewModel {
    val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(MedicationDestinations.GRAPH)
    }
    return hiltViewModel(parentEntry)
}