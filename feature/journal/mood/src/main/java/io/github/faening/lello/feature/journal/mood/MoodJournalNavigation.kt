package io.github.faening.lello.feature.journal.mood

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigation
import io.github.faening.lello.core.navigation.NavigationTransitions
import io.github.faening.lello.core.navigation.customComposable
import io.github.faening.lello.feature.journal.mood.screen.MoodJournalDetailsScreen
import io.github.faening.lello.feature.journal.mood.screen.MoodJournalEmotionScreen
import io.github.faening.lello.feature.journal.mood.screen.MoodJournalReflectionScreen
import io.github.faening.lello.feature.journal.mood.screen.MoodJournalScreen
import io.github.faening.lello.feature.journal.mood.screen.MoodJournalSummaryScreen
import io.github.faening.lello.feature.journal.settings.SettingsJournalDestinations
import io.github.faening.lello.feature.journal.settings.model.JournalOptionType

object MoodJournalDestinations {
    const val GRAPH = "journal_mood_graph"
    const val HOME = "journal_mood_home"
    const val EMOTION = "journal_mood_emotion"
    const val DETAILS = "journal_mood_details"
    const val REFLECTION = "journal_mood_reflection"
    const val SUMMARY = "journal_mood_summary"
}

fun NavGraphBuilder.moodJournalGraph(navController: NavHostController) {
    navigation(
        startDestination = MoodJournalDestinations.HOME,
        route = MoodJournalDestinations.GRAPH
    ) {
        // Step 1: Home screen to start the mood journal.
        customComposable(
            route = MoodJournalDestinations.HOME,
            enterTransition = NavigationTransitions.fadeIn()
        ) { backStackEntry ->
            val viewModel = sharedMoodJournalViewModel(navController, backStackEntry)

            MoodJournalScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(MoodJournalDestinations.EMOTION) }
            )
        }

        // Step 2: Select an emotion to describe the user's mood.
        customComposable(
            route = MoodJournalDestinations.EMOTION
        ) { backStackEntry ->
            val viewModel = sharedMoodJournalViewModel(navController, backStackEntry)
            val moodColor by viewModel.currentMood.collectAsState()

            MoodJournalEmotionScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(MoodJournalDestinations.DETAILS) },
                onFinish = { navController.navigate(MoodJournalDestinations.SUMMARY) },
                onOpenEmotionOptionSettings = {
                    navController.navigate(
                        route = SettingsJournalDestinations.listRoute(
                            type = JournalOptionType.EMOTION,
                            scheme = moodColor
                        )
                    )
                }
            )
        }

        // Step 3: Additional details about the user's mood.
        customComposable(
            route = MoodJournalDestinations.DETAILS
        ) { backStackEntry ->
            val viewModel = sharedMoodJournalViewModel(navController, backStackEntry)
            val moodColor by viewModel.currentMood.collectAsState()

            MoodJournalDetailsScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(MoodJournalDestinations.REFLECTION) },
                onFinish = { navController.navigate(MoodJournalDestinations.SUMMARY) },
                onOpenHealthOptionSettings = {
                    navController.navigate(
                        route = SettingsJournalDestinations.listRoute(
                            type = JournalOptionType.HEALTH,
                            scheme = moodColor
                        )
                    )
                },
                onOpenClimateOptionSettings = {
                    navController.navigate(
                        route = SettingsJournalDestinations.listRoute(
                            type = JournalOptionType.CLIMATE,
                            scheme = moodColor
                        )
                    )
                },
                onOpenLocationOptionSettings = {
                    navController.navigate(
                        route = SettingsJournalDestinations.listRoute(
                            type = JournalOptionType.LOCATION,
                            scheme = moodColor
                        )
                    )
                },
                onOpenSocialOptionSettings = {
                    navController.navigate(
                        route = SettingsJournalDestinations.listRoute(
                            type = JournalOptionType.SOCIAL,
                            scheme = moodColor
                        )
                    )
                },
            )
        }

        // Step 4: Reflection screen for the user to write about their day.
        customComposable(
            route = MoodJournalDestinations.REFLECTION
        ) { backStackEntry ->
            val viewModel = sharedMoodJournalViewModel(navController, backStackEntry)
            MoodJournalReflectionScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onFinish = { navController.navigate(MoodJournalDestinations.SUMMARY) }
            )
        }

        // Step 5: Summary screen after completing the mood journal.
        customComposable(
            route = MoodJournalDestinations.SUMMARY,
            exitTransition = NavigationTransitions.fadeOut(),
            popExitTransition = NavigationTransitions.fadeOut()
        ) { backStackEntry ->
            val viewModel = sharedMoodJournalViewModel(navController, backStackEntry)
            MoodJournalSummaryScreen(
                viewModel = viewModel,
                onExit = { navController.popBackStack(MoodJournalDestinations.GRAPH, inclusive = true) }
            )
        }
    }
}

@Composable
fun sharedMoodJournalViewModel(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
): MoodJournalViewModel {
    val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(MoodJournalDestinations.GRAPH)
    }
    return hiltViewModel(parentEntry)
}