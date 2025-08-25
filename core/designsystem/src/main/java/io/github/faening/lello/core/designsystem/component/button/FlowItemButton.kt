package io.github.faening.lello.core.designsystem.component.button

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.R
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor

@Composable
fun LelloFlowItemButton(
    icon: ImageVector = LelloIcons.Add,
    contentDescription: String = "Adicionar",
    onClick: () -> Unit,
    enabled: Boolean = true,
    moodColor: MoodColor = MoodColor.DEFAULT,
    colorScheme: ColorScheme = MaterialTheme.colorScheme,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        modifier = modifier
            .size(Dimension.heightButtonSmall)
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

// region: Preview Light Theme

@Preview(
    name = "Default",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFIB_LightTheme_Default() {
    LelloTheme {
        LelloFlowItemButton(
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
private fun LelloFIB_LightTheme_Disabled() {
    LelloTheme {
        LelloFlowItemButton(
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
private fun LelloFIB_DarkTheme_Default() {
    LelloTheme(darkTheme = true) {
        LelloFlowItemButton(
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
private fun LelloFIB_DarkTheme_Disabled() {
    LelloTheme(darkTheme = true) {
        LelloFlowItemButton(
            onClick = {},
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
private fun LelloFIB_InverseTheme_Default() {
    LelloTheme {
        LelloFlowItemButton(
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
private fun LelloFIB_InverseTheme_Disabled() {
    LelloTheme {
        LelloFlowItemButton(
            onClick = {},
            enabled = false,
            moodColor = MoodColor.INVERSE,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Inverse Theme

// region: Preview Aquamarine Theme

@Preview(
    name = "Default",
    group = "Aquamarine Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFIB_AquamarineTheme_Default() {
    LelloTheme {
        LelloFlowItemButton(
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
private fun LelloFIB_AquamarineTheme_Disabled() {
    LelloTheme {
        LelloFlowItemButton(
            onClick = {},
            enabled = false,
            moodColor = MoodColor.AQUAMARINE,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Aquamarine Theme

// region: Preview Blue Theme

@Preview(
    name = "Default",
    group = "Blue Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFIB_BlueTheme_Default() {
    LelloTheme {
        LelloFlowItemButton(
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
private fun LelloFIB_BlueTheme_Disabled() {
    LelloTheme {
        LelloFlowItemButton(
            onClick = {},
            enabled = false,
            moodColor = MoodColor.BLUE,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Blue Theme

// region: Preview Orange Theme

@Preview(
    name = "Default",
    group = "Orange Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFIB_OrangeTheme_Default() {
    LelloTheme {
        LelloFlowItemButton(
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
private fun LelloFIB_OrangeTheme_Disabled() {
    LelloTheme {
        LelloFlowItemButton(
            onClick = {},
            enabled = false,
            moodColor = MoodColor.ORANGE,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Orange Theme

// region: Preview Red Theme

@Preview(
    name = "Default",
    group = "Red Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFIB_RedTheme_Default() {
    LelloTheme {
        LelloFlowItemButton(
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
private fun LelloFIB_RedTheme_Disabled() {
    LelloTheme {
        LelloFlowItemButton(
            onClick = {},
            enabled = false,
            moodColor = MoodColor.RED,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Red Theme

// region: Preview Secondary Light Theme

@Preview(
    name = "Default",
    group = "Secondary Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFIB_SecondaryLightTheme_Default() {
    LelloTheme {
        LelloFlowItemButton(
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
private fun LelloFIB_SecondaryLightTheme_Disabled() {
    LelloTheme {
        LelloFlowItemButton(
            onClick = {},
            enabled = false,
            moodColor = MoodColor.SECONDARY,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Secondary Theme

// region: Preview Secondary Dark Theme

@Preview(
    name = "Default",
    group = "Secondary Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloFIB_SecondaryDarkTheme_Default() {
    LelloTheme(darkTheme = true) {
        LelloFlowItemButton(
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
private fun LelloFIB_SecondaryDarkTheme_Disabled() {
    LelloTheme(darkTheme = true) {
        LelloFlowItemButton(
            onClick = {},
            enabled = false,
            moodColor = MoodColor.SECONDARY,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Secondary Theme