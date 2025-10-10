package io.github.faening.lello.feature.diary.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.theme.Dimension

@Composable
internal fun AuthenticationDialog(
    onDismiss: () -> Unit,
    onPasswordSubmit: (String) -> Unit,
    errorMessage: String? = null,
    isLoading: Boolean = false
) {
    var password by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text(
                text = "Autenticação Necessária",
                style = MaterialTheme.typography.headlineSmall
            )
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Digite sua senha para acessar os detalhes do diário",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(bottom = Dimension.spacingRegular)
                )
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Senha") },
                    visualTransformation = PasswordVisualTransformation(),
                    singleLine = true,
                    enabled = !isLoading,
                    modifier = Modifier.fillMaxWidth()
                )
                if (errorMessage != null) {
                    Spacer(modifier = Modifier.height(Dimension.spacingSmall))
                    Text(
                        text = errorMessage,
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
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
                    Text("Confirmar")
                }
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss,
                enabled = !isLoading
            ) {
                Text("Cancelar")
            }
        }
    )
}

@Preview(
    name = "Default",
    group = "Light Mode"
)
@Composable
private fun AuthenticationDialogPreview_LightMode() {
    AuthenticationDialog(
        onDismiss = {},
        onPasswordSubmit = {},
        errorMessage = "Senha incorreta",
        isLoading = false
    )
}