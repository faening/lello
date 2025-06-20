package io.github.faening.lello.feature.journal.mood.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import io.github.faening.lello.core.designsystem.component.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.component.LelloOptionPillSelector
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.domain.mock.EmotionOptionMock
import io.github.faening.lello.core.model.option.EmotionOption
import io.github.faening.lello.feature.journal.mood.MoodJournalViewModel
import io.github.faening.lello.core.designsystem.R as designsystemR

@Composable
internal fun MoodJournalEmotionScreen(
    viewModel: MoodJournalViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    onOpenEmotionOptionSettings: () -> Unit
) {
    val mood by viewModel.currentMood.collectAsState()
    val entryTime by viewModel.entryDateTime.collectAsState()
    val emotionOptions by viewModel.emotionOptions.collectAsState()

    LelloTheme(scheme = mood.colorScheme) {
        MoodJournalEmotionContainer(
            entryTime = entryTime,
            emotionOptions = emotionOptions,
            onEmotionOptionToggle = viewModel::toggleEmotionSelection,
            onOpenEmotionOptionSettings = onOpenEmotionOptionSettings,
            onBack = onBack,
            onNext = onNext,
            onFinish = onFinish
        )
    }
}

@Composable
private fun MoodJournalEmotionContainer(
    entryTime: String,
    emotionOptions: List<EmotionOption>,
    onEmotionOptionToggle: (String) -> Unit,
    onOpenEmotionOptionSettings: () -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit
) {
    val anySelected = emotionOptions.any { it.selected }

    Scaffold(
        topBar = { MoodJournalEmotionTopBar(entryTime, onBack) },
        bottomBar = { MoodJournalEmotionBottomBar(anySelected, onNext, onFinish) }
    ) { paddingValues ->
        MoodJournalEmotionContent(
            emotionOptions = emotionOptions,
            onEmotionOptionToggle = onEmotionOptionToggle,
            onOpenEmotionSettings = onOpenEmotionOptionSettings,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun MoodJournalEmotionTopBar(
    entryTime: String,
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Hoje, $entryTime"),
        navigateUp = TopAppBarAction(onClick = onBack)
    )
}

@Composable
private fun MoodJournalEmotionBottomBar(
    enabled: Boolean,
    onNext: () -> Unit,
    onFinish: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.Medium),
        horizontalArrangement = Arrangement.spacedBy(Dimension.Medium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        LelloFilledButton(
            label = "Concluir",
            enabled = enabled,
            onClick = onFinish,
            modifier = Modifier.weight(1f)
        )

        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            enabled = enabled,
            onClick = onNext
        )
    }
}

@Composable
private fun MoodJournalEmotionContent(
    emotionOptions: List<EmotionOption>,
    onEmotionOptionToggle: (String) -> Unit,
    onOpenEmotionSettings: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(Dimension.Medium)
    ) {
        Text(
            text = "Quais emoções fazem mais sentido neste momento?",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(Dimension.ExtraLarge))

        LelloOptionPillSelector(
            title = null,
            options = emotionOptions,
            isSelected = { it.selected },
            onToggle = { option -> onEmotionOptionToggle(option.description) },
            onOpenSettings = onOpenEmotionSettings,
            getLabel = { it.description }
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
private fun MoodJournalEmotionScreenPreview() {
    LelloTheme {
        MoodJournalEmotionContainer(
            entryTime = "09:41",
            emotionOptions = EmotionOptionMock.list,
            onEmotionOptionToggle = { _ -> },
            onOpenEmotionOptionSettings = {},
            onBack = {},
            onNext = {},
            onFinish = {}
        )
    }
}