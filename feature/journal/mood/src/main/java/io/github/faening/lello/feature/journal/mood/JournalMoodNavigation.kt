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
import io.github.faening.lello.feature.journal.mood.screen.JournalMoodScreen
import io.github.faening.lello.feature.journal.settings.JournalSettingsDestinations

object JournalMoodDestinations {
    const val GRAPH = "journal_mood_graph"
    const val HOME = "journal_mood_home"
    const val EMOTION = "journal_mood_emotion"
    const val DETAILS = "journal_mood_details"
    const val HEALTH = "journal_mood_health"
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
                onFinish = { /* conluir diário */ },
                onOpenEmotionOptionSettings = {
                    navController.navigate(
                        JournalSettingsDestinations.EMOTION_SETTINGS.replace(
                            oldValue = "{colorScheme}",
                            newValue = mood.colorScheme.name
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
                onFinish = { /* concluir diário */ },
                onOpenClimateOptionSettings = {
                    navController.navigate(
                        JournalSettingsDestinations.CLIMATE_SETTINGS.replace(
                            oldValue = "{colorScheme}",
                            newValue = mood.colorScheme.name
                        )
                    )
                },
                onOpenLocationOptionSettings = {
                    navController.navigate(
                        JournalSettingsDestinations.LOCATION_SETTINGS.replace(
                            oldValue = "{colorScheme}",
                            newValue = mood.colorScheme.name
                        )
                    )
                },
                onOpenSocialOptionSettings = {
                    navController.navigate(
                        JournalSettingsDestinations.SOCIAL_SETTINGS.replace(
                            oldValue = "{colorScheme}",
                            newValue = mood.colorScheme.name
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
                 onNext = { /* próximo passo */ },
                 onFinish = { /* concluir diário */ },
                 onOpenHealthOptionSettings = {
                     navController.navigate(
                         JournalSettingsDestinations.HEALTH_SETTINGS.replace(
                             oldValue = "{colorScheme}",
                             newValue = mood.colorScheme.name
                         )
                     )
                 }
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