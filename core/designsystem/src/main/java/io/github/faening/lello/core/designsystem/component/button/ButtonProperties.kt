package io.github.faening.lello.core.designsystem.component.button

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.FloatingActionButtonElevation
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import io.github.faening.lello.core.designsystem.theme.DarkColorScheme
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.MoodColor

object ButtonProperties {
    @Composable
    fun shadowColor(enabled: Boolean): Color = when {
        enabled -> MaterialTheme.colorScheme.scrim.copy(alpha = Dimension.alphaStateNormal)
        else -> MaterialTheme.colorScheme.scrim.copy(alpha = Dimension.alphaStateDisabled)
    }

    @Composable
    fun fabElevation(): FloatingActionButtonElevation = FloatingActionButtonDefaults.elevation(
        defaultElevation = Dimension.elevation,
        pressedElevation = Dimension.elevation,
        hoveredElevation = Dimension.elevation,
        focusedElevation = Dimension.elevation
    )

    @Composable
    fun backgroundColor(enabled: Boolean, colorScheme: ColorScheme, moodColor: MoodColor): Color {
        val effectiveColorScheme = moodColor.let { mood ->
            val isDark = colorScheme.primary == DarkColorScheme.primary
            colorScheme.copy(primary = mood.getColor(isDark))
        }

        return when {
            enabled -> effectiveColorScheme.primary
            else -> effectiveColorScheme.secondaryContainer
        }
    }

    @Composable
    fun contentColor(enabled: Boolean, moodColor: MoodColor): Color = when {
        !enabled -> MaterialTheme.colorScheme.onSurfaceVariant
        moodColor in listOf(MoodColor.BLUE, MoodColor.RED, MoodColor.SECONDARY) -> MaterialTheme.colorScheme.onSecondary
        else -> MaterialTheme.colorScheme.onSurface
    }

    @Composable
    fun borderColor(enabled: Boolean): Color = when {
        !enabled -> MaterialTheme.colorScheme.outlineVariant
        else -> MaterialTheme.colorScheme.outline
    }
}