package io.github.faening.lello.feature.authentication.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
    onEmailSignInClick: () -> Unit = {},
    onGoogleSignInClick: () -> Unit = {},
    onPrivacyPolicyClick: () -> Unit = {},
    onEmailSignUpClick: () -> Unit,
    onRecoverAccountClick: () -> Unit = {}
) {
    AuthenticationScreenContent(
        onEmailSignInClick = onEmailSignInClick,
        onGoogleSignInClick = onGoogleSignInClick,
        onPrivacyPolicyClick = onPrivacyPolicyClick,
        onEmailSignUpClick = onEmailSignUpClick,
        onRecoverAccountClick = onRecoverAccountClick
    )
}

@Composable
private fun AuthenticationScreenContent(
    onEmailSignInClick: () -> Unit,
    onGoogleSignInClick: () -> Unit,
    onPrivacyPolicyClick: () -> Unit,
    onEmailSignUpClick: () -> Unit,
    onRecoverAccountClick: () -> Unit,
    moodColor: MoodColor = MoodColor.INVERSE
) {
    LelloTheme(moodColor) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.inversePrimary)
                .padding(Dimension.spacingRegular)
        ) {
            // Header Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                Image(
                    painter = painterResource(id = LelloIcons.Logo.resId),
                    contentDescription = "Logotipo",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.size(140.dp)
                )
            }

            // Main Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth().weight(1.5f)
            ) {
                Text(
                    text = "Como você deseja entrar?",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(Dimension.spacingExtraLarge)
                )

                LelloFilledButton(
                    label = "Continuar com e-mail e senha",
                    onClick = onEmailSignInClick,
                    icon = LelloIcons.Outlined.Mail.imageVector,
                    moodColor = moodColor,
                    modifier = Modifier.padding(bottom = Dimension.spacingLarge)
                )
                LelloFilledButton(
                    label = "Continuar com o Google",
                    onClick = onGoogleSignInClick,
                    icon = LelloIcons.Filled.Google.imageVector,
                    moodColor = MoodColor.SECONDARY
                )

                Text(
                    text = buildAnnotatedString {
                        append("Ao continuar, você concorda com os ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Termos de Uso e Política de Privacidade")
                        }
                        append(" do Lello.")
                    },
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(vertical = Dimension.spacingRegular).clickable { onPrivacyPolicyClick() }
                )
            }

            // Footer Section
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier.fillMaxWidth().weight(1f)
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Não possui uma conta? ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Inscreva-se")
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(Dimension.spacingRegular).clickable { onEmailSignUpClick() }
                )

                Text(
                    text = buildAnnotatedString {
                        append("Não consegue entrar? ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                            append("Recuperar conta")
                        }
                    },
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.clickable { onRecoverAccountClick() }
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
fun AuthenticationScreenPreview_LightMode() {
    AuthenticationScreenContent(
        onEmailSignInClick = {},
        onGoogleSignInClick = {},
        onPrivacyPolicyClick = {},
        onEmailSignUpClick = {},
        onRecoverAccountClick = {}
    )
}

@Preview(
    name = "Darl Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun AuthenticationScreenPreview_DarkMode() {
    AuthenticationScreenContent(
        onEmailSignInClick = {},
        onGoogleSignInClick = {},
        onPrivacyPolicyClick = {},
        onEmailSignUpClick = {},
        onRecoverAccountClick = {}
    )
}
