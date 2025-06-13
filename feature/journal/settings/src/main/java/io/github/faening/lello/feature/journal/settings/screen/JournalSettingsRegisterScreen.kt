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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.LelloTextArea
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.journal.settings.JournalSettingsViewModel
import io.github.faening.lello.feature.journal.settings.model.JournalOptionType
import io.github.faening.lello.feature.journal.settings.R as settingsR

@Composable
internal fun JournalSettingsRegisterScreen(
    viewModel: JournalSettingsViewModel,
    optionType: JournalOptionType,
    colorScheme: LelloColorScheme,
    onBack: () -> Unit
) {
    val textState = remember { mutableStateOf("") }

    LelloTheme(scheme = colorScheme) {
        JournalSettingsRegisterContainer(
            optionType = optionType,
            text = textState.value,
            onTextChange = { textState.value = it },
            onSave = {
                viewModel.saveOption(optionType, textState.value)
                onBack()
            },
            onBack = onBack
        )
    }
}

@Composable
private fun JournalSettingsRegisterContainer(
    optionType: JournalOptionType,
    text: String,
    onTextChange: (String) -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = { JournalSettingsRegisterTopBar(optionType, onBack) },
        bottomBar = { JournalSettingsRegisterBottomBar(onSave) }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues)
                .padding(Dimension.Medium)
        ) {
            LelloTextArea(
                label = stringResource(settingsR.string.journal_settings_description_label),
                value = text,
                onValueChange = onTextChange,
            )
        }
    }
}

@Composable
private fun JournalSettingsRegisterTopBar(
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
private fun JournalSettingsRegisterBottomBar(
    onSave: () -> Unit
) {
    val label = stringResource(settingsR.string.journal_settings_save_button_label)
    Row(
        modifier = Modifier.padding(Dimension.Medium)
    ) {
        LelloFilledButton(
            label = label,
            onClick = onSave,
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
fun JournalSettingsRegisterScreenPreview() {
    LelloTheme {
        JournalSettingsRegisterContainer(
            optionType = JournalOptionType.EMOTION,
            text = "",
            onTextChange = {},
            onSave = {},
            onBack = {}
        )
    }
}
