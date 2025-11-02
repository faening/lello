package io.github.faening.lello.core.designsystem.component.list

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.option.JournalOption
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import io.github.faening.lello.core.testing.data.MedicationActiveIngredientOptionTestData

@Composable
fun LelloMedicationActiveIngredientOptionList(
    items: List<JournalOption>,
    onSelect: (JournalOption) -> Unit,
    searchQuery: String = "",
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val selectedItem = remember { mutableStateOf<JournalOption?>(null) }

    LazyColumn(modifier = modifier) {
        itemsIndexed(items) { index, item ->
            if (index == 0) { DividerLine() }

            val isSelected = selectedItem.value == item
            val backgroundColor = if (isSelected) {
                MaterialTheme.colorScheme.primaryContainer
            } else {
                Color.Transparent
            }
            val textColor = if (isSelected) {
                MaterialTheme.colorScheme.onPrimary
            } else {
                MedicationListDefaults.textColor()
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(backgroundColor)
                    .clickable {
                        selectedItem.value = item
                        onSelect(item)
                    }
                    .padding(horizontal = Dimension.spacingRegular, vertical = Dimension.spacingMedium),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(vertical = Dimension.spacingSmall),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = buildHighlightedText(item.description.uppercase(), searchQuery.uppercase(), isSelected),
                        style = MaterialTheme.typography.titleMedium,
                        color = textColor,
                        maxLines = 6,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            DividerLine()
        }
    }
}

@Composable
private fun DividerLine() {
    Box(
        Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(
                color = MedicationListDefaults.dividerColor(),
                shape = RoundedCornerShape(1.dp)
            )
    )
}

@Composable
private fun buildHighlightedText(text: String, searchQuery: String, isSelected: Boolean): AnnotatedString {
    if (searchQuery.isEmpty()) {
        return AnnotatedString(text, listOf(
            AnnotatedString.Range(
                SpanStyle(color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MedicationListDefaults.textColor()),
                0,
                text.length
            )
        ))
    }

    return buildAnnotatedString {
        var lastIndex = 0
        val regex = Regex(Regex.escape(searchQuery), RegexOption.IGNORE_CASE)

        regex.findAll(text).forEach { match ->
            append(text.substring(lastIndex, match.range.first))
            withStyle(style = SpanStyle(
                color = if (isSelected) MaterialTheme.colorScheme.onPrimary else MedicationListDefaults.activeTextColor(),
                fontWeight = FontWeight.Bold
            )) {
                append(match.value)
            }
            lastIndex = match.range.last + 1
        }

        append(text.substring(lastIndex))
    }
}

private object MedicationListDefaults {
    @Composable
    fun dividerColor(): Color {
        return MaterialTheme.colorScheme.outline.copy(alpha = Dimension.alphaStateDisabled)
    }

    @Composable
    fun textColor(): Color {
        return MaterialTheme.colorScheme.onPrimaryContainer
    }

    @Composable
    fun activeTextColor(): Color {
        return MaterialTheme.colorScheme.tertiary
    }
}

// region: Previews

@Preview(
    name = "Light",
    group = "Unselected",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloMedicationItemPreview_LightMode_Default() {
    val items = MedicationActiveIngredientOptionTestData.list
    LelloTheme {
        LelloMedicationActiveIngredientOptionList(
            items = items,
            onSelect = {}
        )
    }
}

@Preview(
    name = "Light",
    group = "Unselected",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloMedicationItemPreview_LightMode_Selected() {
    val items = MedicationActiveIngredientOptionTestData.list
    LelloTheme {
        LelloMedicationActiveIngredientOptionList(
            items = items,
            onSelect = { items.first() }
        )
    }
}

// endregion: Previews