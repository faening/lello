package io.github.faening.lello.feature.menu.achievement.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.faening.lello.feature.menu.achievement.screen.AchievementScreen

object AchievementDestinations {
    const val GRAPH = "achievement_graph"
    const val HOME = "achievement_home"
}

fun NavGraphBuilder.achievementGraph(navController: NavHostController) {
    navigation(
        startDestination = AchievementDestinations.HOME,
        route = AchievementDestinations.GRAPH
    ) {
        composable(route = AchievementDestinations.HOME) {
            AchievementScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
