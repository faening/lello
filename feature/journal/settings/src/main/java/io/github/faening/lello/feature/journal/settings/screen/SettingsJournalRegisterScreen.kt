package io.github.faening.lello.feature.journal.settings.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.LelloTextField
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.journal.settings.SettingsJournalViewModel
import io.github.faening.lello.feature.journal.settings.model.JournalOptionType

@Composable
internal fun SettingsJournalRegisterScreen(
    viewModel: SettingsJournalViewModel,
    optionType: JournalOptionType,
    colorScheme: LelloColorScheme,
    onBack: () -> Unit
) {
    val textState = remember { mutableStateOf("") }

    LelloTheme(scheme = colorScheme) {
        SettingsJournalRegisterContainer(
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
private fun SettingsJournalRegisterContainer(
    optionType: JournalOptionType,
    text: String,
    onTextChange: (String) -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = { SettingsJournalRegisterTopBar(optionType, onBack) },
        bottomBar = {
            SettingsJournalRegisterBottomBar(
                enabled = text.trim().isNotEmpty(),
                onSave = onSave
            )
        }
    ) { paddingValues ->
        SettingsJournalRegisterContent(
            text = text,
            onTextChange = onTextChange,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun SettingsJournalRegisterTopBar(
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
private fun SettingsJournalRegisterBottomBar(
    enabled: Boolean,
    onSave: () -> Unit
) {
    val label = "Cadastrar"
    Row(
        modifier = Modifier
            .padding(Dimension.Medium)
            .fillMaxWidth()
    ) {
        LelloFilledButton(
            label = label,
            enabled = enabled,
            onClick = onSave,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun SettingsJournalRegisterContent(
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(Dimension.Medium)
    ) {
        Text(
            text = "Crie um novo item para usar em seus diários",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = Modifier.padding(bottom = Dimension.ExtraLarge)
        )

        LelloTextField(
            value = text,
            onValueChange = onTextChange,
            placeholder = "Descrição do item",
            maxLength = 40,
            showCounter = false
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
fun SettingsJournalRegisterScreenPreview() {
    LelloTheme {
        SettingsJournalRegisterContainer(
            optionType = JournalOptionType.EMOTION,
            text = "",
            onTextChange = {},
            onSave = {},
            onBack = {}
        )
    }
}
