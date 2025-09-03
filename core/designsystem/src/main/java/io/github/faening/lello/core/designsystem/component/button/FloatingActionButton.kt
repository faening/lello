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
            containerColor = ButtonProperties.backgroundColor(enabled, colorScheme, moodColor),
            shape = LelloShape.fabShape,
            elevation = ButtonProperties.fabElevation(),
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
            enabled = false,
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Primary",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_LightTheme_Primary() {
    LelloTheme {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            moodColor = MoodColor.DEFAULT,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Secondary",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_LightTheme_Secondary() {
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
    name = "Aquamarine",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_LightTheme_Aquamarine() {
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
    name = "Blue",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_LightTheme_Blue() {
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
    name = "Orange",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_LightTheme_Orange() {
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
    name = "Red",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFAB_LightTheme_Red() {
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

// endregion: Preview Light Theme

// region: Preview Dark Theme

@Preview(
    name = "Disabled",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloFAB_DarkTheme_Disabled() {
    LelloTheme {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            enabled = false,
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Primary",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloFAB_DarkTheme_Primary() {
    LelloTheme {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = {},
            moodColor = MoodColor.DEFAULT,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Secondary",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloFAB_DarkTheme_Secondary() {
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
    name = "Aquamarine",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloFAB_DarkTheme_Aquamarine() {
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
    name = "Blue",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloFAB_DarkTheme_Blue() {
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
    name = "Orange",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloFAB_DarkTheme_Orange() {
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
    name = "Red",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloFAB_DarkTheme_Red() {
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

// endregion: Preview Dark Theme

// region: Preview Inverse Theme

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

@Preview(
    name = "Primary",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun LelloFAB_InverseTheme_Primary() {
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
    name = "Secondary",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun LelloFAB_InverseTheme_Secondary() {
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
    name = "Aquamarine",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun LelloFAB_InverseTheme_Aquamarine() {
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
    name = "Blue",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun LelloFAB_InverseTheme_Blue() {
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
    name = "Orange",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun LelloFAB_InverseTheme_Orange() {
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
    name = "Red",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun LelloFAB_InverseTheme_Red() {
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

// endregion: Preview Inverse Theme