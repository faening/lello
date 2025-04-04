package io.github.faening.lello.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import io.github.faening.lello.core.domain.repository.NavigationDestination
import io.github.faening.lello.feature.home.screen.HomeScreen

const val HOME_ROUTE = "home_route"

// Objeto que encapsula informações de navegação do módulo
object HomeRoute {
    val route = HOME_ROUTE
}

// Extensão para NavController que facilita a navegação para este módulo
fun NavController.navigateToHome(navOptions: NavOptions? = null) {
    this.navigate(route = HomeRoute.route, navOptions = navOptions)
}

// Função que adiciona este destino ao NavGraphBuilder
fun NavGraphBuilder.homeScreen(
    onDiaryMoodClick: () -> Unit
) {
    composable(route = HomeRoute.route) {
        HomeScreen(
            onDiaryMoodClick = onDiaryMoodClick
        )
    }
}