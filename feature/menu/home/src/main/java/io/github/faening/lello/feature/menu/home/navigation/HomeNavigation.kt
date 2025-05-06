package io.github.faening.lello.feature.menu.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.faening.lello.feature.menu.home.screen.HomeScreen

const val HOME_GRAPH = "home"
const val HOME_MAIN_ROUTE = "home_main"
const val HOME_DETAIL_ROUTE = "home_detail"
const val HOME_FEATURE_ROUTE = "home_feature"

fun NavGraphBuilder.homeGraph(navController: NavController) {
    // Tela principal do Home diretamente na rota "home"
    composable(route = HOME_MAIN_ROUTE) {
        HomeScreen(
            onNavigateToModule = { moduleRoute ->
                // Navegar para outros módulos
                navController.navigate(moduleRoute)
            }
        )
    }
}