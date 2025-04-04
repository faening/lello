package io.github.faening.lello.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.github.faening.lello.core.domain.repository.NavigationDestination
import io.github.faening.lello.feature.home.screen.HomeScreen

object HomeRoute {
    const val HOME_ROUTE = "home_route"
}

fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(route = HomeRoute.HOME_ROUTE, navOptions = navOptions)
}

fun NavGraphBuilder.homeScreen(
    onDiaryMealClick: () -> Unit,
    onDiaryMoodClick: () -> Unit
) {
    composable(route = HomeRoute.HOME_ROUTE) {
        HomeScreen(
            onDiaryMealClick = onDiaryMealClick,
            onDiaryMoodClick = onDiaryMoodClick
        )
    }
}