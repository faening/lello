package io.github.faening.lello.feature.diary.mood.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.diary.mood.screen.JournalMoodHomeScreen
import io.github.faening.lello.feature.diary.mood.screen.JournalMoodStepOneScreen
import io.github.faening.lello.feature.diary.mood.screen.JournalMoodStepTwoScreen

object JournalMoodDestinations {
    const val GRAPH = "journal_mood_graph"
    const val HOME = "journal_mood_home"
    const val STEP1 = "journal_mood_step1"
    const val STEP2 = "journal_mood_step2"
}

fun NavGraphBuilder.journalMoodGraph(navController: NavHostController) {
    navigation(
        startDestination = JournalMoodDestinations.HOME,
        route = JournalMoodDestinations.GRAPH
    ) {
        composable(JournalMoodDestinations.HOME) {
            JournalMoodHomeScreen(
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(JournalMoodDestinations.STEP1) }
            )
        }

        composable(JournalMoodDestinations.STEP1) {
            JournalMoodStepOneScreen(
                onBack = { navController.popBackStack() },
                onNext = { navController.navigate(JournalMoodDestinations.STEP2) }
            )
        }

        composable(JournalMoodDestinations.STEP2) {
            JournalMoodStepTwoScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}