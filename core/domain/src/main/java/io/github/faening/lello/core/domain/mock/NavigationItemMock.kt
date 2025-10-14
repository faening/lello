package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.model.journal.NavigationItem

object NavigationItemMock {
    val list = listOf(
        NavigationItem(
            title = 0,
            route = "home",
            selectedIcon = LelloIcons.Filled.Home.imageVector,
            unselectedIcon = LelloIcons.Outlined.Home.imageVector
        ),
        NavigationItem(
            title = 0,
            route = "diary",
            selectedIcon = LelloIcons.Filled.BookOpen.imageVector,
            unselectedIcon = LelloIcons.Outlined.BookOpen.imageVector
        ),
        NavigationItem(
            title = 0,
            route = "achievements",
            selectedIcon = LelloIcons.Filled.Achievement.imageVector,
            unselectedIcon = LelloIcons.Outlined.Achievement.imageVector
        ),
        NavigationItem(
            title = 0,
            route = "medication",
            selectedIcon = LelloIcons.Filled.DrugPill.imageVector,
            unselectedIcon = LelloIcons.Outlined.DrugPill.imageVector
        ),
        NavigationItem(
            title = 0,
            route = "profile",
            selectedIcon = LelloIcons.Filled.Profile.imageVector,
            unselectedIcon = LelloIcons.Outlined.Profile.imageVector
        )
    )
}