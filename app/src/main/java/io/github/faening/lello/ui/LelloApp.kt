package io.github.faening.lello.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Book
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.faening.lello.core.designsystem.component.navigation.CentralNavigationBarItem
import io.github.faening.lello.core.designsystem.component.navigation.LelloNavigationBar
import io.github.faening.lello.core.designsystem.component.navigation.LelloNavigationBarItem
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.journal.NavigationItem
import io.github.faening.lello.feature.achievement.AchievementDestinations
import io.github.faening.lello.feature.diary.DiaryDestinations
import io.github.faening.lello.feature.home.HomeDestinations
import io.github.faening.lello.feature.medication.MedicationDestinations
import io.github.faening.lello.feature.profile.ProfileDestinations
import io.github.faening.lello.navigation.LelloNavHost
import io.github.faening.lello.feature.achievement.R as achievementR
import io.github.faening.lello.feature.diary.R as diaryR
import io.github.faening.lello.feature.home.R as homeR
import io.github.faening.lello.feature.medication.R as medicationR
import io.github.faening.lello.feature.profile.R as profileR

@Composable
fun LelloApp() {
    val navController = rememberNavController()
    val items = SetupNavigationItems()

    // Lista de rotas de alto nível onde o menu deve aparecer
    val topLevelRoutes = items.map { it.route }.toSet()

    // Verificar se estamos em uma rota de alto nível para mostrar o menu
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showBottomBar = currentDestination?.route in topLevelRoutes

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets.navigationBars,
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

@SuppressLint("ComposableNaming")
@Composable
private fun SetupNavigationItems() : List<NavigationItem> {
    return listOf(
        NavigationItem(
            title = homeR.string.home_title,
            route = HomeDestinations.HOME,
            selectedIcon = LelloIcons.Filled.Home.imageVector,
            unselectedIcon = LelloIcons.Outlined.Home.imageVector
        ),
        NavigationItem(
            title = diaryR.string.diary_title,
            route = DiaryDestinations.HOME,
            selectedIcon = LelloIcons.Filled.BookOpen.imageVector,
            unselectedIcon = LelloIcons.Outlined.BookOpen.imageVector
        ),
        NavigationItem(
            title = achievementR.string.achievements_title,
            route = AchievementDestinations.GRAPH,
            selectedIcon = LelloIcons.Filled.Achievement.imageVector,
            unselectedIcon = LelloIcons.Outlined.Achievement.imageVector
        ),
        NavigationItem(
            title = medicationR.string.medication_title,
            route = MedicationDestinations.HOME,
            selectedIcon = LelloIcons.Filled.DrugPill.imageVector,
            unselectedIcon = LelloIcons.Outlined.DrugPill.imageVector
        ),
        NavigationItem(
            title = profileR.string.profile_title,
            route = ProfileDestinations.HOME,
            selectedIcon = LelloIcons.Filled.Profile.imageVector,
            unselectedIcon = LelloIcons.Outlined.Profile.imageVector
        )
    )
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

            if (index == 2) {
                CentralNavigationBarItem(
                    icon = if (selected) item.selectedIcon else item.unselectedIcon,
                    onClick = {
                        navController.navigate(item.route) {
                            // Evita múltiplas cópias da mesma rota na pilha
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }

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
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }

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

@Composable
fun previewNavigationItems(): List<NavigationItem> {
    return listOf(
        NavigationItem(
            title = android.R.string.ok,
            route = "home",
            selectedIcon = Icons.Rounded.Home,
            unselectedIcon = Icons.Rounded.Home
        ),
        NavigationItem(
            title = android.R.string.ok,
            route = "diary",
            selectedIcon = Icons.Rounded.Book,
            unselectedIcon = Icons.Rounded.Book
        ),
        NavigationItem(
            title = android.R.string.ok,
            route = "achievements",
            selectedIcon = Icons.Rounded.Star,
            unselectedIcon = Icons.Rounded.Star
        ),
        NavigationItem(
            title = android.R.string.ok,
            route = "medication",
            selectedIcon = Icons.Rounded.Add,
            unselectedIcon = Icons.Rounded.Add
        ),
        NavigationItem(
            title = android.R.string.ok,
            route = "profile",
            selectedIcon = Icons.Rounded.Person,
            unselectedIcon = Icons.Rounded.Person
        )
    )
}

@Preview(name = "Light Theme")
@Composable
fun LelloAppPreview() {
    LelloTheme(darkTheme = false) {
        val navController = rememberNavController()
        val items = previewNavigationItems()

        Scaffold(
            modifier = Modifier.fillMaxSize(),
            containerColor = MaterialTheme.colorScheme.background,
            bottomBar = {
                LelloAppBottomBar(
                    items = items,
                    currentDestination = null,
                    navController = navController
                )
            }
        ) { innerPadding ->
            // Conteúdo simulado para o preview
            Text(
                text = "Conteúdo do App",
                modifier = Modifier.padding(innerPadding),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}