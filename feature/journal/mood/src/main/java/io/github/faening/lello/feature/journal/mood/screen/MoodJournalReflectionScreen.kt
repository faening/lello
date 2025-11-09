package io.github.faening.lello.feature.journal.mood.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.textfield.LelloMultilineTextField
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.feature.journal.mood.MoodJournalViewModel

@Composable
internal fun MoodJournalReflectionScreen(
    viewModel: MoodJournalViewModel,
    onBack: () -> Unit,
    onFinish: () -> Unit
) {
    val moodColor by viewModel.currentMood.collectAsState()
    val entryTime by viewModel.entryDateTime.collectAsState()
    val reflection by viewModel.reflection.collectAsState()
    val coinsAcquired by viewModel.coinsAcquired.collectAsState()

    MoodJournalReflectionScreenContent(
        entryTime = entryTime,
        reflection = reflection,
        onValueChange = viewModel::updateReflection,
        onSave = viewModel::saveMoodJournal,
        onBack = onBack,
        onFinish = onFinish,
        coinsAcquired = coinsAcquired,
        moodColor = moodColor
    )
}

@Composable
private fun MoodJournalReflectionScreenContent(
    entryTime: String,
    reflection: String,
    onValueChange: (String) -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
    onFinish: () -> Unit,
    coinsAcquired: Int,
    moodColor: MoodColor
) {
    Scaffold(
        topBar = {
            MoodJournalReflectionTopAppBar(
                entryTime = entryTime,
                moodColor = moodColor,
                onBack = onBack
            )
        },
        bottomBar = {
            MoodJournalReflectionBottomBar(
                moodColor = moodColor,
                onSave = onSave,
                onFinish = onFinish
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(horizontal = Dimension.spacingRegular)
        ) {
            // Header
            Text(
                text = "Quer anotar algum detalhe importante ou uma reflexão neste diário?",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = Dimension.spacingRegular)
            )
            Text(
                text = "Ganhe $coinsAcquired moeads ao concluir",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)
            )

            // Content
            LelloMultilineTextField(
                value = reflection,
                onValueChange = onValueChange,
                placeholder = "Digite sua reflexão livre aqui...",
                maxLength = 500,
                showCounter = true,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
private fun MoodJournalReflectionTopAppBar(
    entryTime: String,
    moodColor: MoodColor,
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Hoje, $entryTime"),
        navigateUp = TopAppBarAction(onClick = onBack),
        moodColor = moodColor
    )
}

@Composable
private fun MoodJournalReflectionBottomBar(
    moodColor: MoodColor,
    onSave: () -> Unit,
    onFinish: () -> Unit,
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
            label = "Concluir",
            onClick = {
                onSave()
                onFinish()
            },
            moodColor = moodColor
        )
    }
}

// region Previews

@Composable
@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
private fun MoodJournalReflectionScreenPreview_LightMode() {
    LelloTheme {
        MoodJournalReflectionScreenContent(
            entryTime = "09:41",
            reflection = "",
            onValueChange = {},
            onSave = {},
            onBack = {},
            onFinish = {},
            coinsAcquired = 100,
            moodColor = MoodColor.DEFAULT
        )
    }
}

// endregion Previews