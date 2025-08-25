package io.github.faening.lello.feature.authentication.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.feature.authentication.AuthenticationViewModel

@Composable
internal fun AuthenticationScreen(
    viewModel: AuthenticationViewModel,
    onEmailSignUpClick: () -> Unit = {},
    onGoogleSignUpClick: () -> Unit = {},
    onPrivacyPolicyClick: () -> Unit = {},
    onLoginClick: () -> Unit,
    onRecoverAccountClick: () -> Unit = {}
) {
    LelloTheme(moodColor = MoodColor.INVERSE) {
        AuthenticationScreenContent(
            onEmailSignUpClick = onEmailSignUpClick,
            onGoogleSignUpClick = onGoogleSignUpClick,
            onPrivacyPolicyClick = onPrivacyPolicyClick,
            onLoginClick = onLoginClick,
            onRecoverAccountClick = onRecoverAccountClick
        )
    }
}

@Composable
private fun AuthenticationScreenContent(
    onEmailSignUpClick: () -> Unit,
    onGoogleSignUpClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    onLoginClick: () -> Unit,
    onRecoverAccountClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.inversePrimary)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(Dimension.spacingRegular)
        ) {
            HeaderSection(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            MainSection(
                onEmailSignUpClick = onEmailSignUpClick,
                onGoogleSignUpClick = onGoogleSignUpClick,
                onPrivacyPolicyClick = onPrivacyPolicyClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f)
            )

            FooterSection(
                onLoginClick = onLoginClick,
                onRecoverAccountClick = onRecoverAccountClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
        }
    }
}

@Composable
private fun HeaderSection(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(
                id = LelloIcons.Logo.resId
            ),
            contentDescription = "Logo do Lello",
            contentScale = ContentScale.Fit,
            modifier = Modifier.size(140.dp)
        )
    }
}

@Composable
private fun MainSection(
    onEmailSignUpClick: () -> Unit,
    onGoogleSignUpClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
    ) {
        Text(
            text = "Como você deseja criar sua conta?",
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(Dimension.spacingExtraLarge)
        )

        AuthenticationOptionsButtons(
            onEmailSignUpClick = onEmailSignUpClick,
            onGoogleSignUpClick = onGoogleSignUpClick
        )

        TermsAndPrivacyText(
            onPrivacyPolicyClick = onPrivacyPolicyClick
        )
    }
}

@Composable
private fun AuthenticationOptionsButtons(
    onEmailSignUpClick: () -> Unit,
    onGoogleSignUpClick: () -> Unit
) {
    val isPreview = LocalInspectionMode.current

    LelloFilledButton(
        label = "Continuar com e-mail e senha",
        onClick = onEmailSignUpClick,
        icon = LelloIcons.Outlined.Mail.imageVector,
        moodColor = MoodColor.INVERSE,
        modifier = Modifier.padding(bottom = Dimension.spacingLarge)
    )

    LelloFilledButton(
        label = "Continuar com o Google",
        onClick = onGoogleSignUpClick,
        icon = LelloIcons.Filled.Google.imageVector,
        moodColor = MoodColor.SECONDARY
    )
}

@Composable
private fun TermsAndPrivacyText(
    onPrivacyPolicyClick: () -> Unit
) {
    Text(
        text = buildAnnotatedString {
            append("Ao continuar, você concorda com os ")
            withStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold)
            ) {
                append("Termos de Uso e Política de Privacidade")
            }
            append(" do Lello.")
        },
        style = MaterialTheme.typography.bodyMedium,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .padding(vertical = Dimension.spacingRegular)
            .clickable { onPrivacyPolicyClick() }
    )
}

@Composable
private fun FooterSection(
    onLoginClick: () -> Unit,
    onRecoverAccountClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,
        modifier = modifier
    ) {
        LoginAccountLink(
            onLoginClick = onLoginClick
        )

        RecoverAccountLink(
            onRecoverAccountClick = onRecoverAccountClick
        )
    }
}

@Composable
private fun LoginAccountLink(
    onLoginClick: () -> Unit
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
        modifier = Modifier
            .padding(Dimension.spacingRegular)
            .clickable { onLoginClick() }
    )
}

@Composable
private fun RecoverAccountLink(
    onRecoverAccountClick: () -> Unit
) {
    Text(
        text = buildAnnotatedString {
            append("Não consegue entrar? ")
            withStyle(
                style = SpanStyle(fontWeight = FontWeight.Bold)
            ) {
                append("Recuperar conta")
            }
        },
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .clickable { onRecoverAccountClick() }
    )
}

@Composable
@Preview(
    name = "Authentication Screen",
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
fun AuthenticationScreenPreview() {
    LelloTheme(moodColor = MoodColor.INVERSE) {
        AuthenticationScreenContent(
            onEmailSignUpClick = {},
            onGoogleSignUpClick = {},
            onPrivacyPolicyClick = {},
            onLoginClick = {},
            onRecoverAccountClick = {}
        )
    }
}