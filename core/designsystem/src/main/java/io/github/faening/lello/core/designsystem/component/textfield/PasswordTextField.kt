package io.github.faening.lello.core.designsystem.component.textfield

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme

@Composable
fun LelloPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String = "Senha",
    placeholder: String = "Digite sua senha",
    enabled: Boolean = true,
    validationConfig: PasswordValidationConfig = PasswordValidationConfig(),
    showValidationErrors: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    modifier: Modifier = Modifier
) {
    var passwordVisible by remember { mutableStateOf(false) }
    var isFocused by remember { mutableStateOf(false) }

    val validationResult = remember(value, validationConfig) {
        if (value.isNotEmpty()) {
            PasswordValidator.validate(value, validationConfig)
        } else {
            PasswordValidationResult(isValid = true, errors = emptyList())
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
            visualTransformation = if (passwordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                PasswordVisibilityIcon(
                    passwordVisible = passwordVisible,
                    onToggle = { passwordVisible = !passwordVisible },
                    enabled = enabled
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password,
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
    val textColor = when {
        !enabled -> MaterialTheme.colorScheme.outlineVariant
        isFocused -> MaterialTheme.colorScheme.onSecondaryContainer
        else -> MaterialTheme.colorScheme.onPrimary
    }

    Text(
        text = text,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimension.paddingComponentSmall),
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
    visualTransformation: VisualTransformation,
    trailingIcon: @Composable (() -> Unit)?,
    keyboardOptions: KeyboardOptions,
    keyboardActions: KeyboardActions
) {
    val shadowColor = when {
        !enabled -> MaterialTheme.colorScheme.scrim.copy(alpha = Dimension.alphaStateDisabled)
        isFocused -> MaterialTheme.colorScheme.scrim.copy(alpha = Dimension.alphaStatePressed)
        else -> MaterialTheme.colorScheme.scrim.copy(alpha = Dimension.alphaStateNormal)
    }
    val borderColor = when {
        !enabled -> MaterialTheme.colorScheme.outlineVariant
        isFocused -> MaterialTheme.colorScheme.outline
        else -> MaterialTheme.colorScheme.outline
    }

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
                    color = shadowColor,
                    shape = LelloShape.buttonShape
                )
        )

        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = Dimension.borderWidthDefault,
                    color = borderColor,
                    shape = LelloShape.buttonShape
                )
                .onFocusChanged { focusState ->
                    onFocusChanged(focusState.isFocused)
                },
            enabled = enabled,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface
            ),
            placeholder = {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = if (enabled) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            visualTransformation = visualTransformation,
            trailingIcon = trailingIcon,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true,
            maxLines = 1,
            shape = LelloShape.buttonShape,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.surface,
                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
                disabledTextColor = MaterialTheme.colorScheme.surfaceVariant,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )
    }
}

@Composable
private fun PasswordVisibilityIcon(
    passwordVisible: Boolean,
    onToggle: () -> Unit,
    enabled: Boolean
) {
    IconButton(
        onClick = onToggle,
        enabled = enabled
    ) {
        Icon(
            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
            contentDescription = if (passwordVisible) "Esconder senha" else "Mostrar senha",
            tint = if (enabled) MaterialTheme.colorScheme.onSurface else MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
private fun ValidationErrorText(
    validationResult: PasswordValidationResult,
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

/**
 * Define as regras de validação para a senha.
 *
 * @param minLength O comprimento mínimo da senha. Padrão é 8.
 * @param requireUppercase Se deve exigir pelo menos uma letra maiúscula. Padrão é true.
 * @param requireLowercase Se deve exigir pelo menos uma letra minúscula. Padrão é true.
 * @param requireDigits Se deve exigir pelo menos um dígito numérico. Padrão é true.
 * @param requireSpecialChars Se deve exigir pelo menos um caractere especial. Padrão é true.
 * @param specialChars A string de caracteres especiais permitidos. Padrão é "!@#$%^&*()_+-=[]{}|;:,.<>?"
 */
data class PasswordValidationConfig(
    val minLength: Int = 8,
    val requireUppercase: Boolean = true,
    val requireLowercase: Boolean = true,
    val requireDigits: Boolean = true,
    val requireSpecialChars: Boolean = true,
    val specialChars: String = "!@#$%^&*()_+-=[]{}|;:,.<>?"
)

/**
 * Resultado da validação da senha.
 *
 * @param isValid Indica se a senha é válida.
 * @param errors Lista de mensagens de erro, se houver.
 */
private data class PasswordValidationResult(
    val isValid: Boolean,
    val errors: List<String>
)

private object PasswordValidator {
    /**
     * Valida senhas com base na configuração fornecida. Retorna um [PasswordValidationResult] indicando se a senha é
     * válida e quaisquer mensagens de erro.
     *
     * @param password A senha a ser validada.
     * @param config A configuração de validação a ser usada.
     * @return Um [PasswordValidationResult] com o resultado da validação.
     */
    fun validate(password: String, config: PasswordValidationConfig): PasswordValidationResult {
        val errors = mutableListOf<String>()

        if (password.length < config.minLength) {
            errors.add("A senha deve ter pelo menos ${config.minLength} caracteres")
        }

        if (config.requireUppercase && !password.any { it.isUpperCase() }) {
            errors.add("A senha deve conter pelo menos uma letra maiúscula")
        }

        if (config.requireLowercase && !password.any { it.isLowerCase() }) {
            errors.add("A senha deve conter pelo menos uma letra minúscula")
        }

        if (config.requireDigits && !password.any { it.isDigit() }) {
            errors.add("A senha deve conter pelo menos um número")
        }

        if (config.requireSpecialChars && !password.any { config.specialChars.contains(it) }) {
            errors.add("A senha deve conter pelo menos um caractere especial")
        }

        return PasswordValidationResult(
            isValid = errors.isEmpty(),
            errors = errors
        )
    }
}

// region: Preview Light Theme

@Preview(
    name = "Default Password Field",
    group = "Light Theme",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun PasswordTextFieldPreview_LightTheme_Default() {
    LelloTheme {
        var password by remember { mutableStateOf("") }
        LelloPasswordTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled Password Field",
    group = "Light Theme",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0
)
@Composable
private fun PasswordTextFieldPreview_LightTheme_Disabled() {
    LelloTheme {
        var password by remember { mutableStateOf("") }
        LelloPasswordTextField(
            value = password,
            onValueChange = { password = it },
            enabled = false,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
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
private fun PasswordTextFieldPreview_LightTheme_WithErrors() {
    LelloTheme {
        var password by remember { mutableStateOf("123") }
        LelloPasswordTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Light Theme

// region: Preview Dark Theme

@Preview(
    name = "Default Password Field",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun PasswordTextFieldPreview_DarkTheme_Default() {
    LelloTheme(darkTheme = true) {
        var password by remember { mutableStateOf("") }
        LelloPasswordTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled Password Field",
    group = "Dark Theme",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    backgroundColor = 0xFF262626
)
@Composable
private fun PasswordTextFieldPreview_DarkTheme_Disabled() {
    LelloTheme(darkTheme = true) {
        var password by remember { mutableStateOf("") }
        LelloPasswordTextField(
            value = password,
            enabled = false,
            onValueChange = { password = it },
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
private fun PasswordTextFieldPreview_DarkTheme_WithErrors() {
    LelloTheme(darkTheme = true) {
        var password by remember { mutableStateOf("123") }
        LelloPasswordTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Dark Theme

// region: Preview Inverse Theme

@Preview(
    name = "Default Password Field",
    group = "Inverse Theme",
    showBackground = true,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun PasswordTextFieldPreview_InverseTheme_Default() {
    LelloTheme {
        var password by remember { mutableStateOf("") }
        LelloPasswordTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

@Preview(
    name = "Disabled Password Field",
    group = "Inverse Theme",
    showBackground = true,
    backgroundColor = 0xFFFBD866
)
@Composable
private fun PasswordTextFieldPreview_InverseTheme_Disabled() {
    LelloTheme {
        var password by remember { mutableStateOf("") }
        LelloPasswordTextField(
            value = password,
            onValueChange = { password = it },
            enabled = false,
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
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
private fun PasswordTextFieldPreview_InverseTheme_WithErrors() {
    LelloTheme {
        var password by remember { mutableStateOf("123") }
        LelloPasswordTextField(
            value = password,
            onValueChange = { password = it },
            modifier = Modifier.padding(Dimension.paddingScreenHorizontal)
        )
    }
}

// endregion: Preview Inverse Theme