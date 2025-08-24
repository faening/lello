package io.github.faening.lello.core.designsystem.component.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.Green300
import io.github.faening.lello.core.designsystem.theme.Grey100
import io.github.faening.lello.core.designsystem.theme.Grey300
import io.github.faening.lello.core.designsystem.theme.Grey50
import io.github.faening.lello.core.designsystem.theme.Grey500
import io.github.faening.lello.core.designsystem.theme.Yellow50

@Composable
fun LelloPasswordTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    label: String = "Senha",
    placeholder: String = "Digite sua senha",
    enabled: Boolean = true,
    validationConfig: PasswordValidationConfig = PasswordValidationConfig(),
    showValidationErrors: Boolean = true,
    imeAction: ImeAction = ImeAction.Done,
    keyboardActions: KeyboardActions = KeyboardActions.Default
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
        !enabled -> MaterialTheme.colorScheme.onSecondaryContainer.copy(alpha = Dimension.alphaStateDisabled)
        isFocused -> MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = Dimension.alphaStatePressed)
        else -> MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = Dimension.alphaStateNormal)
    }
    val borderColor = when {
        !enabled -> MaterialTheme.colorScheme.outlineVariant
        isFocused -> MaterialTheme.colorScheme.onSecondaryContainer
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
                    shape = RoundedCornerShape(Dimension.spacingSmall)
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
                    shape = RoundedCornerShape(Dimension.borderRadiusMedium)
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
            visualTransformation = visualTransformation,
            trailingIcon = trailingIcon,
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true,
            maxLines = 1,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Bold
            ),
            shape = RoundedCornerShape(Dimension.spacingSmall),
            colors = colorScheme()
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
            imageVector = if (passwordVisible) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            },
            contentDescription = if (passwordVisible) {
                "Esconder senha"
            } else {
                "Mostrar senha"
            },
            tint = if (enabled) {
                MaterialTheme.colorScheme.onSurfaceVariant
            } else {
                MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = Dimension.alphaStateDisabled)
            }
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
data class PasswordValidationResult(
    val isValid: Boolean,
    val errors: List<String>
)

object PasswordValidator {
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

@Composable
private fun colorScheme(): TextFieldColors {
    val isDarkTheme = isSystemInDarkTheme()

    return TextFieldDefaults.colors(
        // Background
        focusedContainerColor = Yellow50,   // if (isDarkTheme) M3ColorScheme.onBackground else M3ColorScheme.background,
        unfocusedContainerColor = Yellow50, // if (isDarkTheme) M3ColorScheme.onBackground else M3ColorScheme.background,
        disabledContainerColor = Grey100,   // M3ColorScheme.secondaryContainer,

        // Text
        focusedTextColor = if (isDarkTheme) Grey50 else Grey500,
        unfocusedTextColor = if (isDarkTheme) Grey50 else Grey500,
        disabledTextColor = Green300,

        focusedIndicatorColor = Color.Transparent,
        unfocusedIndicatorColor = Color.Transparent,
        disabledIndicatorColor = Color.Transparent,
        cursorColor = MaterialTheme.colorScheme.primary
    )
}