package io.github.faening.lello.feature.journal.mood.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.journal.mood.screen.JournalMoodRoute
import io.github.faening.lello.feature.journal.mood.screen.JournalMoodEmotionSelectionRoute
import io.github.faening.lello.feature.journal.mood.screen.JournalMoodStepTwoScreen

object JournalMoodDestinations {
    const val GRAPH = "journal_mood_graph"
    const val HOME = "journal_mood_home"
    const val STEP1 = "journal_mood_emotion_selection"
    const val STEP2 = "journal_mood_step2"
}

fun NavGraphBuilder.journalMoodGraph(navController: NavHostController) {
    navigation(
        startDestination = JournalMoodDestinations.HOME,
        route = JournalMoodDestinations.GRAPH
    ) {
        composable(JournalMoodDestinations.HOME) {
            JournalMoodRoute(
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(JournalMoodDestinations.STEP1) }
            )
        }

        composable(JournalMoodDestinations.STEP1) {
            JournalMoodEmotionSelectionRoute(
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(JournalMoodDestinations.STEP2) },
                onOpenRegistration = { /* go to settings */ }
            )
        }

        composable(JournalMoodDestinations.STEP2) {
            JournalMoodStepTwoScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}