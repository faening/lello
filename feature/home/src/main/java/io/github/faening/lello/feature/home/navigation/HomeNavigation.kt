package io.github.faening.lello.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.faening.lello.feature.home.screen.HomeDetailScreen
import io.github.faening.lello.feature.home.screen.HomeFeatureScreen
import io.github.faening.lello.feature.home.screen.HomeScreen

const val HOME_GRAPH = "home"
const val HOME_MAIN_ROUTE = "home_main"
const val HOME_DETAIL_ROUTE = "home_detail"
const val HOME_FEATURE_ROUTE = "home_feature"

fun NavGraphBuilder.homeGraph(navController: NavController) {
    // Tela principal do Home diretamente na rota "home"
    composable(route = HOME_MAIN_ROUTE) {
        HomeScreen(
            onFeatureClick = { featureId ->
                // Navegar para um recurso específico dentro do Home
                navController.navigate("${HOME_FEATURE_ROUTE}/$featureId")
            },
            onDetailClick = { itemId ->
                // Navegar para detalhes de um item
                navController.navigate("${HOME_DETAIL_ROUTE}/$itemId")
            },
            onNavigateToModule = { moduleRoute ->
                // Navegar para outros módulos
                navController.navigate(moduleRoute)
            }
        )
    }

    // Detalhe dentro do Home
    composable(
        route = "${HOME_DETAIL_ROUTE}/{itemId}"
    ) { backStackEntry ->
        val itemId = backStackEntry.arguments?.getString("itemId") ?: ""
        HomeDetailScreen(itemId = itemId)
    }

    // Feature específica dentro do Home
    composable(
        route = "${HOME_FEATURE_ROUTE}/{featureId}"
    ) { backStackEntry ->
        val featureId = backStackEntry.arguments?.getString("featureId") ?: ""
        HomeFeatureScreen(featureId = featureId)
    }
}