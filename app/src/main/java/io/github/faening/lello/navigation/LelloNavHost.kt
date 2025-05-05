package io.github.faening.lello.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.github.faening.lello.feature.menu.achievement.navigation.achievementGraph
import io.github.faening.lello.feature.menu.diary.navigation.diaryGraph
import io.github.faening.lello.feature.menu.home.navigation.HOME_MAIN_ROUTE
import io.github.faening.lello.feature.menu.home.navigation.homeGraph
import io.github.faening.lello.feature.menu.profile.navigation.profileGraph

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

        achievementGraph(
            navController = navController
        )

        profileGraph(
            navController = navController
        )
    }
}