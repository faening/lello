package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.model.journal.NavigationItem

object NavigationItemMock {
    val list = listOf(
        NavigationItem(
            title = "Home",
            route = "home",
            selectedIcon = LelloIcons.Filled.Home.imageVector,
            unselectedIcon = LelloIcons.Outlined.Home.imageVector
        ),
        NavigationItem(
            title = "Diary",
            route = "diary",
            selectedIcon = LelloIcons.Filled.BookOpen.imageVector,
            unselectedIcon = LelloIcons.Outlined.BookOpen.imageVector
        ),
        NavigationItem(
            title = "Achievements",
            route = "achievements",
            selectedIcon = LelloIcons.Filled.Achievement.imageVector,
            unselectedIcon = LelloIcons.Outlined.Achievement.imageVector
        ),
        NavigationItem(
            title = "Medication",
            route = "medication",
            selectedIcon = LelloIcons.Filled.DrugPill.imageVector,
            unselectedIcon = LelloIcons.Outlined.DrugPill.imageVector
        ),
        NavigationItem(
            title = "More",
            route = "more",
            selectedIcon = LelloIcons.Filled.Profile.imageVector,
            unselectedIcon = LelloIcons.Outlined.Profile.imageVector
        )
    )
}