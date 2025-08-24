package io.github.faening.lello.core.designsystem.component.button

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.designsystem.R as designsystemR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LelloFloatingActionButton(
    icon: ImageVector,
    contentDescription: String,
    enabled: Boolean = true,
    colorScheme: ColorScheme = MaterialTheme.colorScheme,
    onClick: () -> Unit,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .padding(bottom = Dimension.paddingComponentSmall, end = Dimension.paddingComponentSmall)
    ) {
        // Fake Shadow
        Box(
            modifier = Modifier
                .offset(x = Dimension.shadowOffsetX, y = Dimension.shadowOffsetY)
                .background(
                    color = if (enabled) colorScheme.onSurface.copy(alpha = Dimension.alphaStateNormal)
                    else colorScheme.onSurface.copy(alpha = Dimension.alphaStateDisabled),
                    shape = LelloShape.fabShape
                )
                .matchParentSize()
        )

        FloatingActionButton(
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = Dimension.borderWidthDefault,
                        color = if (enabled) colorScheme.outline else colorScheme.outlineVariant
                    ),
                    shape = LelloShape.fabShape
                )
                .clickable(enabled = enabled, onClick = onClick),
            containerColor = if (enabled) colorScheme.primary else colorScheme.secondaryContainer,
            shape = LelloShape.fabShape,
            onClick = { if (enabled) onClick() },
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = if (enabled) colorScheme.outline else colorScheme.outlineVariant
            )
        }
    }
}

// region: Preview Light Theme

@Preview(
    name = "Default",
    group = "Light Theme",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_LightTheme_Default() {
    LelloTheme {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled",
    group = "Light Theme",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_LightTheme_Disabled() {
    LelloTheme {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            enabled = false,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Light Theme

// region: Preview Dark Theme

@Preview(
    name = "Default",
    group = "Dark Theme",
    showBackground = true,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloFAB_DarkTheme_Default() {
    LelloTheme(darkTheme = true) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled",
    group = "Dark Theme",
    showBackground = true,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloFAB_DarkTheme_Disabled() {
    LelloTheme(darkTheme = true) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            enabled = false,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Dark Theme

// region: Preview Inverse Theme

@Preview(
    name = "Default",
    group = "Inverse Theme",
    showBackground = true,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun LelloFAB_InverseTheme_Default() {
    LelloTheme(moodColor = MoodColor.INVERSE) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled",
    group = "Inverse Theme",
    showBackground = true,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun LelloFAB_InverseTheme_Disabled() {
    LelloTheme(moodColor = MoodColor.INVERSE) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            enabled = false,
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Inverse Theme

// region: Preview Mood Theme - Aquamarine

@Preview(
    name = "Default",
    group = "Aquamarine Theme",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_MoodThemeAquamarine_Default() {
    LelloTheme(moodColor = MoodColor.AQUAMARINE) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled",
    group = "Aquamarine Theme",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_MoodThemeAquamarine_Disabled() {
    LelloTheme(moodColor = MoodColor.AQUAMARINE) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            enabled = false,
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Mood Theme - Aquamarine

// region: Preview Mood Theme - Blue

@Preview(
    name = "Default",
    group = "Blue Theme",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_MoodThemeBlue_Default() {
    LelloTheme(moodColor = MoodColor.BLUE) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled",
    group = "Blue Theme",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_MoodThemeBlue_Disabled() {
    LelloTheme(moodColor = MoodColor.BLUE) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            enabled = false,
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Mood Theme - Blue

// region: Preview Mood Theme - Orange

@Preview(
    name = "Default",
    group = "Orange Theme",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_MoodThemeOrange_Default() {
    LelloTheme(moodColor = MoodColor.ORANGE) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled",
    group = "Orange Theme",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_MoodThemeOrange_Disabled() {
    LelloTheme(moodColor = MoodColor.ORANGE) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            enabled = false,
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Mood Theme - Orange

// region: Preview Mood Theme - Red

@Preview(
    name = "Default",
    group = "Red Theme",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_MoodThemeRed_Default() {
    LelloTheme(moodColor = MoodColor.RED) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled",
    group = "Red Theme",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_MoodThemeRed_Disabled() {
    LelloTheme(moodColor = MoodColor.RED) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            enabled = false,
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Mood Theme - Red