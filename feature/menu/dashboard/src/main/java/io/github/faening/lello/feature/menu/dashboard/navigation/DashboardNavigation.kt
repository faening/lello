package io.github.faening.lello.feature.menu.dashboard.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.faening.lello.feature.menu.dashboard.screen.DashboardScreen

const val DASHBOARD_GRAPH = "dashboard"
const val DASHBOARD_MAIN_ROUTE = "dashboard_main"
const val DASHBOARD_SETTINGS_ROUTE = "dashboard_settings"
const val DASHBOARD_EDIT_ROUTE = "dashboard_edit"

fun NavGraphBuilder.dashboardGraph(navController: NavController) {

    composable(route = DASHBOARD_MAIN_ROUTE) {
        DashboardScreen(
            onSettingsClick = {
                navController.navigate(DASHBOARD_SETTINGS_ROUTE)
            },
            onEditDashboardClick = {
                navController.navigate(DASHBOARD_EDIT_ROUTE)
            }
        )
    }
}
