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
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.textfield.LelloTextField
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.feature.journal.settings.SettingsJournalViewModel
import io.github.faening.lello.feature.journal.settings.model.JournalOptionType

@Composable
internal fun SettingsJournalRegisterScreen(
    viewModel: SettingsJournalViewModel,
    optionType: JournalOptionType,
    moodColor: MoodColor = MoodColor.DEFAULT,
    onBack: () -> Unit
) {
    val textState = remember { mutableStateOf("") }

    SettingsJournalRegisterScreenContent(
        optionType = optionType,
        moodColor = moodColor,
        text = textState.value,
        onTextChange = { textState.value = it },
        onBack = onBack,
        onSave = {
            viewModel.saveOption(optionType, textState.value)
            onBack()
        }
    )
}

@Composable
private fun SettingsJournalRegisterScreenContent(
    optionType: JournalOptionType,
    moodColor: MoodColor,
    text: String,
    onTextChange: (String) -> Unit,
    onBack: () -> Unit,
    onSave: () -> Unit
) {
    Scaffold(
        topBar = {
            SettingsJournalRegisterTopAppBar(
                optionType = optionType,
                moodColor = moodColor,
                onBack = onBack
            )
        },
        bottomBar = {
            SettingsJournalRegisterBottomBar(
                moodColor = moodColor,
                enabled = text.trim().isNotEmpty(),
                onSave = onSave
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues)
                .padding(Dimension.spacingRegular)
        ) {
            Text(
                text = "Crie um novo item para usar em seus diários",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
            )

            LelloTextField(
                value = text,
                onValueChange = onTextChange,
                placeholder = "Descrição do item",
            )
        }
    }
}

@Composable
private fun SettingsJournalRegisterTopAppBar(
    optionType: JournalOptionType,
    moodColor: MoodColor,
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(optionType.titleRes),
        navigateUp = TopAppBarAction(onClick = onBack),
        moodColor = moodColor
    )
}

@Composable
private fun SettingsJournalRegisterBottomBar(
    moodColor: MoodColor,
    enabled: Boolean,
    onSave: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = Dimension.spacingRegular,
                end = Dimension.spacingRegular,
                bottom = Dimension.spacingRegular
            )
    ) {
        LelloFilledButton(
            label = "Cadastrar",
            enabled = enabled,
            onClick = onSave,
            moodColor = moodColor,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

// region: Previews

@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun SettingsJournalRegisterScreenPreview_LightMode() {
    LelloTheme {
        SettingsJournalRegisterScreenContent(
            optionType = JournalOptionType.EMOTION,
            moodColor = MoodColor.DEFAULT,
            text = "",
            onTextChange = {},
            onBack = {},
            onSave = {}
        )
    }
}

// endregion: Previews