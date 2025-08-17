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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme

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

    val textColor = when {
        !enabled -> MaterialTheme.colorScheme.outlineVariant
        isFocused -> MaterialTheme.colorScheme.onSecondaryContainer
        else -> MaterialTheme.colorScheme.onBackground
    }

    Column(modifier = modifier) {
        TextFieldLabel(
            text = label,
            textColor = textColor
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
            textColor = textColor
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
    textColor: Color
) {
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
    keyboardOptions: KeyboardOptions,
    textColor: Color
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
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(Dimension.BORDER_RADIUS_MEDIUM)
                )
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
                    style = MaterialTheme.typography.bodyLarge
                )
            },
            keyboardOptions = keyboardOptions,
            singleLine = true,
            maxLines = 1,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = textColor
            ),
            shape = RoundedCornerShape(Dimension.Small),
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
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
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.error
            )
        }
    }
}

data class EmailValidationResult(
    val isValid: Boolean,
    val errors: List<String>
)

object EmailValidator {
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

// region: Previews

@Preview(
    name = "Default Email Field",
    group = "LelloEmailTextField",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloEmailTextFieldDefaultPreview() {
    LelloTheme {
        var email by remember { mutableStateOf("") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Preview(
    name = "With Validation Errors",
    group = "LelloEmailTextField",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloEmailTextFieldWithErrorsPreview() {
    LelloTheme {
        var email by remember { mutableStateOf("usuario@") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(16.dp),
            label = "E-mail Institucional"
        )
    }
}

@Preview(
    name = "Valid Email",
    group = "LelloEmailTextField",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloEmailTextFieldValidPreview() {
    LelloTheme {
        var email by remember { mutableStateOf("usuario@dominio.com") }
        LelloEmailTextField(
            value = email,
            onValueChange = { email = it },
            modifier = Modifier.padding(16.dp),
            label = "E-mail Válido"
        )
    }
}

@Preview(
    name = "Disabled State",
    group = "LelloEmailTextField",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun LelloEmailTextFieldDisabledPreview() {
    LelloTheme {
        LelloEmailTextField(
            value = "usuario@dominio.com",
            onValueChange = {},
            modifier = Modifier.padding(16.dp),
            enabled = false,
            label = "Campo Desabilitado"
        )
    }
}

// endregion: Previews