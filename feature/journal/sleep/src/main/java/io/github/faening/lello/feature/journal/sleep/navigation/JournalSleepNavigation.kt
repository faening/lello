package io.github.faening.lello.feature.journal.sleep.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.journal.sleep.screen.JournalSleepScreen

object JournalSleepDestinations {
    const val GRAPH = "journal_sleep_graph"
    const val HOME = "journal_sleep_home"
}

fun NavGraphBuilder.journalSleepGraph(navController: NavHostController) {
    navigation(
        startDestination = JournalSleepDestinations.HOME,
        route = JournalSleepDestinations.GRAPH
    ) {
        composable(route = JournalSleepDestinations.HOME) {
            JournalSleepScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}