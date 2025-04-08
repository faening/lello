package io.github.faening.lello.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.github.faening.lello.feature.diary.navigation.diaryGraph
import io.github.faening.lello.feature.home.navigation.HOME_MAIN_ROUTE
import io.github.faening.lello.feature.home.navigation.homeGraph
import io.github.faening.lello.feature.profile.navigation.profileGraph

@Composable
fun LelloNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HOME_MAIN_ROUTE,
        modifier = modifier
    ) {
        homeGraph(
            navController = navController
        )

        diaryGraph(
            navController = navController
        )

        profileGraph(
            navController = navController
        )
    }
}