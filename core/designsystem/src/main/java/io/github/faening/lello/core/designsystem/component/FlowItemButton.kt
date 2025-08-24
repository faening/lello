package io.github.faening.lello.core.designsystem.component

import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension

@Composable
fun LelloFlowItemButton(
    onClick: () -> Unit,
    colorScheme: ColorScheme = MaterialTheme.colorScheme,
) {
    FloatingActionButton(
        modifier = Modifier.size(40.dp),
        containerColor = colorScheme.primary,
        shape = RoundedCornerShape(Dimension.spacingSmall),
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp,
            hoveredElevation = 0.dp,
            focusedElevation = 0.dp
        ),
        onClick = onClick,
    ) {
        Icon(
            modifier = Modifier.size(28.dp),
            imageVector = LelloIcons.Add,
            contentDescription = "Adicionar",
            tint = colorScheme.onPrimary
        )
    }
}