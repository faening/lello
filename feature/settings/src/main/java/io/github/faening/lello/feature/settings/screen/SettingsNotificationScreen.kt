package io.github.faening.lello.feature.settings.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.card.LelloSettingsCard
import io.github.faening.lello.core.designsystem.component.card.SettingsItem
import io.github.faening.lello.core.designsystem.component.card.SettingsItemType
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.settings.SettingsViewModel

@Composable
fun SettingsNotificationScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit,
) {

    SettingsNotificationScreenContent(
        onBack = onBack
    )
}

@Composable
private fun SettingsNotificationScreenContent(
    onBack: () -> Unit,
) {
    var rewardNotificationEnabled by rememberSaveable { mutableStateOf(true) }
    var medicationNotificationEnabled by rememberSaveable { mutableStateOf(true) }
    var mascotEnergyNotificationEnabled by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        topBar = {
            SettingsNotificationTopAppBar(onBack = onBack)
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
            verticalArrangement = Arrangement.spacedBy(Dimension.spacingLarge),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LelloSettingsCard(
                sectionTitle = "Ative ou desative notificações",
                items = listOf(
                    SettingsItem(
                        icon = LelloIcons.Outlined.Document.imageVector,
                        title = "Diários",
                        subtitle = "Avise quando uma recompensa estiver disponível",
                        type = SettingsItemType.SWITCH,
                        isChecked = rewardNotificationEnabled,
                        onCheckedChange = { rewardNotificationEnabled = it }
                    ),
                    SettingsItem(
                        icon = LelloIcons.Outlined.DrugPill.imageVector,
                        title = "Remédios",
                        subtitle = "Lembretes para tomar os remédios no horário certo",
                        type = SettingsItemType.SWITCH,
                        isChecked = medicationNotificationEnabled,
                        onCheckedChange = { medicationNotificationEnabled = it }
                    ),
                    SettingsItem(
                        icon = LelloIcons.Outlined.Sum.imageVector,
                        title = "Lello",
                        subtitle = "Receber um aviso quando o Lello estiver cansado",
                        type = SettingsItemType.SWITCH,
                        isChecked = mascotEnergyNotificationEnabled,
                        onCheckedChange = { mascotEnergyNotificationEnabled = it }
                    )
                )
            )
        }
    }
}

@Composable
private fun SettingsNotificationTopAppBar(
    onBack: () -> Unit,
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Notificações"),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

// region Previews

@Composable
@Preview(
    name = "Default",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun SettingsNotificationScreenPreview_LightMode() {
    LelloTheme {
        SettingsNotificationScreenContent(
            onBack = {}
        )
    }
}

// endregion Previews