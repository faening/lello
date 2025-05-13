package io.github.faening.lello.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.github.faening.lello.diary.sleep.screen.navigation.journalSleepGraph
import io.github.faening.lello.feature.diary.meal.navigation.journalMealGraph
import io.github.faening.lello.feature.diary.medication.navigation.journalMedicationGraph
import io.github.faening.lello.feature.diary.mood.navigation.journalMoodGraph
import io.github.faening.lello.feature.menu.achievement.navigation.achievementGraph
import io.github.faening.lello.feature.menu.home.navigation.HomeDestinations
import io.github.faening.lello.feature.menu.home.navigation.homeGraph
import io.github.faening.lello.feature.menu.profile.navigation.profileGraph

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
        achievementGraph(navController = navController)
        profileGraph(navController = navController)

        // Journals
        journalMealGraph(navController = navController)
        journalMedicationGraph(navController = navController)
        journalMoodGraph(navController = navController)
        journalSleepGraph(navController = navController)
    }
}