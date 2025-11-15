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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import io.github.faening.lello.core.model.settings.NotificationPreferences
import io.github.faening.lello.feature.settings.SettingsViewModel

@Composable
fun SettingsNotificationScreen(
    viewModel: SettingsViewModel,
    onBack: () -> Unit,
) {
    val notificationPreferences by viewModel.notificationPreferences.collectAsState()

    SettingsNotificationScreenContent(
        notificationPreferences = notificationPreferences,
        onJournalRewardToggle = viewModel::toggleJournalRewardNotification,
        onMedicationToggle = viewModel::toggleMedicationNotification,
        onMascotEnergyToggle = viewModel::toggleMascotEnergyNotification,
        onBack = onBack
    )
}

@Composable
private fun SettingsNotificationScreenContent(
    notificationPreferences: NotificationPreferences,
    onJournalRewardToggle: (Boolean) -> Unit,
    onMedicationToggle: (Boolean) -> Unit,
    onMascotEnergyToggle: (Boolean) -> Unit,
    onBack: () -> Unit,
) {
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
                        isChecked = notificationPreferences.journalRewardsEnabled,
                        onCheckedChange = onJournalRewardToggle
                    ),
                    SettingsItem(
                        icon = LelloIcons.Outlined.DrugPill.imageVector,
                        title = "Remédios",
                        subtitle = "Lembretes para tomar os remédios no horário certo",
                        type = SettingsItemType.SWITCH,
                        isChecked = notificationPreferences.medicationEnabled,
                        onCheckedChange = onMedicationToggle
                    ),
                    SettingsItem(
                        icon = LelloIcons.Outlined.Sum.imageVector,
                        title = "Lello",
                        subtitle = "Receber um aviso quando o Lello estiver cansado",
                        type = SettingsItemType.SWITCH,
                        isChecked = notificationPreferences.mascotEnergyEnabled,
                        onCheckedChange = onMascotEnergyToggle
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
            notificationPreferences = NotificationPreferences(
                journalRewardsEnabled = true,
                medicationEnabled = false,
                mascotEnergyEnabled = true
            ),
            onJournalRewardToggle = {},
            onMedicationToggle = {},
            onMascotEnergyToggle = {},
            onBack = {}
        )
    }
}

// endregion Previews