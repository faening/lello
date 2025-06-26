package io.github.faening.lello.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.github.faening.lello.feature.journal.meal.mealJournalGraph
import io.github.faening.lello.feature.journal.medication.navigation.journalMedicationGraph
import io.github.faening.lello.feature.journal.mood.moodJournalGraph
import io.github.faening.lello.feature.journal.settings.settingsJournalGraph
import io.github.faening.lello.feature.journal.sleep.sleepJournalGraph
import io.github.faening.lello.feature.achievement.achievementGraph
import io.github.faening.lello.feature.diary.diaryGraph
import io.github.faening.lello.feature.home.HomeDestinations
import io.github.faening.lello.feature.home.homeGraph
import io.github.faening.lello.feature.medication.medicationGraph
import io.github.faening.lello.feature.profile.profileGraph

@Composable
fun LelloNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestinations.GRAPH,
        modifier = modifier
    ) {
        // Menu
        homeGraph(navController = navController)
        diaryGraph(navController = navController)
        achievementGraph(navController = navController)
        medicationGraph(navController = navController)
        profileGraph(navController = navController)

        // Journals
        moodJournalGraph(navController = navController)
        mealJournalGraph(navController = navController)
        sleepJournalGraph(navController = navController)
        journalMedicationGraph(navController = navController)
        settingsJournalGraph(navController = navController)
    }
}