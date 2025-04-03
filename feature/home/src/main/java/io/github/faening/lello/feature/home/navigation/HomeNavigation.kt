package io.github.faening.lello.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import io.github.faening.lello.core.domain.repository.NavigationDestination
import io.github.faening.lello.feature.home.screen.HomeScreen

object HomeNavigation : NavigationDestination {
    override val route = "home_route"
    override val destination = "home_destination"

    override fun NavGraphBuilder.addDestination(
        navController: NavHostController,
        onBackClick: () -> Unit
    ) {
        composable(route = HomeNavigation.route) {
            HomeScreen(
                mainNavController = navController,
                onBackClick = onBackClick,
            )
        }
    }
}