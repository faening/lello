package io.github.faening.lello.core.designsystem.component.dialog

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme

@Composable
fun LelloAuthenticationDialog(
    onDismiss: () -> Unit,
    onPasswordSubmit: (String) -> Unit,
    errorMessage: String? = null,
    isLoading: Boolean = false
) {
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    LelloTheme {
        AlertDialog(
            onDismissRequest = onDismiss,
            icon = {
                Icon(
                    imageVector = LelloIcons.Outlined.Lock.imageVector,
                    contentDescription = null,
                    tint = AuthenticationDialogProperties.textColor()
                )
            },
            title = {
                Text(
                    text = "Autenticação Necessária",
                    style = MaterialTheme.typography.titleLarge,
                    color = AuthenticationDialogProperties.titleColor()
                )
            },
            text = {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Digite sua senha para acessar os detalhes do diário.",
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.bodyMedium,
                        color = AuthenticationDialogProperties.textColor()
                    )
                    Spacer(modifier = Modifier.height(Dimension.spacingLarge))
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = {
                            Text(
                                text = "Digite sua senha",
                                style = MaterialTheme.typography.labelMedium,
                                color = AuthenticationDialogProperties.placeholderColor()
                            )
                        },
                        visualTransformation = if (passwordVisible) {
                            VisualTransformation.None
                        } else {
                            PasswordVisualTransformation()
                        },
                        singleLine = true,
                        enabled = !isLoading,
                        shape = LelloShape.textFieldShape,
                        trailingIcon = {
                            PasswordVisibilityIcon(
                                passwordVisible = passwordVisible,
                                onToggle = { passwordVisible = !passwordVisible }
                            )
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(Dimension.heightTextFieldDefault)
                            .border(
                                width = Dimension.borderWidthThin,
                                color = MaterialTheme.colorScheme.outlineVariant,
                                shape = LelloShape.textFieldShape
                            )
                    )
                    if (errorMessage != null) {
                        Text(
                            text = errorMessage,
                            color = MaterialTheme.colorScheme.error,
                            style = MaterialTheme.typography.bodySmall.copy(
                                fontWeight = FontWeight.Bold
                            ),
                            modifier = Modifier.padding(top = Dimension.spacingSmall)
                        )
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = { onPasswordSubmit(password) },
                    enabled = password.isNotEmpty() && !isLoading
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(modifier = Modifier.size(Dimension.spacingRegular))
                    } else {
                        Text(
                            text = "Confirmar",
                            style = MaterialTheme.typography.bodyMedium.copy(
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                }
            },
            dismissButton = {
                TextButton(
                    onClick = onDismiss,
                    enabled = !isLoading
                ) {
                    Text(
                        text = "Cancelar",
                        style = MaterialTheme.typography.bodyMedium.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                }
            }
        )
    }
}

@Composable
private fun PasswordVisibilityIcon(
    passwordVisible: Boolean,
    onToggle: () -> Unit
) {
    IconButton(
        onClick = onToggle
    ) {
        Icon(
            imageVector = if (passwordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff,
            contentDescription = if (passwordVisible) "Esconder senha" else "Mostrar senha",
            tint = AuthenticationDialogProperties.placeholderColor()
        )
    }
}

private object AuthenticationDialogProperties {
    @Composable
    fun titleColor(): Color {
        return MaterialTheme.colorScheme.onSurface
    }

    @Composable
    fun textColor(): Color {
        return MaterialTheme.colorScheme.onSurfaceVariant
    }

    @Composable
    fun placeholderColor(): Color {
        return MaterialTheme.colorScheme.surfaceVariant
    }
}

// region Previews

@Preview(
    name = "Default",
    group = "Light Mode"
)
@Composable
private fun AuthenticationDialogPreview_LightMode() {
    LelloAuthenticationDialog(
        onDismiss = {},
        onPasswordSubmit = {},
        errorMessage = "Senha incorreta",
        isLoading = false
    )
}

// endregion Previews