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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.list.LelloOptionList
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.domain.mock.EmotionOptionMock
import io.github.faening.lello.core.model.option.JournalOption
import io.github.faening.lello.core.testing.data.EmotionOptionTestData
import io.github.faening.lello.core.testing.data.MedicationDosageFormOptionTestData
import io.github.faening.lello.feature.journal.settings.SettingsJournalViewModel
import io.github.faening.lello.feature.journal.settings.model.JournalOptionType

@Composable
internal fun SettingsJournalScreen(
    viewModel: SettingsJournalViewModel,
    optionType: JournalOptionType,
    moodColor: MoodColor = MoodColor.DEFAULT,
    onBack: () -> Unit,
    onRegister: () -> Unit
) {
    val options by viewModel.optionsFlow(optionType).collectAsState()

    SettingsJournalScreenContent(
        optionType = optionType,
        moodColor = moodColor,
        options = options,
        onBack = onBack,
        onRegister = onRegister,
        onToggle = { option, active -> viewModel.toggleOption(optionType, option, active) }
    )
}

@Composable
private fun SettingsJournalScreenContent(
    optionType: JournalOptionType,
    moodColor: MoodColor,
    options: List<JournalOption>,
    onBack: () -> Unit,
    onRegister: () -> Unit,
    onToggle: (JournalOption, Boolean) -> Unit
) {
    Scaffold(
        topBar = {
            SettingsJournalTopAppBar(
                optionType = optionType,
                moodColor = moodColor,
                onBack = onBack
            )
        },
        bottomBar = {
            SettingsJournalBottomBar(
                optionType = optionType,
                moodColor = moodColor,
                onRegister = onRegister
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
                text = "Gerencie os itens disponíveis para preenchimento em seus diários",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
            )

            LelloOptionList(
                options = options,
                onToggle = onToggle,
                moodColor = moodColor,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Composable
private fun SettingsJournalTopAppBar(
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
private fun SettingsJournalBottomBar(
    optionType: JournalOptionType,
    moodColor: MoodColor,
    onRegister: () -> Unit
) {
    val label = stringResource(optionType.registerLabelRes)
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
            label = label,
            onClick = onRegister,
            moodColor = moodColor
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
private fun SettingsJournalScreenPreview_LightMode() {
    val items = EmotionOptionTestData.list

    LelloTheme {
        SettingsJournalScreenContent(
            optionType = JournalOptionType.EMOTION,
            moodColor = MoodColor.DEFAULT,
            options = items,
            onBack = {},
            onRegister = {},
            onToggle = { _, _ -> }
        )
    }
}

// endregion: Previews