package io.github.faening.lello.core.designsystem.component.appbar

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.R
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension

@Composable
internal fun TopAppBarNavigationIcon(
    navigateUp: TopAppBarAction? = null,
    colorScheme: ColorScheme
) {
    navigateUp?.let {
        Box(modifier = Modifier.padding(start = Dimension.Small)) {
            TopAppBarActionButton(
                action = it.also {
                    it.icon = LelloIcons.customIcon(R.drawable.ic_arrow_large_left)
                    it.contentDescription = "Voltar"
                },
                iconTint = colorScheme.onPrimary,
                background = colorScheme.primary
            )
        }
    }
}

@Composable
internal fun TopAppBarActionIcon(
    actions: List<TopAppBarAction> = emptyList(),
    colorScheme: ColorScheme
) {
    actions.forEachIndexed { index, action ->
        Box(
            modifier = Modifier.padding(
                end = Dimension.Small
            )
        ) {
            TopAppBarActionButton(
                action = action,
                iconTint = colorScheme.onPrimary,
                background = colorScheme.primary
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun TopAppBarActionButton(
    action: TopAppBarAction,
    iconTint: Color,
    background: Color,
    contentPadding: Modifier = Modifier
) {
    Box(modifier = contentPadding) {
        Surface(
            modifier = Modifier.size(44.dp),
            shape = RoundedCornerShape(8.dp),
            color = background,
            onClick = action.onClick
        ) {
            Box(Modifier.padding(8.dp), contentAlignment = Alignment.Center) {
                action.icon?.let {
                    Icon(
                        imageVector = it,
                        contentDescription = action.contentDescription,
                        tint = iconTint,
                        modifier = Modifier.size(20.dp)
                    )
                }
            }
        }
    }
}

data class TopAppBarAction(
    var icon: ImageVector? = null,
    var contentDescription: String = "",
    val onClick: () -> Unit = {}
)