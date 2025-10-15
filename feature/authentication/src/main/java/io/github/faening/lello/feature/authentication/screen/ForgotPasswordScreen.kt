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
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.textfield.LelloEmailTextField
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.feature.authentication.AuthenticationUiState
import io.github.faening.lello.feature.authentication.AuthenticationViewModel
import kotlinx.coroutines.delay

@Composable
internal fun ForgotPasswordScreen(
    viewModel: AuthenticationViewModel,
    onBackClick: () -> Unit = {},
    onPasswordResetSuccess: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.isPasswordResetSuccessful) {
        if (uiState.isPasswordResetSuccessful) {
            onPasswordResetSuccess()
            viewModel.resetPasswordResetState()
        }
    }

    ForgotPasswordScreenContent(
        uiState = uiState,
        onBackClick = onBackClick,
        onSendResetEmail = viewModel::sendPasswordResetEmail,
        onErrorDismiss = { viewModel.clearError() }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ForgotPasswordScreenContent(
    uiState: AuthenticationUiState,
    onBackClick: () -> Unit,
    onSendResetEmail: (String) -> Unit,
    onErrorDismiss: () -> Unit,
    moodColor: MoodColor = MoodColor.INVERSE
) {
    var email by remember(uiState.savedEmail) { mutableStateOf(uiState.savedEmail ?: "") }
    var countdown by remember { mutableIntStateOf(0) }
    val snackbarHostState = remember { SnackbarHostState() }

    // Timer para countdown
    LaunchedEffect(countdown) {
        if (countdown > 0) {
            delay(1000L)
            countdown--
        }
    }

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
                    title = TopAppBarTitle(text = "Recuperar Senha"),
                    navigateUp = TopAppBarAction(
                        icon = LelloIcons.Outlined.ArrowLeftLarge.imageVector,
                        contentDescription = "Voltar",
                        onClick = onBackClick
                    ),
                    moodColor = moodColor
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
                    text = "Digite seu e-mail abaixo e enviaremos instruções para redefinir sua senha. \n\n" +
                            "Verifique também sua caixa de spam caso não encontre o e-mail na caixa de entrada.",
                    style = MaterialTheme.typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
                )

                LelloEmailTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
                )

                LelloFilledButton(
                    label = when {
                        uiState.isLoading -> "Enviando..."
                        countdown > 0 -> "Aguarde ${countdown}s"
                        else -> "Enviar e-mail"
                    },
                    onClick = {
                        onSendResetEmail(email)
                        countdown = 40
                    },
                    enabled = email.isNotEmpty() && !uiState.isLoading && countdown == 0,
                    moodColor = moodColor,
                    modifier = Modifier.padding(bottom = Dimension.spacingMedium)
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
private fun ForgotPasswordScreenPreview_LightMode() {
    ForgotPasswordScreenContent(
        uiState = AuthenticationUiState(),
        onBackClick = {},
        onSendResetEmail = {},
        onErrorDismiss = {}
    )
}
