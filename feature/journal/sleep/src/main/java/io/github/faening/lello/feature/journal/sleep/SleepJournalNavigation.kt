package io.github.faening.lello.feature.journal.sleep

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.feature.journal.settings.JournalSettingsDestinations
import io.github.faening.lello.feature.journal.settings.model.JournalOptionType
import io.github.faening.lello.feature.journal.sleep.screen.SleepJournalMoodScreen
import io.github.faening.lello.feature.journal.sleep.screen.SleepJournalScreen

object SleepJournalDestinations {
    const val GRAPH = "journal_sleep_graph"
    const val HOME = "journal_sleep_home"
    const val MOOD = "journal_sleep_mood"

    const val SUMMARY = "journal_sleep_summary"
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
                onNext = { navController.navigate(SleepJournalDestinations.MOOD) },
            )
        }

        // Step 2: Mood screen to record the mood during sleep.
        composable(SleepJournalDestinations.MOOD) { backStackEntry ->
            val viewModel = sharedSleepJournalViewModel(navController, backStackEntry)
            SleepJournalMoodScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = {  },
                onFinish = { navController.navigate(SleepJournalDestinations.SUMMARY) },
                onOpenSleepSensationOptionSettings = {
                    navController.navigate(
                        JournalSettingsDestinations.listRoute(
                            type = JournalOptionType.SLEEP_SENSATION,
                            scheme = LelloColorScheme.DEFAULT
                        )
                    )
                }
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