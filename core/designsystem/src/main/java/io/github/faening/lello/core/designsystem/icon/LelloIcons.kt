package io.github.faening.lello.core.designsystem.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Inventory2
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource

@Suppress("DEPRECATION")
object LelloIcons {
    // Menu
    val Home = Icons.Outlined.Home
    val HomeBorder = Icons.Rounded.Home
    val Achievements = Icons.Outlined.Inventory2
    val AchievementsBorder = Icons.Rounded.Inventory2
    val Profile = Icons.Outlined.Person
    val ProfileBorder = Icons.Rounded.Person

    // TopAppBar
    val Favorite = Icons.Rounded.Favorite
    val MoreVert = Icons.Rounded.MoreVert

    // Carregar ícones SVG
    @Composable
    fun customIcon(id: Int): ImageVector = ImageVector.vectorResource(id = id)
}