package io.github.faening.lello.feature.diarymood.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.diarymood.screen.DiaryMoodScreen
import io.github.faening.lello.feature.diarymood.screen.ScreenOne
import io.github.faening.lello.feature.diarymood.screen.ScreenTwo

internal object DiaryMoodRoute {
    const val DIARY_MOOD_GRAPH = "diary_mood_graph"
    const val DIARY_MOOD_HOME = "diary_mood_route"
    const val SCREEN_ONE_ROUTE = "screen_one_route"
    const val SCREEN_TWO_ROUTE = "screen_two_route"
}

fun NavController.navigateToDiaryMood(navOptions: NavOptions? = null) {
    this.navigate(route = DiaryMoodRoute.DIARY_MOOD_HOME, navOptions = navOptions)
}

internal fun NavController.navigateToScreenOne(navOptions: NavOptions? = null) {
    this.navigate(route = DiaryMoodRoute.SCREEN_ONE_ROUTE, navOptions = navOptions)
}

internal fun NavController.navigateToScreenTwo(navOptions: NavOptions? = null) {
    this.navigate(route = DiaryMoodRoute.SCREEN_TWO_ROUTE, navOptions = navOptions)
}

fun NavGraphBuilder.diaryMoodGraph(onBackClick: () -> Unit, navController: NavController) {
    navigation(
        startDestination = DiaryMoodRoute.DIARY_MOOD_HOME,
        route = DiaryMoodRoute.DIARY_MOOD_GRAPH
    ) {
        composable(route = DiaryMoodRoute.DIARY_MOOD_HOME) {
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