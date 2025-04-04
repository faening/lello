package io.github.faening.lello.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.faening.lello.feature.diarymood.navigation.diaryMoodGraph
import io.github.faening.lello.feature.diarymood.navigation.navigateToDiaryMood
import io.github.faening.lello.feature.home.navigation.HomeRoute.HOME_ROUTE
import io.github.faening.lello.feature.home.navigation.homeScreen

@Composable
fun LelloNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen(
            onDiaryMoodClick = { navController.navigateToDiaryMood() }
        )

        diaryMoodGraph(
            onBackClick = { navController.popBackStack() },
            navController = navController
        )

    }
}