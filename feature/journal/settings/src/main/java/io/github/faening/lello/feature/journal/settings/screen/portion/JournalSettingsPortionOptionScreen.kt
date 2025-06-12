package io.github.faening.lello.feature.journal.settings.screen.portion

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.LelloOptionList
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.journal.PortionOption
import io.github.faening.lello.feature.journal.settings.JournalSettingsViewModel
import io.github.faening.lello.feature.journal.settings.R as settingsR

@Composable
internal fun JournalSettingsPortionOptionScreen(
    viewModel: JournalSettingsViewModel,
    colorScheme: LelloColorScheme,
    onBack: () -> Unit,
    onRegister: () -> Unit
) {
    val options by viewModel.portionOptions.collectAsState()

    LelloTheme(scheme = colorScheme) {
        JournalSettingsPortionOptionContainer(
            options = options,
            onToggle = { option, active -> viewModel.togglePortionOption(option, active) },
            onBack = onBack,
            onRegister = onRegister
        )
    }
}

@Composable
private fun JournalSettingsPortionOptionContainer(
    options: List<PortionOption>,
    onToggle: (PortionOption, Boolean) -> Unit,
    onBack: () -> Unit,
    onRegister: () -> Unit
) {
    Scaffold(
        topBar = { JournalSettingsPortionOptionTopBar(onBack) },
        bottomBar = { JournalSettingsPortionOptionBottomBar(onRegister) }
    ) { paddingValues ->
        JournalSettingsPortionOptionContent(
            options = options,
            onToggle = onToggle,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun JournalSettingsPortionOptionTopBar(
    onBack: () -> Unit
) {
    val title = settingsR.string.journal_settings_portion_appbar_title
    LelloTopAppBar(
        title = TopAppBarTitle(title),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
private fun JournalSettingsPortionOptionBottomBar(
    onRegister: () -> Unit
) {
    val label = stringResource(settingsR.string.journal_settings_portion_register_button_label)
    Row(
        modifier = Modifier.padding(Dimension.Medium)
    ) {
        LelloFilledButton(
            label = label,
            onClick = onRegister,
        )
    }
}

@Composable
private fun JournalSettingsPortionOptionContent(
    options: List<PortionOption>,
    onToggle: (PortionOption, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(Dimension.Medium)
    ) {
        Text(
            text = "Gerencie os itens disponíveis para preenchimento em seus diários",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onPrimary,
            modifier = Modifier.padding(bottom = Dimension.ExtraLarge)
        )

        LelloOptionList(
            options = options,
            onToggle = { option, active ->
                onToggle(option as PortionOption, active)
            },
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
fun JournalSettingsPortionOptionScreenPreview() {
    LelloTheme {
        JournalSettingsPortionOptionContainer(
            options = listOf(),
            onToggle = { _, _ -> },
            onBack = {},
            onRegister = {}
        )
    }
}
