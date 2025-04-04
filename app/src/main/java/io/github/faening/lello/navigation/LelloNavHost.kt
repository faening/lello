package io.github.faening.lello.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import io.github.faening.lello.core.domain.contracts.AppNavigator
import io.github.faening.lello.feature.diarymeal.navigation.diaryMealGraph
import io.github.faening.lello.feature.diarymeal.navigation.navigateToDiaryMeal
import io.github.faening.lello.feature.diarymood.navigation.diaryMoodGraph
import io.github.faening.lello.feature.diarymood.navigation.navigateToDiaryMood
import io.github.faening.lello.feature.home.navigation.HomeRoute.HOME_ROUTE
import io.github.faening.lello.feature.home.navigation.homeScreen
import io.github.faening.lello.feature.home.navigation.navigateToHome

@Composable
fun LelloNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = HOME_ROUTE
) {
    val navigator: AppNavigator = remember(navController) {
        LelloAppNavigator(navController)
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen(
            navigator = navigator
        )

        diaryMealGraph(
            onBackClick = { navController.popBackStack() },
            navController = navController
        )

        diaryMoodGraph(
            onBackClick = { navController.popBackStack() },
            navController = navController
        )
    }
}

internal class LelloAppNavigator(
    private val navController: NavController,
    private val defaultNavOptions: NavOptions? = null
) : AppNavigator {

    override fun navigateToHome() {
        navController.navigateToHome(defaultNavOptions)
    }

    override fun navigateToMealDiary() {
        navController.navigateToDiaryMeal(defaultNavOptions)
    }

    override fun navigateToMedicationDiary() {
        TODO("Not yet implemented")
    }

    override fun navigateToMoodDiary() {
        navController.navigateToDiaryMood(defaultNavOptions)
    }

    override fun navigateToSleepDiary() {
        TODO("Not yet implemented")
    }

    override fun navigateUp() {
        navController.navigateUp()
    }

    override fun popBackStack(): Boolean {
        return navController.popBackStack()
    }
}