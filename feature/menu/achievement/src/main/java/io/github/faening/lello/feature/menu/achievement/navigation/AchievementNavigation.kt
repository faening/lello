package io.github.faening.lello.feature.menu.achievement.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.faening.lello.feature.menu.achievement.screen.AchievementScreen

const val ACHIEVEMENT_GRAPH = "achievement"
const val ACHIEVEMENT_MAIN_ROUTE = "achievement_main"

fun NavGraphBuilder.achievementGraph(navController: NavController) {

    composable(route = ACHIEVEMENT_MAIN_ROUTE) {
        AchievementScreen()
    }
}
