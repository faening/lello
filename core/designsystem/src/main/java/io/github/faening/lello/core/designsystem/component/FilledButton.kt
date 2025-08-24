package io.github.faening.lello.core.designsystem.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LelloFilledButton(
    label: String,
    iconId: Int? = null,
    invertIcon: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit,
    colorScheme: ColorScheme = MaterialTheme.colorScheme,
    shapeRadius: Dp = Dimension.spacingSmall,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .padding(bottom = Dimension.spacingSmall, end = Dimension.spacingSmall)
    ) {
        // Fake Shadow
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(x = Dimension.spacingSmall, y = Dimension.spacingSmall)
                .background(
                    color = if (enabled) colorScheme.surfaceVariant.copy(alpha = Dimension.alphaStateDisabled)
                            else colorScheme.onSurface.copy(alpha = Dimension.alphaStateDisabled),
                    shape = RoundedCornerShape(shapeRadius)
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimension.heightButtonDefault)
                .background(
                    color = if (enabled) colorScheme.primary else colorScheme.secondaryContainer,
                    shape = RoundedCornerShape(shapeRadius)
                )
                .border(
                    border = BorderStroke(
                        width = 1.5.dp,
                        color = if (enabled) colorScheme.outline else colorScheme.onSecondaryContainer
                    ),
                    shape = RoundedCornerShape(shapeRadius)
                )
                .clickable(enabled = enabled, onClick = onClick)
                .padding(horizontal = Dimension.spacingRegular),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (iconId != null && !invertIcon) {
                Icon(
                    imageVector = LelloIcons.customIcon(iconId),
                    contentDescription = null,
                    tint = if (enabled) colorScheme.onPrimary else colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(end = Dimension.spacingSmall)
                )
            }
            Text(
                text = label,
                fontWeight = FontWeight.ExtraBold,
                color = if (enabled) colorScheme.onPrimary else colorScheme.onSurfaceVariant,
                style = MaterialTheme.typography.bodyLarge
            )
            if (iconId != null && invertIcon) {
                Icon(
                    imageVector = LelloIcons.customIcon(iconId),
                    contentDescription = null,
                    tint = if (enabled) colorScheme.onPrimary else colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(start = Dimension.spacingSmall)
                )
            }
        }
    }
}