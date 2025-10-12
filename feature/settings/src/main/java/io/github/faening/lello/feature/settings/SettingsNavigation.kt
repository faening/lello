package io.github.faening.lello.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.faening.lello.feature.settings.screen.SettingsScreen

object SettingsDestinations {
    const val GRAPH = "settings_graph"
    const val HOME = "settings_home"
}

fun NavGraphBuilder.settingsGraph(navController: NavHostController) {
    navigation(
        startDestination = SettingsDestinations.HOME,
        route = SettingsDestinations.GRAPH
    ) {
        composable(route = SettingsDestinations.HOME) { backStackEntry ->
            val viewModel = sharedSettingsViewModel(navController, backStackEntry)

            SettingsScreen(
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun sharedSettingsViewModel(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
): SettingsViewModel {
    val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(SettingsDestinations.GRAPH)
    }
    return hiltViewModel(parentEntry)
}