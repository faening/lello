package io.github.faening.lello.feature.diary.mood.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.diary.mood.screen.DiaryMoodScreen
import io.github.faening.lello.feature.diary.mood.screen.ScreenOne
import io.github.faening.lello.feature.diary.mood.screen.ScreenTwo

object DiaryMoodRoute {
    const val DIARY_MOOD_GRAPH = "diary_mood_graph"
    const val HOME_ROUTE = "diary_mood_route"
    const val SCREEN_ONE_ROUTE = "screen_one_route"
    const val SCREEN_TWO_ROUTE = "screen_two_route"
}

internal fun NavController.navigateToDiaryMood(navOptions: NavOptions? = null) {
    this.navigate(route = DiaryMoodRoute.HOME_ROUTE, navOptions = navOptions)
}

internal fun NavController.navigateToScreenOne(navOptions: NavOptions? = null) {
    this.navigate(route = DiaryMoodRoute.SCREEN_ONE_ROUTE, navOptions = navOptions)
}

internal fun NavController.navigateToScreenTwo(navOptions: NavOptions? = null) {
    this.navigate(route = DiaryMoodRoute.SCREEN_TWO_ROUTE, navOptions = navOptions)
}

fun NavGraphBuilder.diaryMoodGraph(onBackClick: () -> Unit, navController: NavController) {
    navigation(
        startDestination = DiaryMoodRoute.HOME_ROUTE,
        route = DiaryMoodRoute.DIARY_MOOD_GRAPH
    ) {
        composable(route = DiaryMoodRoute.HOME_ROUTE) {
            DiaryMoodScreen(
                onBackClick = onBackClick,
                onNavigateToScreenOne = { navController.navigateToScreenOne() }
            )
        }

        composable(route = DiaryMoodRoute.SCREEN_ONE_ROUTE) {
            ScreenOne(
                onBackClick = { navController.popBackStack() },
                onNavigateToScreenTwo = { navController.navigateToScreenTwo() }
            )
        }

        composable(route = DiaryMoodRoute.SCREEN_TWO_ROUTE) {
            ScreenTwo(
                onBackClick = { navController.popBackStack() }
            )
        }
    }
}