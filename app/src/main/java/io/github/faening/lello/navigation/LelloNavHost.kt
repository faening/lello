package io.github.faening.lello.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.github.faening.lello.feature.home.navigation.HomeRoutes
import io.github.faening.lello.feature.home.navigation.homeGraph
import io.github.faening.lello.feature.profile.navigation.profileGraph

@Composable
fun LelloNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeRoutes.HOME_MAIN_ROUTE,
        modifier = modifier
    ) {
        homeGraph(
            navController = navController
        )

        profileGraph(
            navController = navController
        )
    }
}