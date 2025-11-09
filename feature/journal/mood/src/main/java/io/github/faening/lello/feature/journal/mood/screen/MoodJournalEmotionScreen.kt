package io.github.faening.lello.feature.journal.mood.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.button.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.component.pill.LelloOptionPillSelector
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.domain.mock.EmotionOptionMock
import io.github.faening.lello.core.model.option.EmotionOption
import io.github.faening.lello.feature.journal.mood.MoodJournalViewModel

@Composable
internal fun MoodJournalEmotionScreen(
    viewModel: MoodJournalViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    onOpenEmotionOptionSettings: () -> Unit
) {
    val moodColor by viewModel.currentMood.collectAsState()
    val entryTime by viewModel.entryDateTime.collectAsState()
    val emotionOptions by viewModel.emotionOptions.collectAsState()
    val coinsAcquired by viewModel.coinsAcquired.collectAsState()

    MoodJournalEmotionScreenContent(
        entryTime = entryTime,
        coinsAcquired = coinsAcquired,
        emotionOptions = emotionOptions,
        onEmotionOptionToggle = viewModel::toggleEmotionSelection,
        onOpenEmotionOptionSettings = onOpenEmotionOptionSettings,
        onSave = viewModel::saveMoodJournal,
        onBack = onBack,
        onNext = onNext,
        onFinish = onFinish,
        moodColor = moodColor
    )
}

@Composable
private fun MoodJournalEmotionScreenContent(
    entryTime: String,
    coinsAcquired: Int,
    emotionOptions: List<EmotionOption>,
    onEmotionOptionToggle: (String) -> Unit,
    onOpenEmotionOptionSettings: () -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    moodColor: MoodColor
) {
    val anySelected = emotionOptions.any { it.selected }

    Scaffold(
        topBar = {
            MoodJournalEmotionTopAppBar(
                entryTime = entryTime,
                moodColor = moodColor,
                onBack = onBack
            )
        },
        bottomBar = {
            MoodJournalEmotionBottomBar(
                anySelected = anySelected,
                moodColor = moodColor,
                onSave = onSave,
                onFinish = onFinish,
                onNext = onNext
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(Dimension.spacingRegular)
        ) {
            // Header
            Text(
                text = "Quais emoções fazem mais sentido neste momento?",
                style = MaterialTheme.typography.headlineSmall,
                modifier = Modifier.padding(bottom = Dimension.spacingRegular)
            )
            Text(
                text = "Ganhe $coinsAcquired moeads ao concluir",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(bottom = Dimension.spacingExtraLarge)

            )

            // Content
            Column(
                modifier = Modifier
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                LelloOptionPillSelector(
                    title = null,
                    options = emotionOptions,
                    isSelected = { it.selected },
                    onToggle = { option -> onEmotionOptionToggle(option.description) },
                    onOpenSettings = onOpenEmotionOptionSettings,
                    getLabel = { it.description },
                    moodColor = moodColor
                )
            }
        }
    }
}

@Composable
private fun MoodJournalEmotionTopAppBar(
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
private fun MoodJournalEmotionBottomBar(
    anySelected: Boolean,
    moodColor: MoodColor,
    onSave: () -> Unit,
    onFinish: () -> Unit,
    onNext: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = Dimension.spacingRegular,
                end = Dimension.spacingRegular,
                bottom = Dimension.spacingRegular
            ),
        horizontalArrangement = Arrangement.spacedBy(Dimension.spacingRegular),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LelloFilledButton(
            label = "Concluir",
            enabled = anySelected,
            onClick = { onSave(); onFinish() },
            moodColor = moodColor,
            modifier = Modifier.weight(1f)
        )
        LelloFloatingActionButton(
            icon = LelloIcons.Outlined.ArrowRightLarge.imageVector,
            contentDescription = "Próximo",
            enabled = anySelected,
            moodColor = moodColor,
            onClick = onNext
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
private fun MoodJournalEmotionScreenPreview_LightMode() {
    LelloTheme {
        MoodJournalEmotionScreenContent(
            entryTime = "09:41",
            coinsAcquired = 100,
            emotionOptions = EmotionOptionMock.list,
            onEmotionOptionToggle = { _ -> },
            onOpenEmotionOptionSettings = {},
            onSave = {},
            onBack = {},
            onNext = {},
            onFinish = {},
            moodColor = MoodColor.DEFAULT
        )
    }
}

// endregion Previews