package io.github.faening.lello.feature.journal.meal

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.journal.meal.screen.JournalMealAppetiteScreen
import io.github.faening.lello.feature.journal.meal.screen.JournalMealDetailsScreen
import io.github.faening.lello.feature.journal.meal.screen.JournalMealScreen
import io.github.faening.lello.feature.journal.meal.screen.JournalMealSummaryScreen

object JournalMealDestinations {
    const val GRAPH = "journal_meal_graph"
    const val HOME = "journal_meal_home"
    const val APPETITE = "journal_meal_appetite"
    const val DETAILS = "journal_meal_details"
    const val SUMMARY = "journal_meal_summary"
}

fun NavGraphBuilder.journalMealGraph(navController: NavHostController) {
    navigation(
        startDestination = JournalMealDestinations.HOME,
        route = JournalMealDestinations.GRAPH
    ) {
        // Step 1: Home screen to start the meal journal.
        composable(route = JournalMealDestinations.HOME) { backStackEntry ->
            val viewModel = sharedJournalMealViewModel(navController, backStackEntry)
            JournalMealScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(JournalMealDestinations.APPETITE) },
                onOpenMealOptionSettings = {},
            )
        }

        // Step 2: Select appetite level for the meal.
        composable(route = JournalMealDestinations.APPETITE) { backStackEntry ->
            val viewModel = sharedJournalMealViewModel(navController, backStackEntry)
            JournalMealAppetiteScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(JournalMealDestinations.DETAILS) },
                onFinish = { navController.navigate(JournalMealDestinations.SUMMARY) },
                onOpenAppetiteOptionSettings = {},
            )
        }

        // Step 3: Enter meal details.
        composable(route = JournalMealDestinations.DETAILS) { backStackEntry ->
            val viewModel = sharedJournalMealViewModel(navController, backStackEntry)
            JournalMealDetailsScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onFinish = { navController.navigate(JournalMealDestinations.SUMMARY) },
                onOpenFoodOptionSettings = {},
                onOpenPortionOptionSettings = {},
                onOpenLocationOptionSettings = {},
                onOpenSocialOptionSettings = {},
            )
        }

        // Step 4: Summary of the meal journal entry.
        composable(route = JournalMealDestinations.SUMMARY) { backStackEntry ->
            val viewModel = sharedJournalMealViewModel(navController, backStackEntry)
            JournalMealSummaryScreen(
                viewModel = viewModel,
                onExit = { navController.popBackStack(JournalMealDestinations.GRAPH, inclusive = true) }
            )
        }
    }
}

@Composable
fun sharedJournalMealViewModel(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
): JournalMealViewModel {
    val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(JournalMealDestinations.GRAPH)
    }
    return hiltViewModel(parentEntry)
}