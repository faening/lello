package io.github.faening.lello.core.designsystem.component.pill

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import io.github.faening.lello.core.designsystem.theme.DarkColorScheme
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.MoodColor

internal object PillProperties {
    @Composable
    fun containerColor(selected: Boolean, colorScheme: ColorScheme, moodColor: MoodColor): Color {
        val effectiveColorScheme = moodColor.let { mood ->
            val isDark = colorScheme.primary == DarkColorScheme.primary
            colorScheme.copy(primary = mood.getColor(isDark))
        }

        return when {
            selected -> effectiveColorScheme.primary
            else -> effectiveColorScheme.onSecondary
        }
    }

    @Composable
    fun elevation(): Dp = Dimension.elevation

    @Composable
    fun borderColor(selected: Boolean): Color = when {
        !selected -> MaterialTheme.colorScheme.outlineVariant
        else -> Color.Transparent
    }

    @Composable
    fun contentColor(selected: Boolean, moodColor: MoodColor): Color = when {
        !selected -> MaterialTheme.colorScheme.onSurfaceVariant
        moodColor in listOf(MoodColor.BLUE, MoodColor.RED, MoodColor.SECONDARY) -> MaterialTheme.colorScheme.onSecondary
        else -> MaterialTheme.colorScheme.onSurface
    }
}