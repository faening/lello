package io.github.faening.lello.feature.medication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.medication.screen.MedicationRegisterActiveIngredientScreen
import io.github.faening.lello.feature.medication.screen.MedicationRegisterDosageScreen
import io.github.faening.lello.feature.medication.screen.MedicationRegisterFormScreen
import io.github.faening.lello.feature.medication.screen.MedicationRegisterFrequencyScreen
import io.github.faening.lello.feature.medication.screen.MedicationScreen

object MedicationDestinations {
    const val GRAPH = "medication_graph"
    const val HOME = "medication_home"
    const val MEDICATION_REGISTER_ACTIVE_INGREDIENT = "medication_register_active_ingredient"
    const val MEDICATION_REGISTER_FORM = "medication_register_form"
    const val MEDICATION_REGISTER_FREQUENCY = "medication_register_frequency"
    const val MEDICATION_REGISTER_DOSAGE = "medication_register_dosage"
}

fun NavGraphBuilder.medicationGraph(navController: NavHostController) {
    navigation(
        startDestination = MedicationDestinations.HOME,
        route = MedicationDestinations.GRAPH
    ) {
        // Home Screen
        composable(MedicationDestinations.HOME) { backStackEntry ->
            val viewModel = sharedMedicationViewModel(navController, backStackEntry)

            MedicationScreen(
                 viewModel = viewModel,
                 onRegisterMedication = {
                     navController.navigate(MedicationDestinations.MEDICATION_REGISTER_ACTIVE_INGREDIENT)
                 }
             )
        }

        // Step 1: Select medication (active ingredient)
        composable(MedicationDestinations.MEDICATION_REGISTER_ACTIVE_INGREDIENT) { backStackEntry ->
            val viewModel = sharedMedicationViewModel(navController, backStackEntry)

            MedicationRegisterActiveIngredientScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(MedicationDestinations.MEDICATION_REGISTER_FORM) }
            )
        }

        // Step 2: Select medication presentation (dosage form)
        composable(MedicationDestinations.MEDICATION_REGISTER_FORM) { backStackEntry ->
            val viewModel = sharedMedicationViewModel(navController, backStackEntry)

            MedicationRegisterFormScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(MedicationDestinations.MEDICATION_REGISTER_FREQUENCY) }
            )
        }

        // Step 3: Select dosage list (dosage quantity and frequency)
        composable(MedicationDestinations.MEDICATION_REGISTER_FREQUENCY) { backStackEntry ->
            val viewModel = sharedMedicationViewModel(navController, backStackEntry)

            MedicationRegisterFrequencyScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onRegisterDosage = { navController.navigate(MedicationDestinations.MEDICATION_REGISTER_DOSAGE) },
                onSaveSuccess = {
                    navController.navigate(MedicationDestinations.HOME) {
                        popUpTo(MedicationDestinations.GRAPH) { inclusive = false }
                    }
                }
            )
        }

        // Step 4: Select dosage unit (dosage unit option)
        composable(MedicationDestinations.MEDICATION_REGISTER_DOSAGE) { backStackEntry ->
            val viewModel = sharedMedicationViewModel(navController, backStackEntry)

            MedicationRegisterDosageScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
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