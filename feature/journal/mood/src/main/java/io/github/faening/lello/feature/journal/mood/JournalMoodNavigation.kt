package io.github.faening.lello.feature.journal.mood

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.journal.mood.screen.JournalMoodScreen
import io.github.faening.lello.feature.journal.mood.screen.JournalMoodEmotionScreen
import io.github.faening.lello.feature.journal.mood.screen.JournalMoodDetailsScreen
import io.github.faening.lello.feature.journal.settings.JournalSettingsDestinations

object JournalMoodDestinations {
    const val GRAPH = "journal_mood_graph"
    const val HOME = "journal_mood_home"
    const val EMOTION = "journal_mood_emotion"
    const val DETAILS = "journal_mood_details"
}

fun NavGraphBuilder.journalMoodGraph(navController: NavHostController) {
    navigation(
        startDestination = JournalMoodDestinations.HOME,
        route = JournalMoodDestinations.GRAPH
    ) {
        composable(JournalMoodDestinations.HOME) { backStackEntry ->
            val viewModel = sharedJournalMoodViewModel(navController, backStackEntry)
            JournalMoodScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(JournalMoodDestinations.EMOTION) }
            )
        }

        composable(JournalMoodDestinations.EMOTION) { backStackEntry ->
            val viewModel = sharedJournalMoodViewModel(navController, backStackEntry)
            JournalMoodEmotionScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(JournalMoodDestinations.DETAILS) },
                onFinish = { /* conluir diário */ },
                onOpenRegistration = { navController.navigate(JournalSettingsDestinations.EMOTION_SETTINGS) }
            )
        }

        composable(JournalMoodDestinations.DETAILS) {
            JournalMoodDetailsScreen(
                onBack = { navController.popBackStack() }
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