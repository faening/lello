package io.github.faening.lello.feature.profile

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.faening.lello.feature.profile.screen.ProfileScreen

object ProfileDestinations {
    const val GRAPH = "profile_graph"
    const val HOME = "profile_home"
}

fun NavGraphBuilder.profileGraph(navController: NavHostController) {
    navigation(
        startDestination = ProfileDestinations.HOME,
        route = ProfileDestinations.GRAPH
    ) {
        composable(route = ProfileDestinations.HOME) {
            ProfileScreen(
                onBack = { navController.popBackStack() }
            )
        }
    }
}
