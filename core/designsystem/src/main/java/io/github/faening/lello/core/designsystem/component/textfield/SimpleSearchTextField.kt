package io.github.faening.lello.core.designsystem.component.textfield

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.Grey500
import io.github.faening.lello.core.designsystem.theme.LelloTheme

@Composable
fun LelloSimpleSearchTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String = "",
    maxLength: Int = 60,
    enabled: Boolean = true,
    toUpperCase: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        BasicTextField(
            value = value,
            onValueChange = { newValue ->
                val processedValue = if (toUpperCase) newValue.uppercase() else newValue
                if (processedValue.length <= maxLength) onValueChange(processedValue)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimension.heightButtonDefault)
                .padding(bottom = Dimension.paddingComponentSmall)
                .drawBehind {
                    drawLine(
                        color = SimpleSearchTextFieldDefaults.drawColor(),
                        start = Offset(0f, size.height),
                        end = Offset(size.width, size.height),
                        strokeWidth = Dimension.borderWidthThick.toPx()
                    )
                },
            enabled = enabled,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = SimpleSearchTextFieldDefaults.textColor(enabled)
            ),
            decorationBox = { innerTextField ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = Dimension.paddingComponentSmall),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    leadingIcon?.invoke()

                    Box(modifier = Modifier.weight(1f)) {
                        if (value.isEmpty()) {
                            Text(
                                text = if (toUpperCase) placeholder.uppercase() else placeholder,
                                style = MaterialTheme.typography.bodyLarge.copy(
                                    fontWeight = FontWeight.Bold,
                                    color = SimpleSearchTextFieldDefaults.placeholderColor(enabled)
                                )
                            )
                        }
                        innerTextField()
                    }

                    trailingIcon?.invoke()
                }
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true
        )
    }
}

object SimpleSearchTextFieldDefaults {
    fun drawColor(): Color = Grey500

    @Composable
    fun textColor(enabled: Boolean) : Color = when(enabled) {
        true -> MaterialTheme.colorScheme.onSurface
        false -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    @Composable
    fun placeholderColor(enabled: Boolean): Color = when(enabled) {
        true -> MaterialTheme.colorScheme.surfaceVariant
        false -> MaterialTheme.colorScheme.onSurfaceVariant
    }
}

// region: Previews

@Preview(
    name = "Default Text Field",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
fun LelloSearchTextFieldPreview_LightTheme_Default() {
    LelloTheme {
        LelloSimpleSearchTextField(
            value = "",
            onValueChange = {},
            placeholder = "Search...",
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Previews