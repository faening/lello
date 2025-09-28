package io.github.faening.lello.core.designsystem.component.button

import android.annotation.SuppressLint
import android.content.res.Configuration
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
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.designsystem.R as designsystemR

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LelloFilledButton(
    label: String,
    icon: ImageVector? = null,
    invertIcon: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit,
    moodColor: MoodColor = MoodColor.DEFAULT,
    colorScheme: ColorScheme = MaterialTheme.colorScheme,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {

    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = Dimension.paddingComponentSmall, end = Dimension.paddingComponentSmall)
    ) {
        // Fake Shadow
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(x = Dimension.shadowOffsetX, y = Dimension.shadowOffsetY)
                .background(
                    color = ButtonProperties.shadowColor(enabled),
                    shape = LelloShape.buttonShape
                )
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimension.heightButtonDefault)
                .background(
                    color = ButtonProperties.backgroundColor(enabled, colorScheme, moodColor),
                    shape = LelloShape.buttonShape
                )
                .border(
                    border = BorderStroke(
                        width = Dimension.borderWidthDefault,
                        color = ButtonProperties.borderColor(enabled)
                    ),
                    shape = LelloShape.buttonShape
                )
                .clickable(enabled = enabled, onClick = onClick)
                .padding(horizontal = Dimension.spacingRegular),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            if (icon != null && !invertIcon) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = ButtonProperties.contentColor(enabled, moodColor),
                    modifier = Modifier.padding(end = Dimension.spacingSmall)
                )
            }
            Text(
                text = label,
                fontWeight = FontWeight.Bold,
                color = ButtonProperties.contentColor(enabled, moodColor),
                style = MaterialTheme.typography.bodyMedium
            )
            if (icon != null && invertIcon) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = ButtonProperties.contentColor(enabled, moodColor),
                    modifier = Modifier.padding(start = Dimension.spacingSmall)
                )
            }
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
private fun LelloFilledButton_LightTheme_Disabled() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
            enabled = false,
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "RTL",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFilledButton_LightTheme_RTL() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
            invertIcon = true,
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
private fun LelloFilledButton_LightTheme_Primary() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun LelloFilledButton_LightTheme_Secondary() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun LelloFilledButton_LightTheme_Aquamarine() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun LelloFilledButton_LightTheme_Blue() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun LelloFilledButton_LightTheme_Orange() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun LelloFilledButton_LightTheme_Red() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun LelloFilledButton_DarkTheme_Disabled() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
            enabled = false,
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "RTL",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloFilledButton_DarkTheme_RTL() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
            invertIcon = true,
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
private fun LelloFilledButton_DarkTheme_Primary() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun LelloFilledButton_DarkTheme_Secondary() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun LelloFilledButton_DarkTheme_Aquamarine() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun LelloFilledButton_DarkTheme_Blue() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun LelloFilledButton_DarkTheme_Orange() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun LelloFilledButton_DarkTheme_Red() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun LelloFilledButton_InverseTheme_Disabled() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
            enabled = false,
            onClick = {},
            moodColor = MoodColor.INVERSE,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "RTL",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun LelloFilledButton_InverseTheme_RTL() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
            invertIcon = true,
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
private fun LelloFilledButton_InverseTheme_Primary() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun LelloFilledButton_InverseTheme_Secondary() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun LelloFilledButton_InverseTheme_Aquamarine() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun LelloFilledButton_InverseTheme_Blue() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun LelloFilledButton_InverseTheme_Orange() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun LelloFilledButton_InverseTheme_Red() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
            onClick = {},
            moodColor = MoodColor.RED,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Inverse Theme