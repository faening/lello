package io.github.faening.lello.feature.diarymood.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import io.github.faening.lello.core.domain.repository.NavigationDestination
import io.github.faening.lello.feature.diarymood.screen.DiaryMoodScreen

object DiaryMoodNavigation : NavigationDestination {
    override val route = "diary_mood_route"
    override val destination = "diary_mood_destination"

    override fun NavGraphBuilder.addDestination(
        navController: NavHostController,
        onBackClick: () -> Unit
    ) {
        composable(route = route.toString()) {
            DiaryMoodScreen(
                mainNavController = navController,
                onBackClick = onBackClick
            )
        }
    }
}