package io.github.faening.lello.feature.journal.meal.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.journal.meal.screen.JournalMealScreen

object JournalMealDestinations {
    const val GRAPH = "journal_meal_graph"
    const val HOME = "journal_meal_home"
}

fun NavGraphBuilder.journalMealGraph(navController: NavHostController) {
    navigation(
        startDestination = JournalMealDestinations.HOME,
        route = JournalMealDestinations.GRAPH
    ) {
        composable(route = JournalMealDestinations.HOME) {
            JournalMealScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}