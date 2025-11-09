package io.github.faening.lello.core.designsystem.component.textfield

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme

@Composable
fun LelloMultilineTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeholder: String = "",
    maxLength: Int = 500,
    showCounter: Boolean = false,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier,
) {
    var isFocused by remember { mutableStateOf(false) }

    Column(modifier = modifier) {
        if (!label.isNullOrBlank()) {
            TextFieldLabel(
                text = label,
                enabled = enabled,
                isFocused = isFocused
            )
        }

        ShadowedOutlinedTextField(
            value = value,
            onValueChange = { if (it.length <= maxLength) onValueChange(it) },
            enabled = enabled,
            isFocused = isFocused,
            onFocusChanged = { isFocused = it },
            placeholder = placeholder,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions
        )

        CharacterCounter(
            currentLength = value.length,
            maxLength = maxLength,
            showCounter = showCounter,
            enabled = enabled
        )
    }
}

@Composable
private fun TextFieldLabel(
    text: String,
    enabled: Boolean,
    isFocused: Boolean
) {
    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimension.paddingComponentSmall),
        color = MultilineTextFieldDefaults.textLabelColor(enabled, isFocused),
        style = MaterialTheme.typography.titleMedium,
        maxLines = 1
    )
}

@Composable
private fun ShadowedOutlinedTextField(
    value: String,
    onValueChange: (String) -> Unit,
    enabled: Boolean,
    isFocused: Boolean,
    onFocusChanged: (Boolean) -> Unit,
    placeholder: String,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) {
    Box(
        modifier = Modifier
            .padding(bottom = Dimension.paddingComponentSmall, end = Dimension.paddingComponentSmall)
    ) {
        // Fake Shadow
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(x = Dimension.shadowOffsetX, y = Dimension.shadowOffsetY)
                .background(
                    color = MultilineTextFieldDefaults.shadowColor(enabled, isFocused),
                    shape = LelloShape.textFieldShape
                )
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimension.textFieldMultilineHeight)
                .border(
                    width = Dimension.borderWidthThick,
                    color = MultilineTextFieldDefaults.borderColor(enabled, isFocused),
                    shape = LelloShape.textFieldShape
                )
                .onFocusChanged { focusState ->
                    onFocusChanged(focusState.isFocused)
                },
            enabled = enabled,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MultilineTextFieldDefaults.textColor(enabled)
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MultilineTextFieldDefaults.placeholderColor(enabled),
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = 7,
            shape = LelloShape.textFieldShape,
            colors = MultilineTextFieldDefaults.textFieldColorScheme()
        )
    }
}

@Composable
private fun CharacterCounter(
    currentLength: Int,
    maxLength: Int,
    showCounter: Boolean,
    enabled: Boolean
) {
    if (showCounter) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = Dimension.paddingComponentSmall),
            horizontalArrangement = Arrangement.End
        ) {
            Text(
                text = "$currentLength de $maxLength",
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MultilineTextFieldDefaults.textColor(enabled)
            )
        }
    }
}

object MultilineTextFieldDefaults {
    @Composable
    fun textLabelColor(enabled: Boolean, focused: Boolean): Color = when {
        !enabled -> MaterialTheme.colorScheme.outlineVariant
        focused -> MaterialTheme.colorScheme.onSecondaryContainer
        else -> MaterialTheme.colorScheme.onPrimary
    }

    @Composable
    fun textColor(enabled: Boolean): Color = when (enabled) {
        true -> MaterialTheme.colorScheme.onSurface
        false -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    @Composable
    fun placeholderColor(enabled: Boolean): Color = when (enabled) {
        true -> MaterialTheme.colorScheme.surfaceVariant
        false -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    @Composable
    fun borderColor(enabled: Boolean, focused: Boolean): Color = when {
        !enabled -> MaterialTheme.colorScheme.outlineVariant
        focused -> MaterialTheme.colorScheme.outline
        else -> MaterialTheme.colorScheme.outline
    }

    @Composable
    fun shadowColor(enabled: Boolean, focused: Boolean): Color = when {
        !enabled -> MaterialTheme.colorScheme.scrim.copy(alpha = Dimension.alphaStateDisabled)
        focused -> MaterialTheme.colorScheme.scrim.copy(alpha = Dimension.alphaStatePressed)
        else -> MaterialTheme.colorScheme.scrim.copy(alpha = Dimension.alphaStateNormal)
    }

    @Composable
    fun textFieldColorScheme(): TextFieldColors = TextFieldDefaults.colors(
        focusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerLowest,
        disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
        disabledTextColor = MaterialTheme.colorScheme.surfaceVariant,
        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
    )
}

// region: Preview Light Theme

@Preview(
    name = "Default Multiline Text Field",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
fun MultilineTextFieldPreview_LightTheme_Default() {
    LelloTheme {
        LelloMultilineTextField(
            label = "Sample Text Area",
            value = "This is a sample text area.",
            onValueChange = {},
            placeholder = "Type something here...",
            maxLength = 500,
            showCounter = true,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled Multiline Text Field",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
fun MultilineTextFieldPreview_LightTheme_Disabled() {
    LelloTheme {
        LelloMultilineTextField(
            label = "Sample Text Area",
            value = "This is a sample text area.",
            onValueChange = {},
            placeholder = "Type something here...",
            maxLength = 500,
            showCounter = true,
            enabled = false,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Without Label Multiline Text Field",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
fun MultilineTextFieldPreview_LightTheme_WithoutLabel() {
    LelloTheme {
        LelloMultilineTextField(
            value = "This is a sample text area.",
            onValueChange = {},
            placeholder = "Type something here...",
            maxLength = 500,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Light Theme

// region: Preview Dark Theme

@Preview(
    name = "Default Multiline Text Field",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
fun MultilineTextFieldPreview_DarkTheme_Default() {
    LelloTheme(darkTheme = true) {
        LelloMultilineTextField(
            label = "Sample Text Area",
            value = "This is a sample text area.",
            onValueChange = {},
            placeholder = "Type something here...",
            maxLength = 500,
            showCounter = true,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled Multiline Text Field",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
fun MultilineTextFieldPreview_DarkTheme_Disabled() {
    LelloTheme(darkTheme = true) {
        LelloMultilineTextField(
            label = "Sample Text Area",
            value = "This is a sample text area.",
            onValueChange = {},
            placeholder = "Type something here...",
            maxLength = 500,
            showCounter = true,
            enabled = false,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Without Label Multiline Text Field",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
fun MultilineTextFieldPreview_DarkTheme_WithoutLabel() {
    LelloTheme(darkTheme = true) {
        LelloMultilineTextField(
            value = "This is a sample text area.",
            onValueChange = {},
            placeholder = "Type something here...",
            maxLength = 500,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Dark Theme

// region: Preview Inverse Theme

@Preview(
    name = "Default Multiline Text Field",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
fun MultilineTextFieldPreview_InverseTheme_Default() {
    LelloTheme {
        LelloMultilineTextField(
            label = "Sample Text Area",
            value = "This is a sample text area.",
            onValueChange = {},
            placeholder = "Type something here...",
            maxLength = 500,
            showCounter = true,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled Multiline Text Field",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
fun MultilineTextFieldPreview_InverseTheme_Disabled() {
    LelloTheme {
        LelloMultilineTextField(
            label = "Sample Text Area",
            value = "This is a sample text area.",
            onValueChange = {},
            placeholder = "Type something here...",
            maxLength = 500,
            showCounter = true,
            enabled = false,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Without Label Multiline Text Field",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
fun MultilineTextFieldPreview_InverseTheme_WithoutLabel() {
    LelloTheme {
        LelloMultilineTextField(
            value = "This is a sample text area.",
            onValueChange = {},
            placeholder = "Type something here...",
            maxLength = 500,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Inverse Theme