package io.github.faening.lello.feature.medication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.medication.screen.MedicationDosageFormScreen
import io.github.faening.lello.feature.medication.screen.MedicationDosageFrequencyScreen
import io.github.faening.lello.feature.medication.screen.MedicationDosageUnitScreen
import io.github.faening.lello.feature.medication.screen.MedicationScreen

object MedicationDestinations {
    const val GRAPH = "medication_graph"
    const val HOME = "medication_home"
    const val DOSAGE_UNIT = "medication_dosage_unit"
    const val DOSAGE_FORM = "medication_dosage_form"
    const val DOSAGE_FREQUENCY = "medication_dosage_frequency"
}

fun NavGraphBuilder.medicationGraph(navController: NavHostController) {
    navigation(
        startDestination = MedicationDestinations.HOME,
        route = MedicationDestinations.GRAPH
    ) {
        // Step 1: Select medication (active ingredient)
        composable(MedicationDestinations.HOME) { backStackEntry ->
            val viewModel = sharedMedicationViewModel(navController, backStackEntry)

            MedicationScreen(
                viewModel = viewModel,
                onNext = { navController.navigate(MedicationDestinations.DOSAGE_UNIT) }
            )
        }

        // Step 2: Select dosage unit (dosage unit option)
        composable(MedicationDestinations.DOSAGE_UNIT) { backStackEntry ->
            val viewModel = sharedMedicationViewModel(navController, backStackEntry)

            MedicationDosageUnitScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(MedicationDestinations.DOSAGE_FORM) }
            )
        }

        // Step 3: Select Dosage Screen (dosage form option)
        composable(MedicationDestinations.DOSAGE_FORM) { backStackEntry ->
            val viewModel = sharedMedicationViewModel(navController, backStackEntry)

             MedicationDosageFormScreen(
                 viewModel = viewModel,
                 onBack = { navController.popBackStack() },
                 onNext = { navController.navigate(MedicationDestinations.DOSAGE_FREQUENCY) }
             )
        }

        // Step 4: Select Type Screen (medication type)
        composable(MedicationDestinations.DOSAGE_FREQUENCY) { backStackEntry ->
            val viewModel = sharedMedicationViewModel(navController, backStackEntry)

             MedicationDosageFrequencyScreen(
                 viewModel = viewModel,
                 onBack = { navController.popBackStack() },
                 onFinish = { navController.popBackStack(MedicationDestinations.HOME, inclusive = false) }
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