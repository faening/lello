package io.github.faening.lello.core.designsystem.component

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.option.EmotionOption
import io.github.faening.lello.core.model.option.JournalOption

@Composable
fun LelloOptionList(
    options: List<JournalOption>,
    onToggle: (option: JournalOption, active: Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val dividerColor = MaterialTheme.colorScheme.outline.copy(alpha = Dimension.ALPHA_DISABLED)
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(options) { index, option ->
            Column(Modifier.fillMaxWidth()) {
                // Divider superior
                if (index == 0) {
                    DividerLine(color = dividerColor)
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = Dimension.Medium, vertical = Dimension.Small),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = option.description,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Switch(
                        checked = option.active,
                        onCheckedChange = { checked ->
                            onToggle(option, checked)
                        },
                        colors = SwitchDefaults.colors(
                            checkedThumbColor = Color.White,
                            checkedTrackColor = MaterialTheme.colorScheme.primary,
                            checkedBorderColor = Color.Transparent,

                            uncheckedThumbColor = Color.White,
                            uncheckedTrackColor = MaterialTheme.colorScheme.secondaryContainer,
                            uncheckedBorderColor = Color.Transparent,
                        )
                    )
                }

                // Divider inferior
                DividerLine(color = dividerColor)
            }
        }
    }
}

@Composable
private fun DividerLine(
    color: Color
) {
    Box(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color = color, shape = RoundedCornerShape(1.dp))
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Light",
    group = "Unselected",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloOptionListPreview() {
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