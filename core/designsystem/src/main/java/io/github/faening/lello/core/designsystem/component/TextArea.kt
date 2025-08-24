package io.github.faening.lello.core.designsystem.component

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme

@Composable
fun LelloTextArea(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String? = null,
    placeholder: String = "",
    maxLength: Int = 500,
    showCounter: Boolean = false,
    enabled: Boolean = true,
) {
    Column(modifier = modifier) {
        if (!label.isNullOrBlank()) {
            Text(
                text = label,
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = Dimension.spacingSmall),
                fontWeight = FontWeight.ExtraBold,
            )
        }

        Box(
            modifier = Modifier
                .padding(bottom = Dimension.spacingSmall, end = Dimension.spacingSmall)
        ) {
            // Fake Shadow
            Box(
                modifier = Modifier
                    .matchParentSize()
                    .offset(x = Dimension.spacingSmall, y = Dimension.spacingSmall)
                    .background(
                        color = MaterialTheme.colorScheme.onPrimary.copy(alpha = Dimension.alphaStateDisabled),
                        shape = RoundedCornerShape(Dimension.spacingSmall)
                    )
            )

            OutlinedTextField(
                value = value,
                onValueChange = { if (it.length <= maxLength) onValueChange(it) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(140.dp)
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(Dimension.spacingSmall)
                    )
                    .border(
                        width = 1.5.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(Dimension.spacingSmall)
                    ),
                placeholder = { Text(placeholder) },
                shape = RoundedCornerShape(Dimension.spacingSmall),
                maxLines = 7,
                enabled = enabled,
                textStyle = MaterialTheme.typography.bodyLarge
            )
        }

        // Contador de caracteres (opcional)
        if (showCounter) {
            Spacer(Modifier.height(Dimension.paddingComponentExtraSmall))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "${value.length}/$maxLength",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.outline
                )
            }
        }
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
fun LelloTextAreaPreview() {
    LelloTheme {
        LelloTextArea(
            label = "Sample Text Area",
            value = "This is a sample text area.",
            onValueChange = {},
            placeholder = "Type something here...",
            maxLength = 500,
            showCounter = true,
            modifier = Modifier.fillMaxWidth()
        )
    }
}