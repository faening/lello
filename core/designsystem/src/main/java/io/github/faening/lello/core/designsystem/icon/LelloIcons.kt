package io.github.faening.lello.core.designsystem.icon

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import io.github.faening.lello.core.designsystem.R as designsystemR

@Suppress("DEPRECATION")
object LelloIcons {
    // Menu
    val Home = Icons.Outlined.Home

    // TopAppBar
    val Favorite = Icons.Rounded.Favorite
    val MoreVert = Icons.Rounded.MoreVert
    val Add = Icons.Rounded.Add

    val Logo = LelloIcon(designsystemR.drawable.ic_lello_logo)

    object Outlined {
        val Mail = LelloIcon(designsystemR.drawable.ic_mail_outlined)
    }

    object Filled {
        val Google = LelloIcon(designsystemR.drawable.ic_google_filled)
    }

    @Composable
    fun customIcon(id: Int): ImageVector = ImageVector.vectorResource(id = id)
}

data class LelloIcon(val resId: Int) {
    val imageVector: ImageVector
        @Composable get() = ImageVector.vectorResource(id = resId)
}