package io.github.faening.lello.feature.medication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.navArgument
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

    fun medicationRegisterDosageRoute(medicationId: Long? = null, dosageIndex: Int? = null): String {
        return if (medicationId != null && dosageIndex != null) {
            "${MEDICATION_REGISTER_DOSAGE}?medicationId=$medicationId&dosageIndex=$dosageIndex"
        } else {
            MEDICATION_REGISTER_DOSAGE
        }
    }
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
                 onRegister = {
                     navController.navigate(MedicationDestinations.MEDICATION_REGISTER_ACTIVE_INGREDIENT)
                 },
                onEditDosage = { medication, dosageIndex ->
                    medication.id?.let { id ->
                        navController.navigate(
                            MedicationDestinations.medicationRegisterDosageRoute(id, dosageIndex)
                        )
                    }
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
        // MedicationNavigation.kt

        composable(
            route = "${MedicationDestinations.MEDICATION_REGISTER_DOSAGE}?medicationId={medicationId}&dosageIndex={dosageIndex}",
            arguments = listOf(
                navArgument("medicationId") {
                    type = NavType.LongType
                    defaultValue = -1L
                },
                navArgument("dosageIndex") {
                    type = NavType.IntType
                    defaultValue = -1
                }
            )
        ) { backStackEntry ->
            val viewModel = sharedMedicationViewModel(navController, backStackEntry)
            val medicationId = backStackEntry.arguments?.getLong("medicationId") ?: -1L
            val dosageIndex = backStackEntry.arguments?.getInt("dosageIndex") ?: -1

            // Carrega os dados da dosagem quando em modo de edição
            if (medicationId > 0 && dosageIndex >= 0) {
                val medication = viewModel.medications.value.find { it.id == medicationId }
                medication?.let {
                    viewModel.startEditingDosage(it, dosageIndex)
                }
            }

            MedicationRegisterDosageScreen(
                viewModel = viewModel,
                isEditMode = medicationId > 0 && dosageIndex >= 0,
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