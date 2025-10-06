package io.github.faening.lello.core.designsystem.component.textfield

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import io.github.faening.lello.core.designsystem.theme.Dimension

object TextFieldProperties {
    @Composable
    fun textLabelColor(enabled: Boolean, focused: Boolean): Color = when {
        !enabled -> MaterialTheme.colorScheme.outlineVariant
        focused -> MaterialTheme.colorScheme.onSecondaryContainer
        else -> MaterialTheme.colorScheme.onPrimary
    }

    @Composable
    fun textColor(enabled: Boolean) : Color = when(enabled) {
        true -> MaterialTheme.colorScheme.onSurface
        false -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    @Composable
    fun placeholderColor(enabled: Boolean) : Color = when(enabled) {
        true -> MaterialTheme.colorScheme.surfaceVariant
        false -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    @Composable
    fun iconColor(enabled: Boolean) : Color = when(enabled) {
        true -> MaterialTheme.colorScheme.surfaceVariant
        false -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    @Composable
    fun borderColor(enabled: Boolean, focused: Boolean): Color = when {
        !enabled -> MaterialTheme.colorScheme.outlineVariant
        focused -> MaterialTheme.colorScheme.outline
        else -> MaterialTheme.colorScheme.outline
    }

    @Composable
    fun shadowColor(enabled: Boolean, focused: Boolean): Color = when {
        !enabled -> MaterialTheme.colorScheme.scrim.copy(alpha = Dimension.alphaStateDisabled)
        focused -> MaterialTheme.colorScheme.scrim.copy(alpha = Dimension.alphaStatePressed)
        else -> MaterialTheme.colorScheme.scrim.copy(alpha = Dimension.alphaStateNormal)
    }

    @Composable
    fun textFieldColorScheme(): TextFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.surface,
        unfocusedContainerColor = MaterialTheme.colorScheme.surface,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        disabledTextColor = MaterialTheme.colorScheme.surfaceVariant,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent
    )
}