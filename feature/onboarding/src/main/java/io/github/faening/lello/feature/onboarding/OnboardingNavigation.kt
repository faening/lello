package io.github.faening.lello.feature.onboarding

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.faening.lello.core.domain.mock.OnboardingPageMock
import io.github.faening.lello.feature.onboarding.screen.OnboardingScreen

object OnboardingDestinations {
    const val GRAPH = "onboarding_graph"
    const val ONBOARDING = "onboarding"
}

fun NavGraphBuilder.onboardingGraph(
    navController: NavHostController,
    onOnboardingFinish: () -> Unit
) {
    navigation(
        startDestination = OnboardingDestinations.ONBOARDING,
        route = OnboardingDestinations.GRAPH
    ) {
        composable(route = OnboardingDestinations.ONBOARDING) {
            OnboardingScreen(
                pages = OnboardingPageMock.list,
                onFinish = onOnboardingFinish
            )
        }
    }
}