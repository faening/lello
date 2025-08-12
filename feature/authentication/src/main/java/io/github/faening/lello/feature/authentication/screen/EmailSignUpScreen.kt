package io.github.faening.lello.feature.authentication.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.LelloOutlinedTextField
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.authentication.AuthenticationViewModel

@Composable
internal fun EmailSignUpScreen(
    viewModel: AuthenticationViewModel,
    onBackClick: () -> Unit = {},
    onSignUpSuccess: () -> Unit = {},
    onLoginClick: () -> Unit,
) {
    LelloTheme(scheme = LelloColorScheme.INVERSE) {
        EmailSignUpScreenContent(
            onBackClick = onBackClick,
            onSignUpSuccess = onSignUpSuccess,
            onLoginClick = onLoginClick
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EmailSignUpScreenContent(
    onBackClick: () -> Unit,
    onSignUpSuccess: () -> Unit = {},
    onLoginClick: () -> Unit
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            SignUpTopAppBar(
                onBackClick = onBackClick
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(Dimension.Medium)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(Dimension.Medium),
        ) {
            HeaderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            MainSection(
                email = email,
                password = password,
                confirmPassword = confirmPassword,
                onEmailChange = { email = it },
                onPasswordChange = { password = it },
                onConfirmPasswordChange = { confirmPassword = it },
                onSignUpClick = onSignUpSuccess,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            FooterSection(
                onLoginClick = onLoginClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignUpTopAppBar(
    onBackClick: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(
            text = "Criar conta"
        ),
        navigateUp = TopAppBarAction(
            icon = LelloIcons.ArrowLeft.imageVector,
            contentDescription = "Voltar",
            onClick = onBackClick
        )
    )
}

@Composable
private fun HeaderSection(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .padding(bottom = Dimension.Medium),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Text(
            text = "Preencha os campos abaixo para criar sua conta no Lello",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun MainSection(
    email: String,
    password: String,
    confirmPassword: String,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onConfirmPasswordChange: (String) -> Unit,
    onSignUpClick: () -> Unit = {},
    modifier: Modifier
) {
    val isPreview = LocalInspectionMode.current
    var isPasswordVisible by remember { mutableStateOf(false) }
    var isConfirmPasswordVisible by remember { mutableStateOf(false) }
    val focusManager = LocalFocusManager.current

    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.spacedBy(Dimension.Medium)
    ) {
        LelloOutlinedTextField(
            value = email,
            onValueChange = onEmailChange,
            label = "E-mail",
            placeholder = "Digite seu e-mail",
            leadingIcon = if (!isPreview) LelloIcons.Outlined.Mail.imageVector else null,
            isEmail = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        LelloOutlinedTextField(
            value = password,
            onValueChange = onPasswordChange,
            label = "Senha",
            placeholder = "Digite sua senha",
            isPassword = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)
        )

        LelloOutlinedTextField(
            value = confirmPassword,
            onValueChange = onConfirmPasswordChange,
            label = "Confirmar Senha",
            placeholder = "Digite sua senha novamente",
            isPassword = true
        )

        SignUpButton(
            onClick = onSignUpClick,
            enabled = email.isNotBlank() && password.isNotBlank() && confirmPassword.isNotBlank()
        )
    }
}

@Composable
private fun SignUpButton(
    onClick: () -> Unit,
    enabled: Boolean
) {
    LelloFilledButton(
        label = "Criar conta",
        onClick = onClick,
        enabled = enabled,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = Dimension.Medium)
    )
}

@Composable
private fun FooterSection(
    onLoginClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier
    ) {
        Text(
            text = buildAnnotatedString {
                append("Já possui uma conta? ")
                withStyle(
                    style = SpanStyle(fontWeight = FontWeight.Bold)
                ) {
                    append("Entrar")
                }
            },
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            modifier = Modifier.clickable { onLoginClick() }
        )
    }
}

@Preview(
    name = "Email Sign Up Screen",
    showBackground = true
)
@Composable
fun EmailSignUpScreenPreview() {
    LelloTheme(scheme = LelloColorScheme.INVERSE) {
        EmailSignUpScreenContent(
            onBackClick = {},
            onSignUpSuccess = {},
            onLoginClick = {}
        )
    }
}