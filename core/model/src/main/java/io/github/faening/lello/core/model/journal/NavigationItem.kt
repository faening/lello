package io.github.faening.lello.core.model.journal

import androidx.compose.ui.graphics.vector.ImageVector

data class NavigationItem(
    val title: Int,
    val route: String,
    val unselectedIcon: ImageVector,
    val selectedIcon: ImageVector
)