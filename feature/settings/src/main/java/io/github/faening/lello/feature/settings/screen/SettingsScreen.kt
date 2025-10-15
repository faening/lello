package io.github.faening.lello.feature.settings.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.card.LelloSettingsCard
import io.github.faening.lello.core.designsystem.component.card.SettingsItem
import io.github.faening.lello.core.designsystem.component.card.SettingsItemType
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.feature.settings.SettingsViewModel

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel
) {
    val isBiometricEnabled by viewModel.isBiometricEnabled.collectAsState()
    val isBiometricAvailable by viewModel.isBiometricAvailable.collectAsState()

    SettingsScreenContent(
        isBiometricEnabled = isBiometricEnabled,
        isBiometricAvailable = isBiometricAvailable,
        onBiometricToggle = viewModel::toggleBiometricAuthentication
    )
}

@Composable
private fun SettingsScreenContent(
    moodColor: MoodColor = MoodColor.DEFAULT,
    isBiometricEnabled: Boolean = false,
    isBiometricAvailable: Boolean = false,
    onBiometricToggle: (Boolean) -> Unit = {}
) {
    LelloTheme {
        Scaffold(
            topBar = {
                TopAppBarSection()
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(16.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                SecuritySection(
                    isBiometricEnabled = isBiometricEnabled,
                    isBiometricAvailable = isBiometricAvailable,
                    onBiometricToggle = onBiometricToggle
                )
            }
        }
    }
}

@Composable
private fun TopAppBarSection(
    moddColor: MoodColor = MoodColor.INVERSE
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text= "Mais recursos"),
        moodColor = moddColor
    )
}

@Composable
private fun SecuritySection(
    isBiometricEnabled: Boolean = false,
    isBiometricAvailable: Boolean = false,
    onBiometricToggle: (Boolean) -> Unit = {}
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
                        "Ative ou desative o recurso de impressão digital."
                    } else {
                        "Impressão digital não disponível neste dispositivo."
                    },
                    type = SettingsItemType.SWITCH,
                    isChecked = isBiometricEnabled,
                    onCheckedChange = { onBiometricToggle(it) }
                ),
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
            moodColor = MoodColor.DEFAULT
        )
    }
}

// endregion Previews