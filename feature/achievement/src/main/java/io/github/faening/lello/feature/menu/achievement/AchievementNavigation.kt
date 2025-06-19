package io.github.faening.lello.feature.menu.achievement

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.faening.lello.feature.menu.achievement.screen.AchievementScreen

object AchievementDestinations {
    const val GRAPH = "achievement_graph"
    const val HOME = "achievement_home"
    const val STORE = "achievement_store"
    const val INVENTORY = "achievement_inventory"
}

fun NavGraphBuilder.achievementGraph(navController: NavHostController) {
    navigation(
        startDestination = AchievementDestinations.HOME,
        route = AchievementDestinations.GRAPH
    ) {
        composable(route = AchievementDestinations.HOME) { backStackEntry ->
            val viewModel = sharedAchievementViewModel(navController, backStackEntry)

            AchievementScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }


    }
}

@Composable
fun sharedAchievementViewModel(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
): AchievementViewModel {
    val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(AchievementDestinations.GRAPH)
    }
    return hiltViewModel(parentEntry)
}