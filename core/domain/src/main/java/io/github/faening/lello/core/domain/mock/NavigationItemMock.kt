package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.model.journal.NavigationItem

object NavigationItemMock {
    val list = listOf(
        NavigationItem(
            title = 0,
            route = "home",
            selectedIcon = LelloIcons.Home,
            unselectedIcon = LelloIcons.Home
        ),
        NavigationItem(
            title = 0,
            route = "diary",
            selectedIcon = LelloIcons.Home,
            unselectedIcon = LelloIcons.Home
        ),
        NavigationItem(
            title = 0,
            route = "achievements",
            selectedIcon = LelloIcons.Home,
            unselectedIcon = LelloIcons.Home
        ),
        NavigationItem(
            title = 0,
            route = "medication",
            selectedIcon = LelloIcons.Home,
            unselectedIcon = LelloIcons.Home
        ),
        NavigationItem(
            title = 0,
            route = "profile",
            selectedIcon = LelloIcons.Home,
            unselectedIcon = LelloIcons.Home
        )
    )
}