package io.github.faening.lello.feature.authentication.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.textfield.LelloEmailTextField
import io.github.faening.lello.core.designsystem.component.textfield.LelloPasswordTextField
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.feature.authentication.AuthenticationUiState
import io.github.faening.lello.feature.authentication.AuthenticationViewModel

@Composable
internal fun EmailSignUpScreen(
    viewModel: AuthenticationViewModel,
    onBackClick: () -> Unit = {},
    onSignUpSuccess: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    // Observar mudanças no estado e navegar quando o sign up for bem-sucedido
    LaunchedEffect(uiState.isSignUpSuccessful) {
        if (uiState.isSignUpSuccessful) {
            onSignUpSuccess()
            viewModel.resetSignUpState()
        }
    }

    EmailSignUpScreenContent(
        uiState = uiState,
        onBackClick = onBackClick,
        onSignUp = { email, password -> viewModel.signUpWithEmailAndPassword(email, password) },
        onErrorDismiss = { viewModel.clearError() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EmailSignUpScreenContent(
    uiState: AuthenticationUiState,
    onBackClick: () -> Unit,
    onSignUp: (String, String) -> Unit,
    onErrorDismiss: () -> Unit,
    moodColor: MoodColor = MoodColor.INVERSE
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }

    val isEmailValid = email.contains("@") && email.isNotBlank()
    val doPasswordsMatch = password == confirmPassword && confirmPassword.isNotBlank()
    val isFormValid = isEmailValid && doPasswordsMatch && password.isNotBlank()

    // Mostrar mensagem de erro na Snackbar
    LaunchedEffect(uiState.errorMessage) {
        uiState.errorMessage?.let { errorMessage ->
            snackbarHostState.showSnackbar(errorMessage)
            // Limpar a mensagem de erro após exibir
            onErrorDismiss()
        }
    }

    LelloTheme(moodColor) {
        Scaffold(
            topBar = {
                LelloTopAppBar(
                    title = TopAppBarTitle(text = "Criar conta"),
                    navigateUp = TopAppBarAction(
                        icon = LelloIcons.ArrowLargeLeft.imageVector,
                        contentDescription = "Voltar",
                        onClick = onBackClick
                    ),
                    moodColor = moodColor
                )
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            containerColor = MaterialTheme.colorScheme.inversePrimary
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(Dimension.spacingRegular),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Preencha os campos abaixo para criar sua conta no Lello",
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
                    )
                    LelloEmailTextField(
                        value = email,
                        onValueChange = { email = it },
                        modifier = Modifier.padding(bottom = Dimension.spacingMedium),
                        enabled = !uiState.isLoading
                    )
                    LelloPasswordTextField(
                        value = password,
                        onValueChange = { password = it },
                        modifier = Modifier.padding(bottom = Dimension.spacingMedium),
                        enabled = !uiState.isLoading
                    )
                    LelloPasswordTextField(
                        value = confirmPassword,
                        onValueChange = { confirmPassword = it },
                        label = "Confirmar senha",
                        placeholder = "Digite sua senha novamente",
                        modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge),
                        enabled = !uiState.isLoading
                    )
                    LelloFilledButton(
                        label = if (uiState.isLoading) "Criando conta..." else "Criar conta",
                        onClick = { onSignUp(email, password) },
                        enabled = isFormValid && !uiState.isLoading,
                        moodColor = moodColor
                    )
                }

                // Indicador de carregamento
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }
}

@Preview(
    name = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun EmailSignUpScreenPreview_LightMode() {
    EmailSignUpScreenContent(
        uiState = AuthenticationUiState(),
        onBackClick = {},
        onSignUp = { _, _ -> },
        onErrorDismiss = {}
    )
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun EmailSignUpScreenPreview_DarkMode() {
    EmailSignUpScreenContent(
        uiState = AuthenticationUiState(),
        onBackClick = {},
        onSignUp = { _, _ -> },
        onErrorDismiss = {}
    )
}
