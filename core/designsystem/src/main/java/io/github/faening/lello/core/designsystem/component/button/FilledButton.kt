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
        modifier = modifier.padding(bottom = Dimension.paddingComponentSmall, end = Dimension.paddingComponentSmall)
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
                    color = ButtonProperties.buttonColor(enabled, colorScheme, moodColor),
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
                style = MaterialTheme.typography.bodyLarge
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
    name = "Default",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun FilledButton_LightTheme_Default() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Default Invert Icon",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun FilledButton_LightTheme_InvertIcon() {
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
    name = "Disabled",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun FilledButton_LightTheme_Disabled() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun FilledButton_DarkTheme_Default() {
    LelloTheme(darkTheme = true) {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun FilledButton_DarkTheme_Disabled() {
    LelloTheme(darkTheme = true) {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun FilledButton_InverseTheme_Default() {
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
    name = "Disabled",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun FilledButton_InverseTheme_Disabled() {
    LelloTheme(moodColor = MoodColor.INVERSE) {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun FilledButton_AquamarineTheme_Default() {
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
    name = "Disabled",
    group = "Aquamarine Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun FilledButton_AquamarineTheme_Disabled() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun FilledButton_BlueTheme_Default() {
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
    name = "Disabled",
    group = "Blue Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun FilledButton_BlueTheme_Disabled() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun FilledButton_OrangeTheme_Default() {
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
    name = "Disabled",
    group = "Orange Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun FilledButton_OrangeTheme_Disabled() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun FilledButton_RedTheme_Default() {
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

@Preview(
    name = "Disabled",
    group = "Red Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun FilledButton_RedTheme_Disabled() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
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
private fun FilledButton_SecondaryLightTheme_Default() {
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
    name = "Disabled",
    group = "Secondary Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun FilledButton_SecondaryLightTheme_Disabled() {
    LelloTheme {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
            onClick = {},
            enabled = false,
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
private fun FilledButton_SecondaryDarkTheme_Default() {
    LelloTheme(
        darkTheme = true
    ) {
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
    name = "Disabled",
    group = "Secondary Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun FilledButton_SecondaryDarkTheme_Disabled() {
    LelloTheme(
        darkTheme = true
    ) {
        LelloFilledButton(
            label = "Enter with e-mail",
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_mail_outlined),
            onClick = {},
            enabled = false,
            moodColor = MoodColor.SECONDARY,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Secondary Dark Theme