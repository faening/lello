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
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.faening.lello.core.designsystem.component.CentralNavigationBarItem
import io.github.faening.lello.core.designsystem.component.LelloNavigationBar
import io.github.faening.lello.core.designsystem.component.LelloNavigationBarItem
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.home.HomeDestinations
import io.github.faening.lello.feature.achievement.AchievementDestinations
import io.github.faening.lello.feature.profile.ProfileDestinations
import io.github.faening.lello.navigation.LelloNavHost
import io.github.faening.lello.core.designsystem.R as designsystemR
import io.github.faening.lello.feature.achievement.R as achievementR
import io.github.faening.lello.feature.home.R as homeR
import io.github.faening.lello.feature.profile.R as profileR

@Composable
fun LelloApp() {
    val navController = rememberNavController()

    // Principais destinos
    val items = listOf(
        NavigationItem(
            title = homeR.string.home_title,
            route = HomeDestinations.HOME,
            selectedIcon = LelloIcons.customIcon(designsystemR.drawable.ic_home_filled),
            unselectedIcon = LelloIcons.customIcon(designsystemR.drawable.ic_home_outlined)
        ),
        NavigationItem(
            title = achievementR.string.achievements_title,
            route = AchievementDestinations.GRAPH,
            selectedIcon = LelloIcons.customIcon(designsystemR.drawable.ic_achievements),
            unselectedIcon = LelloIcons.customIcon(designsystemR.drawable.ic_achievements)
        ),
        NavigationItem(
            title = profileR.string.profile_title,
            route = ProfileDestinations.HOME,
            selectedIcon = LelloIcons.customIcon(designsystemR.drawable.ic_profile_filled),
            unselectedIcon = LelloIcons.customIcon(designsystemR.drawable.ic_profile_outlined)
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
                LelloAppBottomBar(
                    items = items,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    ) { innerPadding ->
        LelloNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
private fun LelloAppBottomBar(
    items: List<NavigationItem>,
    currentDestination: NavDestination?,
    navController: NavController
) {
    LelloNavigationBar {
        items.forEachIndexed { index, item ->
            val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true

            if (index == 1) {
                CentralNavigationBarItem(
                    icon = if (selected) item.selectedIcon else item.unselectedIcon,
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
            } else {
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

private data class NavigationItem(
    val title: Int,
    val route: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)

@Preview(name = "Light Theme")
@Composable
fun LelloAppPreview() {
    LelloTheme(darkTheme = false) {
        LelloApp()
    }
}