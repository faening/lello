package io.github.faening.lello.feature.journal.mood.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.journal.mood.JournalMoodViewModel
import io.github.faening.lello.feature.journal.mood.screen.JournalMoodRoute
import io.github.faening.lello.feature.journal.mood.screen.JournalMoodEmotionScreen
import io.github.faening.lello.feature.journal.mood.screen.JournalMoodStepTwoScreen
import io.github.faening.lello.feature.journal.settings.JournalSettingsDestinations

object JournalMoodDestinations {
    const val GRAPH = "journal_mood_graph"
    const val HOME = "journal_mood_home"
    const val STEP1 = "journal_mood_emotion"
    const val STEP2 = "journal_mood_step2"
}

fun NavGraphBuilder.journalMoodGraph(navController: NavHostController) {
    navigation(
        startDestination = JournalMoodDestinations.HOME,
        route = JournalMoodDestinations.GRAPH
    ) {
        composable(JournalMoodDestinations.HOME) { backStackEntry ->
            val viewModel = sharedJournalMoodViewModel(navController, backStackEntry)
            JournalMoodRoute(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(JournalMoodDestinations.STEP1) }
            )
        }

        composable(JournalMoodDestinations.STEP1) { backStackEntry ->
            val viewModel = sharedJournalMoodViewModel(navController, backStackEntry)
            JournalMoodEmotionScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(JournalMoodDestinations.STEP2) },
                onFinish = { /* conluir diário */ },
                onOpenRegistration = { navController.navigate(JournalSettingsDestinations.EMOTION_SETTINGS) }
            )
        }

        composable(JournalMoodDestinations.STEP2) {
            JournalMoodStepTwoScreen(
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