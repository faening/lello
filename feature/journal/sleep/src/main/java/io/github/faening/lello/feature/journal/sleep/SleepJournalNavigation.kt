package io.github.faening.lello.feature.journal.sleep

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.feature.journal.settings.SettingsJournalDestinations
import io.github.faening.lello.feature.journal.settings.model.JournalOptionType
import io.github.faening.lello.feature.journal.sleep.screen.SleepJournalDetailsScreen
import io.github.faening.lello.feature.journal.sleep.screen.SleepJournalMoodScreen
import io.github.faening.lello.feature.journal.sleep.screen.SleepJournalScreen
import io.github.faening.lello.feature.journal.sleep.screen.SleepJournalSummaryScreen

object SleepJournalDestinations {
    const val GRAPH = "journal_sleep_graph"
    const val HOME = "journal_sleep_home"
    const val MOOD = "journal_sleep_mood"
    const val DETAILS = "journal_sleep_details"
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
                onNext = { navController.navigate(SleepJournalDestinations.DETAILS) },
                onFinish = { navController.navigate(SleepJournalDestinations.SUMMARY) },
                onOpenSleepSensationOptionSettings = {
                    navController.navigate(
                        SettingsJournalDestinations.listRoute(
                            type = JournalOptionType.SLEEP_SENSATION,
                            scheme = MoodColor.DEFAULT
                        )
                    )
                }
            )
        }

        // Step 3: Details screen to provide more information about the sleep journal entry.
        composable(SleepJournalDestinations.DETAILS) { backStackEntry ->
            val viewModel = sharedSleepJournalViewModel(navController, backStackEntry)
            SleepJournalDetailsScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onFinish = { navController.navigate(SleepJournalDestinations.SUMMARY) },
                onOpenSleepQualityOptionSettings = {
                    navController.navigate(
                        SettingsJournalDestinations.listRoute(
                            type = JournalOptionType.SLEEP_QUALITY,
                            scheme = MoodColor.DEFAULT
                        )
                    )
                },
                onOpenSleepActivityOptionSettings = {
                    navController.navigate(
                        SettingsJournalDestinations.listRoute(
                            type = JournalOptionType.SLEEP_ACTIVITY,
                            scheme = MoodColor.DEFAULT
                        )
                    )
                },
                onOpenLocationOptionSettings = {
                    navController.navigate(
                        SettingsJournalDestinations.listRoute(
                            JournalOptionType.LOCATION,
                            scheme = MoodColor.DEFAULT
                        )
                    )
                }
            )
        }

        // Step 4: Summary screen to review the sleep journal entry.
        composable(SleepJournalDestinations.SUMMARY) { backStackEntry ->
            val viewModel = sharedSleepJournalViewModel(navController, backStackEntry)
            SleepJournalSummaryScreen(
                viewModel = viewModel,
                onExit = { navController.popBackStack(SleepJournalDestinations.GRAPH, inclusive = true) }
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