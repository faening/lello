package io.github.faening.lello.feature.menu.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.faening.lello.feature.menu.home.screen.HomeScreen

object HomeDestinations {
    const val GRAPH = "home_graph"
    const val HOME = "home"
}

fun NavGraphBuilder.homeGraph(navController: NavHostController) {
    navigation(
        startDestination = HomeDestinations.HOME,
        route = HomeDestinations.GRAPH
    ) {
        composable(route = HomeDestinations.HOME) {
            HomeScreen(
                onNavigateToModule = { moduleRoute ->
                    navController.navigate(moduleRoute)
                }
            )
        }
    }
}