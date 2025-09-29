package io.github.faening.lello.core.designsystem.component.textfield

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme

@Composable
fun LelloTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    placeholder: String = "",
    maxLength: Int = 60,
    enabled: Boolean = true,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
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
        color = TextFieldProperties.textLabelColor(enabled, isFocused),
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
                    color = TextFieldProperties.shadowColor(enabled, isFocused),
                    shape = LelloShape.textFieldShape
                )
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .height(Dimension.heightTextFieldDefault)
                .border(
                    width = Dimension.borderWidthDefault,
                    color = TextFieldProperties.borderColor(enabled, isFocused),
                    shape = LelloShape.textFieldShape
                )
                .onFocusChanged { focusState ->
                    onFocusChanged(focusState.isFocused)
                },
            enabled = enabled,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = TextFieldProperties.textColor(enabled)
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = TextFieldProperties.textColor(enabled),
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            maxLines = 1,
            shape = LelloShape.textFieldShape,
            colors = TextFieldProperties.textFieldColorScheme()
        )
    }
}

// region: Preview Light Theme

@Preview(
    name = "Default Text Field",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
fun TextFieldPreview_LightTheme_Default() {
    LelloTheme {
        LelloTextField(
            label = "Sample Text Field",
            value = "This is a sample text field.",
            onValueChange = {},
            placeholder = "Type something here...",
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled Text Field",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
fun TextFieldPreview_LightTheme_Disabled() {
    LelloTheme {
        LelloTextField(
            label = "Sample Text Field",
            value = "This is a sample text field.",
            onValueChange = {},
            placeholder = "Type something here...",
            enabled = false,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Without Label Text Field",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
fun TextFieldPreview_LightTheme_WithoutLabel() {
    LelloTheme {
        LelloTextField(
            value = "This is a sample text field.",
            onValueChange = {},
            placeholder = "Type something here...",
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Light Theme

// region: Preview Dark Theme

@Preview(
    name = "Default Text Field",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
fun TextFieldPreview_DarkTheme_Default() {
    LelloTheme(darkTheme = true) {
        LelloTextField(
            label = "Sample Text Field",
            value = "This is a sample text field.",
            onValueChange = {},
            placeholder = "Type something here...",
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled Text Field",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
fun TextFieldPreview_DarkTheme_Disabled() {
    LelloTheme(darkTheme = true) {
        LelloTextField(
            label = "Sample Text Field",
            value = "This is a sample text field.",
            onValueChange = {},
            placeholder = "Type something here...",
            enabled = false,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Without Label Text Field",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
fun TextFieldPreview_DarkTheme_WithoutLabel() {
    LelloTheme(darkTheme = true) {
        LelloTextField(
            value = "This is a sample text field.",
            onValueChange = {},
            placeholder = "Type something here...",
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Dark Theme

// region: Preview Inverse Theme

@Preview(
    name = "Default Text Field",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
fun TextFieldPreview_InverseTheme_Default() {
    LelloTheme {
        LelloTextField(
            label = "Sample Text Field",
            value = "This is a sample text field.",
            onValueChange = {},
            placeholder = "Type something here...",
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled Text Field",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
fun TextFieldPreview_InverseTheme_Disabled() {
    LelloTheme {
        LelloTextField(
            label = "Sample Text Field",
            value = "This is a sample text field.",
            onValueChange = {},
            placeholder = "Type something here...",
            enabled = false,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Without Label Text Field",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
fun TextFieldPreview_InverseTheme_WithoutLabel() {
    LelloTheme {
        LelloTextField(
            value = "This is a sample text field.",
            onValueChange = {},
            placeholder = "Type something here...",
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Inverse Theme