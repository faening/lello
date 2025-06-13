package io.github.faening.lello.feature.journal.settings.screen

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
import io.github.faening.lello.core.domain.mock.EmotionOptionMock
import io.github.faening.lello.core.model.journal.JournalOption
import io.github.faening.lello.feature.journal.settings.JournalSettingsViewModel
import io.github.faening.lello.feature.journal.settings.model.JournalOptionType

@Composable
internal fun JournalSettingsScreen(
    viewModel: JournalSettingsViewModel,
    optionType: JournalOptionType,
    colorScheme: LelloColorScheme,
    onBack: () -> Unit,
    onRegister: () -> Unit
) {
    val options by viewModel.optionsFlow(optionType).collectAsState()

    LelloTheme(scheme = colorScheme) {
        JournalSettingsContainer(
            optionType = optionType,
            options = options,
            onToggle = { option, active -> viewModel.toggleOption(optionType, option, active) },
            onBack = onBack,
            onRegister = onRegister
        )
    }
}

@Composable
private fun JournalSettingsContainer(
    optionType: JournalOptionType,
    options: List<JournalOption>,
    onToggle: (JournalOption, Boolean) -> Unit,
    onBack: () -> Unit,
    onRegister: () -> Unit
) {
    Scaffold(
        topBar = { JournalSettingsTopBar(optionType, onBack) },
        bottomBar = { JournalSettingsBottomBar(optionType, onRegister) }
    ) { paddingValues ->
        JournalSettingsContent(
            options = options,
            onToggle = onToggle,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun JournalSettingsTopBar(
    optionType: JournalOptionType,
    onBack: () -> Unit
) {
    val title = optionType.titleRes
    LelloTopAppBar(
        title = TopAppBarTitle(title),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
private fun JournalSettingsBottomBar(
    optionType: JournalOptionType,
    onRegister: () -> Unit
) {
    val label = stringResource(optionType.registerLabelRes)
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
private fun JournalSettingsContent(
    options: List<JournalOption>,
    onToggle: (JournalOption, Boolean) -> Unit,
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
            onToggle = onToggle,
            modifier = Modifier.weight(1f)
        )
    }
}

@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun JournalSettingsScreenPreview() {
    LelloTheme {
        JournalSettingsContainer(
            optionType = JournalOptionType.EMOTION,
            options = EmotionOptionMock.list,
            onToggle = { _, _ -> },
            onBack = {},
            onRegister = {}
        )
    }
}
