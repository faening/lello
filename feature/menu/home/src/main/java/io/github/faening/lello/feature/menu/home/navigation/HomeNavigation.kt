package io.github.faening.lello.feature.menu.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.faening.lello.feature.diary.mood.navigation.DiaryMoodRoute
import io.github.faening.lello.feature.diary.mood.navigation.diaryMoodGraph
import io.github.faening.lello.feature.menu.home.screen.HomeScreen

const val HOME_MAIN_ROUTE = "home_main"

// Journals
sealed class ModuleRoute(val route: String) {
    object Mood : ModuleRoute(DiaryMoodRoute.HOME_ROUTE)

}

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