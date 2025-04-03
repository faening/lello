package io.github.faening.lello.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.faening.lello.feature.diarymood.screen.DiaryMoodScreen
import io.github.faening.lello.feature.home.navigation.HomeNavigation
import io.github.faening.lello.feature.home.screen.HomeScreen

object LelloDestinations {
    const val HOME_ROUTE = "home_route"
    const val DIARY_MOOD_ROUTE = "diary_mood_route"
    const val DIARY_MEDICATION_ROUTE = "diary_medication_route"
    const val DIARY_SLEEP_ROUTE = "diary_sleep_route"
    const val DIARY_FOOD_ROUTE = "diary_food_route"
}

@Composable
fun LelloNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    // Por enquanto, iniciar com home, depois você pode mudar para verificar autenticação
    val startDestination = HomeNavigation.route

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        // Home
        composable(LelloDestinations.HOME_ROUTE) {
            HomeScreen(
                mainNavController = navController,
                onBackClick = { navController.popBackStack() }
            )
        }

        // Diary Mood
        composable(LelloDestinations.DIARY_MOOD_ROUTE) {
            DiaryMoodScreen(
                mainNavController = navController,
                onBackClick = { navController.popBackStack() }
            )
        }

        // Para autenticação futura, descomente:
        /*
        with(LoginDestination) {
            addDestination(
                navController = navController,
                onBackClick = { navController.popBackStack() }
            )
        }

        with(RegisterDestination) {
            addDestination(
                navController = navController,
                onBackClick = { navController.popBackStack() }
            )
        }
        */
    }
}