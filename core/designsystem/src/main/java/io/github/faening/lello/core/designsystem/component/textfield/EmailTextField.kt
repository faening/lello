package io.github.faening.lello.core.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.Grey100
import io.github.faening.lello.core.designsystem.theme.Grey300
import io.github.faening.lello.core.designsystem.theme.Grey500
import io.github.faening.lello.core.designsystem.theme.Grey700
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.Yellow50

@Composable
fun LelloEmailTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "E-mail",
    placeholder: String = "Digite seu e-mail",
    enabled: Boolean = true,
    showValidationErrors: Boolean = true,
    imeAction: ImeAction = ImeAction.Done
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
            )
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
    val textColor = when {
        !enabled -> MaterialTheme.colorScheme.outlineVariant
        isFocused -> MaterialTheme.colorScheme.onSecondaryContainer
        else -> MaterialTheme.colorScheme.onBackground
    }

    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimension.PADDING_COMPONENT_SMALL),
        color = textColor,
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
    keyboardOptions: KeyboardOptions
) {
    val shadowColor = when {
        !enabled -> MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = Dimension.ALPHA_STATE_DISABLED)
        isFocused -> MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = Dimension.ALPHA_STATE_PRESSED)
        else -> MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = Dimension.ALPHA_STATE_NORMAL)
    }
    val borderColor = when {
        !enabled -> MaterialTheme.colorScheme.outlineVariant
        isFocused -> MaterialTheme.colorScheme.onSecondaryContainer
        else -> MaterialTheme.colorScheme.outline
    }

    Box(
        modifier = Modifier
            .padding(bottom = Dimension.PADDING_COMPONENT_SMALL, end = Dimension.PADDING_COMPONENT_SMALL)
    ) {
        Box(
            modifier = Modifier
                .matchParentSize()
                .offset(x = Dimension.SHADOW_OFFSET_X, y = Dimension.SHADOW_OFFSET_Y)
                .background(
                    color = shadowColor,
                    shape = RoundedCornerShape(Dimension.Small)
                )
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = Dimension.BORDER_WIDTH_DEFAULT,
                    color = borderColor,
                    shape = RoundedCornerShape(Dimension.BORDER_RADIUS_MEDIUM)
                )
                .onFocusChanged { focusState ->
                    onFocusChanged(focusState.isFocused)
                },
            enabled = enabled,
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = if (enabled) Grey500 else Grey300,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            keyboardOptions = keyboardOptions,
            singleLine = true,
            maxLines = 1,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            shape = RoundedCornerShape(Dimension.Small),
            colors = TextFieldDefaults.colors(
                // Background
                focusedContainerColor = Yellow50,
                unfocusedContainerColor = Yellow50,
                disabledContainerColor = Grey100,

                // Text
                focusedTextColor = Grey700,
                unfocusedTextColor = Grey500,
                disabledTextColor = Grey300,

                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent,
                cursorColor = MaterialTheme.colorScheme.primary
            )
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
        Box(modifier = Modifier.padding(top = Dimension.PADDING_COMPONENT_SMALL)) {
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
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun EmailTextField_PreviewLightTheme_Default() {
    LelloTheme(scheme = LelloColorScheme.DEFAULT) {
        var email by remember { mutableStateOf("") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(
    name = "Disabled Email Field",
    group = "Light Theme",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun EmailTextField_PreviewLightTheme_Disabled() {
    LelloTheme(scheme = LelloColorScheme.DEFAULT) {
        var email by remember { mutableStateOf("") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            enabled = false,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(
    name = "With Validation Errors",
    group = "Light Theme",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun EmailTextField_PreviewLightTheme_WithErrors() {
    LelloTheme(scheme = LelloColorScheme.DEFAULT) {
        var email by remember { mutableStateOf("usuario@") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(16.dp),
            label = "E-mail"
        )
    }
}

// endregion: Preview Light Theme

// region: Preview Inverse Theme

@Preview(
    name = "Default Email Field",
    group = "Inverse Theme",
    showBackground = true,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun EmailTextField_PreviewInverseTheme_Default() {
    LelloTheme(scheme = LelloColorScheme.INVERSE) {
        var email by remember { mutableStateOf("") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(
    name = "Disabled Email Field",
    group = "Inverse Theme",
    showBackground = true,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun EmailTextField_PreviewInverseTheme_Disabled() {
    LelloTheme(scheme = LelloColorScheme.INVERSE) {
        var email by remember { mutableStateOf("") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            enabled = false,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(
    name = "With Validation Errors",
    group = "Inverse Theme",
    showBackground = true,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun EmailTextField_PreviewInverseTheme_WithErrors() {
    LelloTheme(scheme = LelloColorScheme.INVERSE) {
        var email by remember { mutableStateOf("usuario@") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(16.dp),
            label = "E-mail"
        )
    }
}

// endregion: Preview Inverse Theme

// region: Preview Dark Theme

@Preview(
    name = "Default Email Field",
    group = "Dark Theme",
    showBackground = true,
    backgroundColor = 0xFF262626
)
@Composable
private fun EmailTextField_PreviewDarkTheme_Default() {
    LelloTheme(darkTheme =  true) {
        var email by remember { mutableStateOf("") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(
    name = "Default Email Field",
    group = "Dark Theme",
    showBackground = true,
    backgroundColor = 0xFF262626
)
@Composable
private fun EmailTextField_PreviewDarkTheme_Disabled() {
    LelloTheme(darkTheme =  true) {
        var email by remember { mutableStateOf("") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            enabled = false,
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(
    name = "With Validation Errors",
    group = "Dark Theme",
    showBackground = true,
    backgroundColor = 0xFF262626
)
@Composable
private fun EmailTextField_PreviewDarkTheme_WithErrors() {
    LelloTheme(darkTheme =  true) {
        var email by remember { mutableStateOf("usuario@") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(16.dp),
            label = "E-mail"
        )
    }
}

// endregion: Preview Dark Theme
