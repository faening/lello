package io.github.faening.lello.feature.authentication.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.FragmentActivity
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.feature.authentication.AuthenticationUiState
import io.github.faening.lello.feature.authentication.AuthenticationViewModel
import kotlinx.coroutines.delay

@Composable
internal fun BiometricAuthenticationScreen(
    viewModel: AuthenticationViewModel,
    onNavigateToEmailSignIn: () -> Unit,
    onSignInSuccess: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    // Inicia autenticação biométrica automaticamente se disponível
    LaunchedEffect(key1 = true) {
        delay(200)
        val biometricAvailable = viewModel.canUseBiometricAuth.value
        val hasSavedEmail = uiState.savedEmail != null

        if (biometricAvailable && hasSavedEmail) {
            runCatching {
                viewModel.authenticateWithBiometric(context as FragmentActivity)
            }.onFailure { e ->
                e.printStackTrace()
            }
        }
    }

    // Monitora resultado da autenticação
    LaunchedEffect(uiState.isSignInSuccessful) {
        if (uiState.isSignInSuccessful) {
            onSignInSuccess()
            viewModel.resetSignInState()
        }
    }

    BiometricAuthenticationContent(
        uiState = uiState,
        onTryAgain = { viewModel.authenticateWithBiometric(context as FragmentActivity) },
        onErrorDismiss = { viewModel.clearError() },
        onNavigateToEmailSignIn = onNavigateToEmailSignIn
    )
}

@Composable
private fun BiometricAuthenticationContent(
    uiState: AuthenticationUiState,
    onTryAgain: () -> Unit,
    onErrorDismiss: () -> Unit,
    onNavigateToEmailSignIn: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    // Mostrar erro, se houver
    uiState.errorMessage?.let { message ->
        LaunchedEffect(message) {
            snackbarHostState.showSnackbar(message)
            onErrorDismiss()
        }
    }

    LelloTheme(MoodColor.INVERSE) {
        Scaffold(
            snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            containerColor = MaterialTheme.colorScheme.inversePrimary
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(Dimension.spacingRegular)
            ) {
                // Conteúdo principal (centralizado)
                Column(
                    modifier = Modifier.fillMaxWidth().weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        imageVector = LelloIcons.Outlined.Fingerprint.imageVector,
                        contentDescription = "Biometria",
                        modifier = Modifier
                            .size(104.dp)
                            .padding(bottom = Dimension.spacingExtraLarge),
                    )
                    Text(
                        text = "Use sua digital para desbloquear o aplicativo",
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge),
                    )
                }

                LelloFilledButton(
                    label = "Usar Digital",
                    onClick = onTryAgain,
                    moodColor = MoodColor.INVERSE,
                    modifier = Modifier.fillMaxWidth().padding(bottom = Dimension.spacingRegular),
                )
                LelloFilledButton(
                    label = "Entrar com e-mail e senha",
                    onClick = onNavigateToEmailSignIn,
                    moodColor = MoodColor.SECONDARY,
                    modifier = Modifier.fillMaxWidth().padding(bottom = Dimension.spacingExtraLarge),
                )

                uiState.savedEmail?.let { email ->
                    Text(
                        text = email,
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = Dimension.spacingRegular)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun BiometricAuthenticationContentPreview() {
    BiometricAuthenticationContent(
        uiState = AuthenticationUiState(savedEmail = "exemplo@email.com"),
        onTryAgain = {},
        onErrorDismiss = {},
        onNavigateToEmailSignIn = {}
    )
}