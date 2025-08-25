package io.github.faening.lello.core.designsystem.component.pill

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor

@Composable
fun LelloFilledPill(
    label: String,
    selected: Boolean = false,
    onClick: () -> Unit,
    moodColor: MoodColor = MoodColor.DEFAULT,
    colorScheme: ColorScheme = MaterialTheme.colorScheme,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    Surface(
        modifier = modifier.clickable { onClick() },
        shape = LelloShape.pillShape,
        color = PillProperties.containerColor(selected, colorScheme, moodColor),
        border = BorderStroke(
            width = Dimension.borderWidthThin,
            color = PillProperties.borderColor(selected)
        ),
        shadowElevation = PillProperties.elevation(),
        tonalElevation = PillProperties.elevation(),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = PillProperties.contentColor(selected, moodColor),
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            modifier = Modifier.padding(horizontal = Dimension.paddingComponentRegular, vertical = Dimension.paddingComponentMedium)
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
private fun LelloFilledPill_LightTheme_Default() {
    LelloTheme {
        LelloFilledPill(
            label = "Happy",
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Selected",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloFilledPill_LightTheme_Selected() {
    LelloTheme {
        LelloFilledPill(
            label = "Happy",
            selected = true,
            onClick = {},
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
private fun LelloFilledPill_LightTheme_Aquamarine() {
    LelloTheme {
        LelloFilledPill(
            label = "Happy",
            selected = true,
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
private fun LelloFilledPill_LightTheme_Blue() {
    LelloTheme {
        LelloFilledPill(
            label = "Happy",
            selected = true,
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
private fun LelloFilledPill_LightTheme_Orange() {
    LelloTheme {
        LelloFilledPill(
            label = "Happy",
            selected = true,
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
private fun LelloFilledPill_LightTheme_Red() {
    LelloTheme {
        LelloFilledPill(
            label = "Happy",
            selected = true,
            onClick = {},
            moodColor = MoodColor.RED,
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
private fun LelloFilledPill_LightTheme_Secondary() {
    LelloTheme {
        LelloFilledPill(
            label = "Happy",
            selected = true,
            onClick = {},
            moodColor = MoodColor.SECONDARY,
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
private fun LelloFilledPill_DarkTheme_Default() {
    LelloTheme {
        LelloFilledPill(
            label = "Happy",
            onClick = {},
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Selected",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun LelloFilledPill_DarkTheme_Selected() {
    LelloTheme {
        LelloFilledPill(
            label = "Happy",
            selected = true,
            onClick = {},
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
private fun LelloFilledPill_DarkTheme_Aquamarine() {
    LelloTheme {
        LelloFilledPill(
            label = "Happy",
            selected = true,
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
private fun LelloFilledPill_DarkTheme_Blue() {
    LelloTheme {
        LelloFilledPill(
            label = "Happy",
            selected = true,
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
private fun LelloFilledPill_DarkTheme_Orange() {
    LelloTheme {
        LelloFilledPill(
            label = "Happy",
            selected = true,
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
private fun LelloFilledPill_DarkTheme_Red() {
    LelloTheme {
        LelloFilledPill(
            label = "Happy",
            selected = true,
            onClick = {},
            moodColor = MoodColor.RED,
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
private fun LelloFilledPill_DarkTheme_Secondary() {
    LelloTheme {
        LelloFilledPill(
            label = "Happy",
            selected = true,
            onClick = {},
            moodColor = MoodColor.SECONDARY,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Dark Theme