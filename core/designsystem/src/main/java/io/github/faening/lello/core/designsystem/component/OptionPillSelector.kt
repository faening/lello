package io.github.faening.lello.core.designsystem.component

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.option.EmotionOption
import io.github.faening.lello.core.model.option.JournalOption

@Composable
fun <T> LelloOptionPillSelector(
    title: String?,
    options: List<T>,
    isSelected: (T) -> Boolean,
    onToggle: (T) -> Unit,
    onOpenSettings: (() -> Unit)? = null,
    getLabel: (T) -> String,
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimension.spacingRegular)
    ) {
        if (title != null) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge
            )
            Spacer(modifier = Modifier.height(Dimension.spacingRegular))
        }

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(Dimension.spacingSmall),
            verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
        ) {
            options.forEach { option ->
                LelloSelectablePill(
                    label = getLabel(option),
                    selected = isSelected(option),
                    onClick = { onToggle(option) }
                )
            }
            if (onOpenSettings != null) {
                LelloFlowItemButton(
                    onClick = onOpenSettings
                )
            }
        }
    }
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
private fun LelloOptionPillSelectorPreview() {
    val options = listOf<JournalOption>(
        EmotionOption(id = 1, description = "Feliz", blocked = false, active = true),
        EmotionOption(id = 2, description = "Triste", blocked = false, active = false),
        EmotionOption(id = 3, description = "Ansioso", blocked = false, active = true)
    )

    LelloTheme {
        LelloOptionPillSelector(
            title = "Como está o clima agora?",
            options = options,
            isSelected = { _ -> false },
            onToggle = { _ -> },
            onOpenSettings = {},
            getLabel = { it.description }
        )
    }
}