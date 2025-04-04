package io.github.faening.lello.feature.diarymeal.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.diarymeal.DiaryMealScreen

internal object DiaryMealRoute {
    const val DIARY_MEAL_GRAPH = "diary_meal_graph"
    const val HOME_ROUTE = "diary_meal_route"
}

fun NavController.navigateToDiaryMeal(navOptions: NavOptions? = null) {
    this.navigate(route = DiaryMealRoute.HOME_ROUTE, navOptions = navOptions)
}

fun NavGraphBuilder.diaryMealGraph(onBackClick: () -> Unit, navController: NavController) {
    navigation(
        startDestination = DiaryMealRoute.HOME_ROUTE,
        route = DiaryMealRoute.DIARY_MEAL_GRAPH
    ) {
        composable(route = DiaryMealRoute.HOME_ROUTE) {
            DiaryMealScreen(
                onBackClick = onBackClick
            )
        }
    }
}