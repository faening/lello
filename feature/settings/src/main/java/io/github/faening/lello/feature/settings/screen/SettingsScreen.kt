package io.github.faening.lello.feature.settings.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.card.LelloSettingsCard
import io.github.faening.lello.core.designsystem.component.card.SettingsItem
import io.github.faening.lello.core.designsystem.component.card.SettingsItemType
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.settings.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onNavigateToNotifications: () -> Unit,
    onNavigateToTerms: () -> Unit
) {
    // UI
    val isDarkThemeEnabled by viewModel.isDarkThemeEnabled.collectAsState()

    // Security
    val isBiometricEnabled by viewModel.isBiometricEnabled.collectAsState()
    val isBiometricAvailable by viewModel.isBiometricAvailable.collectAsState()

    SettingsScreenContent(
        isDarkThemeEnabled = isDarkThemeEnabled,
        onToggleTheme = viewModel::toggleDarkTheme,
        isBiometricEnabled = isBiometricEnabled,
        isBiometricAvailable = isBiometricAvailable,
        onBiometricToggle = viewModel::toggleBiometricAuthentication,
        onNavigateToNotifications = onNavigateToNotifications,
        onNavigateToTerms = onNavigateToTerms
    )
}

@Composable
private fun SettingsScreenContent(
    isDarkThemeEnabled: Boolean = false,
    onToggleTheme: (Boolean) -> Unit = {},
    isBiometricEnabled: Boolean = false,
    isBiometricAvailable: Boolean = false,
    onBiometricToggle: (Boolean) -> Unit = {},
    onNavigateToNotifications: () -> Unit = {},
    onLogout: () -> Unit = {},
    onDeleteAccount: () -> Unit = {},
    onNavigateToTerms: () -> Unit = {}
) {
    Scaffold(
        topBar = {
            SettingsScreenTopAppBar(
                onSearchClick = {}
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(
                    top = Dimension.spacingRegular,
                    start = Dimension.spacingRegular,
                    end = Dimension.spacingRegular
                ),
            verticalArrangement = Arrangement.spacedBy(Dimension.spacingExtraLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SettingsScreenUISection(
                isDarkThemeEnabled = isDarkThemeEnabled,
                onToggleTheme = onToggleTheme,
                onNavigateToNotifications = onNavigateToNotifications
            )
            SettingsScreenSecuritySection(
                isBiometricEnabled = isBiometricEnabled,
                isBiometricAvailable = isBiometricAvailable,
                onBiometricToggle = onBiometricToggle
            )
            SettingsScreenAccountSection(
                onLogout = onLogout,
                onDeleteAccount = onDeleteAccount
            )
            SettingsScreenTermsAndPrivacySection(
                onNavigateToTerms = onNavigateToTerms
            )
        }
    }
}

@Composable
private fun SettingsScreenTopAppBar(
    onSearchClick: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text= "Mais recursos"),
//        actions = listOf(
//            TopAppBarAction(
//                icon = LelloIcons.Outlined.Search.imageVector,
//                contentDescription = "Fechar configurações",
//                onClick = { onSearchClick }
//            )
//        ),
    )
}

@Composable
private fun SettingsScreenUISection(
    isDarkThemeEnabled: Boolean = false,
    onToggleTheme: (Boolean) -> Unit = {},
    onNavigateToNotifications: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        LelloSettingsCard(
            sectionTitle = "UI",
            items = listOf(
                SettingsItem(
                    icon = LelloIcons.Outlined.Sum.imageVector,
                    title = "Personalização",
                    subtitle = "Ativar ou desativar o tema escuro",
                    type = SettingsItemType.SWITCH,
                    isChecked = isDarkThemeEnabled,
                    onCheckedChange = onToggleTheme
                ),
                SettingsItem(
                    icon = LelloIcons.Outlined.NotificationBell.imageVector,
                    title = "Notificações",
                    subtitle = "Ajuste as preferências de notificação",
                    type = SettingsItemType.NAVIGATION,
                    onClick = onNavigateToNotifications
                )
            )
        )
    }
}

@Composable
private fun SettingsScreenSecuritySection(
    isBiometricEnabled: Boolean = false,
    isBiometricAvailable: Boolean = false,
    onBiometricToggle: (Boolean) -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        LelloSettingsCard(
            sectionTitle = "Segurança",
            items = listOf(
                SettingsItem(
                    icon = LelloIcons.Outlined.Fingerprint.imageVector,
                    title = "Biometria",
                    subtitle = if (isBiometricAvailable) {
                        "Ative ou desative o recurso de impressão digital"
                    } else {
                        "Impressão digital não disponível neste dispositivo"
                    },
                    type = SettingsItemType.SWITCH,
                    isChecked = isBiometricEnabled,
                    onCheckedChange = { onBiometricToggle(it) }
                ),
            )
        )
    }
}

@Composable
private fun SettingsScreenAccountSection(
    onLogout: () -> Unit,
    onDeleteAccount: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        LelloSettingsCard(
            sectionTitle = "Conta",
            items = listOf(
                SettingsItem(
                    icon = LelloIcons.Outlined.ArrowTopLeftRound.imageVector,
                    title = "Sair",
                    subtitle = "Saia da sua conta sem perder seus dados",
                    type = SettingsItemType.NAVIGATION,
                    onClick = { onLogout }
                ),
                SettingsItem(
                    icon = LelloIcons.Outlined.Trash.imageVector,
                    title = "Excluir conta",
                    subtitle = "Excluir permanentemente sua conta e todos os dados associados",
                    type = SettingsItemType.NAVIGATION,
                    onClick = { onDeleteAccount },
                    isDangerousTheme = true
                )
            )
        )
    }
}

@Composable
private fun SettingsScreenTermsAndPrivacySection(
    onNavigateToTerms: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        LelloSettingsCard(
            sectionTitle = "Sobre",
            items = listOf(
                SettingsItem(
                    icon = LelloIcons.Outlined.Document.imageVector,
                    title = "Uso e Privacidade",
                    subtitle = "Leia nossos termos de uso e política de privacidade",
                    type = SettingsItemType.NAVIGATION,
                    onClick = onNavigateToTerms,
                )
            )
        )
    }
}

// region Previews

@Composable
@Preview(
    name = "Default",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun SettingsScreenPreview_LightMode() {
    LelloTheme {
        SettingsScreenContent(
            onNavigateToNotifications = { }
        )
    }
}

// endregion Previews