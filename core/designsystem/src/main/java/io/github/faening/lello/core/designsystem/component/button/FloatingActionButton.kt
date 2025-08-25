package io.github.faening.lello.core.designsystem.component.button

import android.annotation.SuppressLint
import android.content.res.Configuration
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
    onClick: () -> Unit,
    moodColor: MoodColor = MoodColor.DEFAULT,
    colorScheme: ColorScheme = MaterialTheme.colorScheme,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {

    Box(
        modifier = modifier.padding(bottom = Dimension.paddingComponentSmall, end = Dimension.paddingComponentSmall)
    ) {
        // Fake Shadow
        Box(
            modifier = Modifier
                .offset(x = Dimension.shadowOffsetX, y = Dimension.shadowOffsetY)
                .background(
                    color = ButtonProperties.shadowColor(enabled),
                    shape = LelloShape.fabShape
                )
                .matchParentSize()
        )

        FloatingActionButton(
            modifier = Modifier
                .border(
                    border = BorderStroke(
                        width = Dimension.borderWidthDefault,
                        color = ButtonProperties.borderColor(enabled)
                    ),
                    shape = LelloShape.fabShape
                )
                .clickable(enabled = enabled, onClick = onClick),
            containerColor = ButtonProperties.buttonColor(enabled, colorScheme, moodColor),
            shape = LelloShape.fabShape,
            elevation = ButtonProperties.buttonElevation(),
            onClick = { if (enabled) onClick() },
        ) {
            Icon(
                imageVector = icon,
                contentDescription = contentDescription,
                tint = ButtonProperties.contentColor(enabled, moodColor)
            )
        }
    }
}

// region: Preview Light Theme

@Preview(
    name = "Default",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
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
    uiMode = Configuration.UI_MODE_NIGHT_NO,
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
    uiMode = Configuration.UI_MODE_NIGHT_YES,
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
    uiMode = Configuration.UI_MODE_NIGHT_YES,
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
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun LelloFAB_InverseTheme_Default() {
    LelloTheme {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            moodColor = MoodColor.INVERSE,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun LelloFAB_InverseTheme_Disabled() {
    LelloTheme {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            enabled = false,
            onClick = {},
            moodColor = MoodColor.INVERSE,
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
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_MoodThemeAquamarine_Default() {
    LelloTheme {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            moodColor = MoodColor.AQUAMARINE,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled",
    group = "Aquamarine Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_MoodThemeAquamarine_Disabled() {
    LelloTheme {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            enabled = false,
            onClick = {},
            moodColor = MoodColor.AQUAMARINE,
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
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_MoodThemeBlue_Default() {
    LelloTheme {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            moodColor = MoodColor.BLUE,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled",
    group = "Blue Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_MoodThemeBlue_Disabled() {
    LelloTheme {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            enabled = false,
            onClick = {},
            moodColor = MoodColor.BLUE,
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
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_MoodThemeOrange_Default() {
    LelloTheme {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            moodColor = MoodColor.ORANGE,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled",
    group = "Orange Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_MoodThemeOrange_Disabled() {
    LelloTheme {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            enabled = false,
            onClick = {},
            moodColor = MoodColor.ORANGE,
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
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_MoodThemeRed_Default() {
    LelloTheme {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            moodColor = MoodColor.RED,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled",
    group = "Red Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_MoodThemeRed_Disabled() {
    LelloTheme {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            enabled = false,
            onClick = {},
            moodColor = MoodColor.RED,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Mood Theme - Red

// region: Preview Secondary Light Theme

@Preview(
    name = "Default",
    group = "Secondary Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_SecondaryLightTheme_Default() {
    LelloTheme {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            moodColor = MoodColor.SECONDARY,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled",
    group = "Secondary Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_SecondaryLightTheme_Disabled() {
    LelloTheme {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            enabled = false,
            onClick = {},
            moodColor = MoodColor.SECONDARY,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Secondary Light Theme

// region: Preview Secondary Dark Theme

@Preview(
    name = "Default",
    group = "Secondary Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloFAB_SecondaryDarkTheme_Default() {
    LelloTheme(darkTheme = true) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            moodColor = MoodColor.SECONDARY,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled",
    group = "Secondary Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloFAB_SecondaryDarkTheme_Disabled() {
    LelloTheme(darkTheme = true) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            enabled = false,
            onClick = {},
            moodColor = MoodColor.SECONDARY,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Secondary Dark Theme