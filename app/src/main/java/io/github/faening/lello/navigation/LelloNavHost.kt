package io.github.faening.lello.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import io.github.faening.lello.feature.achievement.achievementGraph
import io.github.faening.lello.feature.authentication.AuthenticationDestinations
import io.github.faening.lello.feature.authentication.authenticationGraph
import io.github.faening.lello.feature.diary.diaryGraph
import io.github.faening.lello.feature.home.HomeDestinations
import io.github.faening.lello.feature.home.homeGraph
import io.github.faening.lello.feature.journal.meal.mealJournalGraph
import io.github.faening.lello.feature.journal.medication.medicationJournalGraph
import io.github.faening.lello.feature.journal.mood.moodJournalGraph
import io.github.faening.lello.feature.journal.settings.settingsJournalGraph
import io.github.faening.lello.feature.journal.sleep.sleepJournalGraph
import io.github.faening.lello.feature.medication.medicationGraph
import io.github.faening.lello.feature.onboarding.OnboardingDestinations
import io.github.faening.lello.feature.onboarding.onboardingGraph
import io.github.faening.lello.feature.settings.settingsGraph
import io.github.faening.lello.startup.StartupViewModel

@Composable
fun LelloNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    viewModel: StartupViewModel = hiltViewModel()
) {
    val isUserAuthenticated by viewModel.isUserAuthenticated.collectAsState()
    val hasSeen by viewModel.hasSeenOnboarding.collectAsState(initial = false)
    val isLoading by viewModel.isLoading.collectAsState()
    val canUseBiometricAuth by viewModel.canUseBiometricAuth.collectAsState()

    if (isLoading || isUserAuthenticated == null) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
        return
    }

    val startDestination = when {
        isUserAuthenticated == false -> AuthenticationDestinations.GRAPH
        isUserAuthenticated == true -> AuthenticationDestinations.GRAPH
        !hasSeen -> OnboardingDestinations.GRAPH
        else -> HomeDestinations.GRAPH
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        onboardingGraph(
            navController = navController,
            onOnboardingFinish = { navController.navigate(HomeDestinations.HOME) }
        )

        authenticationGraph(
            navController = navController,
            isReauthentication = isUserAuthenticated == true,
            canUseBiometricAuth = canUseBiometricAuth
        )

        homeGraph(navController = navController)
        diaryGraph(navController = navController)
        achievementGraph(navController = navController)
        medicationGraph(navController = navController)
        settingsGraph(navController = navController)

        mealJournalGraph(navController = navController)
        medicationJournalGraph(navController = navController)
        moodJournalGraph(navController = navController)
        sleepJournalGraph(navController = navController)
        settingsJournalGraph(navController = navController)
    }
}