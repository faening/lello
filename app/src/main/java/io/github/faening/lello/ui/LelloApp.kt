package io.github.faening.lello.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.faening.lello.core.designsystem.component.LelloNavigationBar
import io.github.faening.lello.core.designsystem.component.LelloNavigationBarItem
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.dashboard.navigation.DASHBOARD_MAIN_ROUTE
import io.github.faening.lello.feature.diary.navigation.DIARY_MAIN_ROUTE
import io.github.faening.lello.feature.home.navigation.HOME_MAIN_ROUTE
import io.github.faening.lello.feature.profile.navigation.PROFILE_MAIN_ROUTE
import io.github.faening.lello.navigation.LelloNavHost
import io.github.faening.lello.feature.dashboard.R as dashboardR
import io.github.faening.lello.feature.diary.R as diaryR
import io.github.faening.lello.feature.home.R as homeR
import io.github.faening.lello.feature.profile.R as profileR

@Composable
fun LelloApp() {
    val navController = rememberNavController()

    // Principais destinos
    val items = listOf(
        NavigationItem(
            title = homeR.string.home_title,
            route = HOME_MAIN_ROUTE,
            selectedIcon = LelloIcons.Home,
            unselectedIcon = LelloIcons.HomeBorder
        ),
        NavigationItem(
            title = diaryR.string.diary_title,
            route = DIARY_MAIN_ROUTE,
            selectedIcon = LelloIcons.Diary,
            unselectedIcon = LelloIcons.DiaryBorder
        ),
        NavigationItem(
            title = dashboardR.string.dashboard_title,
            route = DASHBOARD_MAIN_ROUTE,
            selectedIcon = LelloIcons.Dashboard,
            unselectedIcon = LelloIcons.DashboardBorder
        ),
        NavigationItem(
            title = profileR.string.profile_title,
            route = PROFILE_MAIN_ROUTE,
            selectedIcon = LelloIcons.Profile,
            unselectedIcon = LelloIcons.ProfileBorder
        )
    )

    // Lista de rotas de alto nível onde o menu deve aparecer
    val topLevelRoutes = items.map { it.route }.toSet()

    // Verificar se estamos em uma rota de alto nível para mostrar o menu
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showBottomBar = currentDestination?.route in topLevelRoutes

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            if (showBottomBar) {
                LelloNavigationBar {
                    items.forEach { item ->
                        val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true

                        LelloNavigationBarItem(
                            icon = {
                                Icon(
                                    imageVector = item.unselectedIcon,
                                    contentDescription = stringResource(id = item.title)
                                )
                            },
                            selectedIcon = {
                                Icon(
                                    imageVector = item.selectedIcon,
                                    contentDescription = stringResource(id = item.title)
                                )
                            },
                            label = { Text(text = stringResource(id = item.title)) },
                            selected = selected,
                            onClick = {
                                navController.navigate(item.route) {
                                    // Evita múltiplas cópias da mesma rota na pilha
                                    popUpTo(navController.graph.findStartDestination().id) { saveState = true }

                                    // Evita múltiplas cópias do mesmo destino
                                    launchSingleTop = true

                                    // Restaura o estado quando voltar a este destino
                                    restoreState = true
                                }
                            }
                        )
                    }
                }
            }
        }
    ) { innerPadding ->
        LelloNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview(name = "Light Theme")
@Composable
fun LelloAppPreview() {
    LelloTheme(darkTheme = false) {
        LelloApp()
    }
}

private data class NavigationItem(
    val title: Int,
    val route: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)