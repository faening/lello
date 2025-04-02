package io.github.faening.lello.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import io.github.faening.lello.feature.diarymood.DiaryMoodScreen

object AppDestinations {
    const val HOME_ROUTE = "home"
    const val MOOD_ROUTE = "mood"
    const val MEDICATION_ROUTE = "medication"
    const val SLEEP_ROUTE = "sleep"
    const val FOOD_ROUTE = "food"
}

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppDestinations.HOME_ROUTE
    ) {
        composable(AppDestinations.HOME_ROUTE) {
            DiaryMoodScreen(navController = navController)
        }
//        composable(AppDestinations.MOOD_ROUTE) {
//            DiaryMoodScreen(navController = navController)
//        }
//        composable(AppDestinations.MEDICATION_ROUTE) {
//            MedicationScreen(navController = navController)
//        }
//        composable(AppDestinations.SLEEP_ROUTE) {
//            SleepScreen(navController = navController)
//        }
//        composable(AppDestinations.FOOD_ROUTE) {
//            FoodScreen(navController = navController)
//        }
    }
}