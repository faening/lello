package io.github.faening.lello.core.designsystem.component.textfield

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme

@Composable
fun LelloEmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "E-mail",
    placeholder: String = "Digite seu e-mail",
    enabled: Boolean = true,
    showValidationErrors: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    var isFocused by remember { mutableStateOf(false) }

    val validationResult = remember(value) {
        if (value.isNotEmpty()) {
            EmailValidator.validate(value)
        } else {
            EmailValidationResult(isValid = true, errors = emptyList())
        }
    }

    Column(modifier = modifier) {
        TextFieldLabel(
            text = label,
            enabled = enabled,
            isFocused = isFocused
        )

        ShadowedOutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            enabled = enabled,
            isFocused = isFocused,
            onFocusChanged = { isFocused = it },
            placeholder = placeholder,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = imeAction
            ),
            keyboardActions = keyboardActions
        )

        ValidationErrorText(
            validationResult = validationResult,
            showErrors = showValidationErrors,
            hasValue = value.isNotEmpty()
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
            leadingIcon = {
                Icon(
                    painter = painterResource(LelloIcons.Outlined.Mail.resId),
                    contentDescription = "Ícone de e-mail",
                    tint = TextFieldProperties.iconColor(enabled)
                )
            },
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true,
            maxLines = 1,
            shape = LelloShape.textFieldShape,
            colors = TextFieldProperties.textFieldColorScheme()
        )
    }
}

@Composable
private fun ValidationErrorText(
    validationResult: EmailValidationResult,
    showErrors: Boolean,
    hasValue: Boolean
) {
    val hasErrors = showErrors && hasValue && !validationResult.isValid

    if (hasErrors) {
        Box(modifier = Modifier.padding(top = Dimension.paddingComponentSmall)) {
            Text(
                text = validationResult.errors.first(),
                style = MaterialTheme.typography.bodySmall.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

private data class EmailValidationResult(
    val isValid: Boolean,
    val errors: List<String>
)

private object EmailValidator {
    fun validate(email: String): EmailValidationResult {
        val errors = mutableListOf<String>()
        val parts = email.split("@")
        if (parts.size != 2 || parts[0].isBlank() || parts[1].isBlank()) {
            errors.add("Digite um e-mail válido (ex: usuario@dominio.com)")
        }
        return EmailValidationResult(
            isValid = errors.isEmpty(),
            errors = errors
        )
    }
}

// region: Preview Light Theme

@Preview(
    name = "Default Email Field",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun EmailTextFieldPreview_LightTheme_Default() {
    LelloTheme {
        var email by remember { mutableStateOf("") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled Email Field",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun EmailTextFieldPreview_LightTheme_Disabled() {
    LelloTheme {
        var email by remember { mutableStateOf("") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            enabled = false,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "With Validation Errors",
    group = "Light Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun EmailTextFieldPreview_LightTheme_WithErrors() {
    LelloTheme {
        var email by remember { mutableStateOf("user") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Light Theme

// region: Preview Dark Theme

@Preview(
    name = "Default Email Field",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun EmailTextFieldPreview_DarkTheme_Default() {
    LelloTheme(darkTheme = true) {
        var email by remember { mutableStateOf("") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled Email Field",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun EmailTextFieldPreview_DarkTheme_Disabled() {
    LelloTheme(darkTheme = true) {
        var email by remember { mutableStateOf("") }
        LelloEmailTextField(
            value = email,
            enabled = false,
            onValueChange = { email = it },
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "With Validation Errors",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun EmailTextFieldPreview_DarkTheme_WithErrors() {
    LelloTheme(darkTheme = true) {
        var email by remember { mutableStateOf("user") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Dark Theme

// region: Preview Inverse Theme

@Preview(
    name = "Default Email Field",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun EmailTextFieldPreview_InverseTheme_Default() {
    LelloTheme {
        var email by remember { mutableStateOf("") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled Email Field",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun EmailTextFieldPreview_InverseTheme_Disabled() {
    LelloTheme {
        var email by remember { mutableStateOf("") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            enabled = false,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "With Validation Errors",
    group = "Inverse Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun EmailTextFieldPreview_InverseTheme_WithErrors() {
    LelloTheme {
        var email by remember { mutableStateOf("user") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Inverse Theme