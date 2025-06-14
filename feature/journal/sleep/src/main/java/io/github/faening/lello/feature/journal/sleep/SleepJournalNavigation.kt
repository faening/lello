package io.github.faening.lello.feature.journal.sleep

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.journal.sleep.screen.SleepJournalScreen

object SleepJournalDestinations {
    const val GRAPH = "journal_sleep_graph"
    const val HOME = "journal_sleep_home"
}

fun NavGraphBuilder.sleepJournalGraph(navController: NavHostController) {
    navigation(
        startDestination = SleepJournalDestinations.HOME,
        route = SleepJournalDestinations.GRAPH
    ) {
        // Step 1: Home screen to start the sleep journal.
        composable(SleepJournalDestinations.HOME) { backStackEntry ->
            val viewModel = sharedSleepJournalViewModel(navController, backStackEntry)
            SleepJournalScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = {  }
            )
        }





    }
}

@Composable
fun sharedSleepJournalViewModel(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
): SleepJournalViewModel {
    val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(SleepJournalDestinations.GRAPH)
    }
    return hiltViewModel(parentEntry)
}