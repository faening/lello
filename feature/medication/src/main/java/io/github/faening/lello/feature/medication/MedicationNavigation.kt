package io.github.faening.lello.feature.medication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.medication.screen.MedicationDosageScreen
import io.github.faening.lello.feature.medication.screen.MedicationScreen
import io.github.faening.lello.feature.medication.screen.MedicationSelectionScreen
import io.github.faening.lello.feature.medication.screen.MedicationTypeScreen

object MedicationDestinations {
    const val GRAPH = "medication_graph"
    const val HOME = "medication_home"
    const val SELECT_MEDICATION = "medication_select_medication"
    const val SELECT_TYPE = "medication_select_type"
    const val SELECT_DOSAGE = "medication_select_dosage"
}

fun NavGraphBuilder.medicationGraph(navController: NavHostController) {
    navigation(
        startDestination = MedicationDestinations.HOME,
        route = MedicationDestinations.GRAPH
    ) {
        // Step 1: Medication Home Screen
        composable(MedicationDestinations.HOME) { backStackEntry ->
            val viewModel = sharedMedicationViewModel(navController, backStackEntry)
            MedicationScreen(
                viewModel = viewModel,
                onNext = { navController.navigate(MedicationDestinations.SELECT_MEDICATION) }
            )
        }

        // Step 2: Select Medication Screen
        composable(MedicationDestinations.SELECT_MEDICATION) { backStackEntry ->
            val viewModel = sharedMedicationViewModel(navController, backStackEntry)

            MedicationSelectionScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(MedicationDestinations.SELECT_TYPE) }
            )
        }

        // Step 3: Select Medication Type Screen
        composable(MedicationDestinations.SELECT_TYPE) { backStackEntry ->
            val viewModel = sharedMedicationViewModel(navController, backStackEntry)

             MedicationTypeScreen(
                 viewModel = viewModel,
                 onBack = { navController.popBackStack() },
                 onNext = { navController.navigate(MedicationDestinations.SELECT_DOSAGE) }
             )
        }

        // Step 4: Select Dosage Screen
        composable(MedicationDestinations.SELECT_DOSAGE) { backStackEntry ->
            val viewModel = sharedMedicationViewModel(navController, backStackEntry)

             MedicationDosageScreen(
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