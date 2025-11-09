package io.github.faening.lello.core.designsystem.component.list

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchColors
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.theme.DarkColorScheme
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.model.option.EmotionOption
import io.github.faening.lello.core.model.option.JournalOption

@Composable
fun LelloOptionList(
    options: List<JournalOption>,
    onToggle: (option: JournalOption, active: Boolean) -> Unit,
    moodColor: MoodColor = MoodColor.DEFAULT,
    colorScheme: ColorScheme = MaterialTheme.colorScheme,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(options) { index, option ->
            Column(Modifier.fillMaxWidth()) {
                if (index == 0) { DividerLine() }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimension.spacingRegular, vertical = Dimension.spacingMedium),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = option.description,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.bodyLarge,
                        color = OptionListDefaults.textColor()
                    )
                    Switch(
                        checked = option.active,
                        onCheckedChange = { checked ->
                            onToggle(option, checked)
                        },
                        modifier = Modifier.scale(0.8f),
                        colors = OptionListDefaults.switchColor(colorScheme, moodColor)
                    )
                }

                DividerLine()
            }
        }
    }
}

@Composable
private fun DividerLine() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(Dimension.borderWidthThin)
            .background(
                color = OptionListDefaults.dividerColor(),
                shape = RoundedCornerShape(Dimension.borderWidthThin)
            )
    )
}

private object OptionListDefaults {
    @Composable
    fun textColor(): Color {
        return MaterialTheme.colorScheme.onSurface
    }

    @Composable
    fun dividerColor(): Color {
        return MaterialTheme.colorScheme.outline.copy(alpha = Dimension.alphaStateDisabled)
    }

    @Composable
    fun switchColor(colorScheme: ColorScheme, moodColor: MoodColor): SwitchColors {
        val effectiveColorScheme = moodColor.let { mood ->
            val isDark = colorScheme.primary == DarkColorScheme.primary
            colorScheme.copy(primary = mood.getColor(isDark))
        }

        return SwitchDefaults.colors(
            checkedThumbColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            checkedTrackColor = effectiveColorScheme.primary,
            checkedBorderColor = Color.Transparent,
            uncheckedThumbColor = MaterialTheme.colorScheme.surfaceContainerLowest,
            uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            uncheckedBorderColor = Color.Transparent,
        )
    }
}

// region Previews

@Preview(
    name = "Light",
    group = "Unselected",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloOptionListPreview_LightMode() {
    val options = listOf<JournalOption>(
        EmotionOption(id = 1, description = "Feliz", blocked = false, active = true),
        EmotionOption(id = 2, description = "Triste", blocked = false, active = false),
        EmotionOption(id = 3, description = "Ansioso", blocked = false, active = true)
    )

    LelloTheme {
        LelloOptionList(
            options = options,
            onToggle = { _, _ -> }
        )
    }
}

// endregion Previews