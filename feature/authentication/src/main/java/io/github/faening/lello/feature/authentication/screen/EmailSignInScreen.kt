package io.github.faening.lello.feature.authentication.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
internal fun EmailSignInScreen(
    viewModel: AuthenticationViewModel,
    onBackClick: () -> Unit = {},
    onSignInSuccess: () -> Unit = {},
) {
    val uiState by viewModel.uiState.collectAsState()

    // Observar mudanças no estado e navegar quando o sign in for bem-sucedido
    LaunchedEffect(uiState.isSignInSuccessful) {
        if (uiState.isSignInSuccessful) {
            onSignInSuccess()
            viewModel.resetSignInState()
        }
    }

    EmailSignInScreenContent(
        uiState = uiState,
        onBackClick = onBackClick,
        onLoginClick = viewModel::signInWithEmailAndPassword,
        onErrorDismiss = { viewModel.clearError() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EmailSignInScreenContent(
    uiState: AuthenticationUiState,
    onBackClick: () -> Unit,
    onLoginClick: (String, String) -> Unit,
    onErrorDismiss: () -> Unit,
    moodColor: MoodColor = MoodColor.INVERSE
) {
    var email by remember(uiState.savedEmail) { mutableStateOf(uiState.savedEmail ?: "") }
    var password by remember { mutableStateOf("") }
    val snackbarHostState = remember { SnackbarHostState() }

    // Mostrar mensagem de erro na Snackbar
    if (uiState.errorMessage != null) {
        LaunchedEffect(uiState.errorMessage) {
            snackbarHostState.showSnackbar(uiState.errorMessage)
            onErrorDismiss()
        }
    }

    LelloTheme(moodColor) {
        Scaffold(
            topBar = {
                LelloTopAppBar(
                    title = TopAppBarTitle(text = "Entrar"),
                    navigateUp = TopAppBarAction(
                        icon = LelloIcons.Outlined.ArrowLeftLarge.imageVector,
                        contentDescription = "Voltar",
                        onClick = onBackClick
                    )
                )
            },
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            containerColor = MaterialTheme.colorScheme.inversePrimary
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(Dimension.spacingRegular),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Acesse sua conta e registre seus momentos",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
                )
                LelloEmailTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.padding(bottom = Dimension.spacingRegular)
                )
                LelloPasswordTextField(
                    value = password,
                    onValueChange = { password = it },
                    modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
                )
                LelloFilledButton(
                    label = if (uiState.isLoading) "Entrando..." else "Entrar",
                    onClick = { onLoginClick(email, password) },
                    enabled = email.isNotEmpty() && password.isNotEmpty() && !uiState.isLoading,
                    moodColor = moodColor
                )
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
private fun EmailLoginScreenPreview_LightMode() {
    EmailSignInScreenContent(
        uiState = AuthenticationUiState(),
        onBackClick = {},
        onLoginClick = { _, _ -> },
        onErrorDismiss = {}
    )
}

@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun EmailLoginScreenPreview_DarkMode() {
    EmailSignInScreenContent(
        uiState = AuthenticationUiState(),
        onBackClick = {},
        onLoginClick = { _, _ -> },
        onErrorDismiss = {}
    )
}
