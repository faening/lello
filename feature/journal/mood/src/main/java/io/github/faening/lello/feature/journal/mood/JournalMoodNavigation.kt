package io.github.faening.lello.feature.journal.mood

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.journal.mood.screen.JournalMoodDetailsScreen
import io.github.faening.lello.feature.journal.mood.screen.JournalMoodEmotionScreen
import io.github.faening.lello.feature.journal.mood.screen.JournalMoodHealthScreen
import io.github.faening.lello.feature.journal.mood.screen.JournalMoodReflectionScreen
import io.github.faening.lello.feature.journal.mood.screen.JournalMoodScreen
import io.github.faening.lello.feature.journal.mood.screen.JournalMoodSummaryScreen
import io.github.faening.lello.feature.journal.settings.JournalSettingsDestinations
import io.github.faening.lello.feature.journal.settings.model.JournalOptionType

object JournalMoodDestinations {
    const val GRAPH = "journal_mood_graph"
    const val HOME = "journal_mood_home"
    const val EMOTION = "journal_mood_emotion"
    const val DETAILS = "journal_mood_details"
    const val HEALTH = "journal_mood_health"
    const val REFLECTION = "journal_mood_reflection"
    const val SUMMARY = "journal_mood_summary"
}

fun NavGraphBuilder.journalMoodGraph(navController: NavHostController) {
    navigation(
        startDestination = JournalMoodDestinations.HOME,
        route = JournalMoodDestinations.GRAPH
    ) {
        // Step 1: Home screen to start the mood journal.
        composable(JournalMoodDestinations.HOME) { backStackEntry ->
            val viewModel = sharedJournalMoodViewModel(navController, backStackEntry)
            JournalMoodScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(JournalMoodDestinations.EMOTION) }
            )
        }

        // Step 2: Select an emotion to describe the user's mood.
        composable(JournalMoodDestinations.EMOTION) { backStackEntry ->
            val viewModel = sharedJournalMoodViewModel(navController, backStackEntry)
            val mood by viewModel.currentMood.collectAsState()

            JournalMoodEmotionScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(JournalMoodDestinations.DETAILS) },
                onFinish = { navController.navigate(JournalMoodDestinations.SUMMARY) },
                onOpenEmotionOptionSettings = {
                    navController.navigate(
                        JournalSettingsDestinations.listRoute(
                            JournalOptionType.EMOTION,
                            mood.colorScheme
                        )
                    )
                }
            )
        }

        // Step 3: Additional details about the user's mood.
        composable(JournalMoodDestinations.DETAILS) { backStackEntry ->
            val viewModel = sharedJournalMoodViewModel(navController, backStackEntry)
            val mood by viewModel.currentMood.collectAsState()

            JournalMoodDetailsScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(JournalMoodDestinations.HEALTH) },
                onFinish = { navController.navigate(JournalMoodDestinations.SUMMARY) },
                onOpenClimateOptionSettings = {
                    navController.navigate(
                        JournalSettingsDestinations.listRoute(
                            JournalOptionType.CLIMATE,
                            mood.colorScheme
                        )
                    )
                },
                onOpenLocationOptionSettings = {
                    navController.navigate(
                        JournalSettingsDestinations.listRoute(
                            JournalOptionType.LOCATION,
                            mood.colorScheme
                        )
                    )
                },
                onOpenSocialOptionSettings = {
                    navController.navigate(
                        JournalSettingsDestinations.listRoute(
                            JournalOptionType.SOCIAL,
                            mood.colorScheme
                        )
                    )
                },
            )
        }

        // Step 4: Health options screen (not implemented in this example).
        composable(JournalMoodDestinations.HEALTH) { backStackEntry ->
            val viewModel = sharedJournalMoodViewModel(navController, backStackEntry)
            val mood by viewModel.currentMood.collectAsState()

             JournalMoodHealthScreen(
                 viewModel = viewModel,
                 onBack = { navController.popBackStack() },
                 onNext = { navController.navigate(JournalMoodDestinations.REFLECTION) },
                 onFinish = { navController.navigate(JournalMoodDestinations.SUMMARY) },
                 onOpenHealthOptionSettings = {
                     navController.navigate(
                         JournalSettingsDestinations.listRoute(
                             JournalOptionType.HEALTH,
                             mood.colorScheme
                         )
                     )
                }
             )
        }

        // Step 5: Reflection screen for the user to write about their day.
        composable(JournalMoodDestinations.REFLECTION) { backStackEntry ->
            val viewModel = sharedJournalMoodViewModel(navController, backStackEntry)
            JournalMoodReflectionScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onFinish = { navController.navigate(JournalMoodDestinations.SUMMARY) }
            )
        }

        // Step 6: Summary screen after completing the mood journal.
        composable(JournalMoodDestinations.SUMMARY) { backStackEntry ->
            val viewModel = sharedJournalMoodViewModel(navController, backStackEntry)
            JournalMoodSummaryScreen(
                viewModel = viewModel,
                onExit = { navController.popBackStack(JournalMoodDestinations.GRAPH, inclusive = true) }
            )
        }
    }
}

@Composable
fun sharedJournalMoodViewModel(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
): JournalMoodViewModel {
    val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(JournalMoodDestinations.GRAPH)
    }
    return hiltViewModel(parentEntry)
}