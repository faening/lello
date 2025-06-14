package io.github.faening.lello.feature.journal.meal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.feature.journal.meal.screen.MealJournalAppetiteScreen
import io.github.faening.lello.feature.journal.meal.screen.MealJournalDetailsScreen
import io.github.faening.lello.feature.journal.meal.screen.MealJournalScreen
import io.github.faening.lello.feature.journal.meal.screen.MealJournalSummaryScreen
import io.github.faening.lello.feature.journal.settings.SettingsJournalDestinations
import io.github.faening.lello.feature.journal.settings.model.JournalOptionType

object JournalMealDestinations {
    const val GRAPH = "journal_meal_graph"
    const val HOME = "journal_meal_home"
    const val APPETITE = "journal_meal_appetite"
    const val DETAILS = "journal_meal_details"
    const val SUMMARY = "journal_meal_summary"
}

fun NavGraphBuilder.mealJournalGraph(navController: NavHostController) {
    navigation(
        startDestination = JournalMealDestinations.HOME,
        route = JournalMealDestinations.GRAPH
    ) {
        // Step 1: Home screen to start the meal journal.
        composable(JournalMealDestinations.HOME) { backStackEntry ->
            val viewModel = sharedMealJournalViewModel(navController, backStackEntry)
            MealJournalScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(JournalMealDestinations.APPETITE) },
                onOpenMealOptionSettings = {
                    navController.navigate(
                        SettingsJournalDestinations.listRoute(
                            type = JournalOptionType.MEAL,
                            scheme = LelloColorScheme.DEFAULT
                        )
                    )
                },
            )
        }

        // Step 2: Select appetite level for the meal.
        composable(JournalMealDestinations.APPETITE) { backStackEntry ->
            val viewModel = sharedMealJournalViewModel(navController, backStackEntry)
            MealJournalAppetiteScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(JournalMealDestinations.DETAILS) },
                onFinish = { navController.navigate(JournalMealDestinations.SUMMARY) },
                onOpenAppetiteOptionSettings = {
                    navController.navigate(
                        SettingsJournalDestinations.listRoute(
                            type = JournalOptionType.APPETITE,
                            scheme = LelloColorScheme.DEFAULT
                        )
                    )
                },
            )
        }

        // Step 3: Enter meal details.
        composable(JournalMealDestinations.DETAILS) { backStackEntry ->
            val viewModel = sharedMealJournalViewModel(navController, backStackEntry)
            MealJournalDetailsScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onFinish = { navController.navigate(JournalMealDestinations.SUMMARY) },
                onOpenFoodOptionSettings = {
                    navController.navigate(
                        SettingsJournalDestinations.listRoute(
                            type = JournalOptionType.FOOD,
                            scheme = LelloColorScheme.DEFAULT
                        )
                    )
                },
                onOpenPortionOptionSettings = {
                    navController.navigate(
                        SettingsJournalDestinations.listRoute(
                            type = JournalOptionType.PORTION,
                            scheme = LelloColorScheme.DEFAULT
                        )
                    )
                },
                onOpenLocationOptionSettings = {
                    navController.navigate(
                        SettingsJournalDestinations.listRoute(
                            type = JournalOptionType.LOCATION,
                            scheme = LelloColorScheme.DEFAULT
                        )
                    )
                },
                onOpenSocialOptionSettings = {
                    navController.navigate(
                        SettingsJournalDestinations.listRoute(
                            type = JournalOptionType.SOCIAL,
                            scheme = LelloColorScheme.DEFAULT
                        )
                    )
                },
            )
        }

        // Step 4: Summary of the meal journal entry.
        composable(JournalMealDestinations.SUMMARY) { backStackEntry ->
            val viewModel = sharedMealJournalViewModel(navController, backStackEntry)
            MealJournalSummaryScreen(
                viewModel = viewModel,
                onExit = { navController.popBackStack(JournalMealDestinations.GRAPH, inclusive = true) }
            )
        }
    }
}

@Composable
fun sharedMealJournalViewModel(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
): MealJournalViewModel {
    val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(JournalMealDestinations.GRAPH)
    }
    return hiltViewModel(parentEntry)
}