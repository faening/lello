package io.github.faening.lello.feature.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.faening.lello.core.navigation.customComposable
import io.github.faening.lello.feature.authentication.AuthenticationDestinations
import io.github.faening.lello.feature.settings.screen.SettingsNotificationScreen
import io.github.faening.lello.feature.settings.screen.SettingsScreen
import io.github.faening.lello.feature.settings.screen.SettingsTermsAndPrivacyScreen

object SettingsDestinations {
    const val GRAPH = "settings_graph"
    const val HOME = "settings_home"
    const val NOTIFICATIONS = "settings_notifications"
    const val TERMS = "settings_terms"
}

fun NavGraphBuilder.settingsGraph(navController: NavHostController) {
    navigation(
        startDestination = SettingsDestinations.HOME,
        route = SettingsDestinations.GRAPH
    ) {
        composable(
            route = SettingsDestinations.HOME
        ) { backStackEntry ->
            val viewModel = sharedSettingsViewModel(navController, backStackEntry)

            SettingsScreen(
                viewModel = viewModel,
                onNavigateToNotifications = { navController.navigate(SettingsDestinations.NOTIFICATIONS) },
                onNavigateToTerms = { navController.navigate(SettingsDestinations.TERMS) },
                onLogoutSuccess = {
                    navController.navigate(AuthenticationDestinations.GRAPH) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }
            )
        }

        customComposable(
            route = SettingsDestinations.NOTIFICATIONS
        ) { backStackEntry ->
            val viewModel = sharedSettingsViewModel(navController, backStackEntry)

            SettingsNotificationScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
            )
        }

        customComposable(
            route = SettingsDestinations.TERMS
        ) { backStackEntry ->
            val viewModel = sharedSettingsViewModel(navController, backStackEntry)

            SettingsTermsAndPrivacyScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() }
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