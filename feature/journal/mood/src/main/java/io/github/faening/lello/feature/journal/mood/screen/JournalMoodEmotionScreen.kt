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
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.domain.mock.EmotionOptionMock
import io.github.faening.lello.core.model.journal.EmotionOption
import io.github.faening.lello.feature.journal.mood.JournalMoodViewModel
import io.github.faening.lello.core.designsystem.R as designsystemR

/**
 * Step 2: Seleção das emoções do usuário.
 */
@Composable
internal fun JournalMoodEmotionScreen(
    viewModel: JournalMoodViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    onOpenEmotionOptionSettings: () -> Unit
) {
    val mood by viewModel.currentMood.collectAsState()
    val entryTime by viewModel.entryTimeFormatted.collectAsState()
    val emotionOptions by viewModel.emotionOptions.collectAsState()

    LelloTheme(scheme = mood.colorScheme) {
        JournalMoodEmotionContainer(
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
private fun JournalMoodEmotionContainer(
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
        topBar = { JournalMoodEmotionTopBar(entryTime, onBack) },
        bottomBar = { JournalMoodEmotionBottomBar(anySelected, onNext, onFinish) }
    ) { paddingValues ->
        JournalMoodEmotionContent(
            emotionOptions = emotionOptions,
            onEmotionOptionToggle = onEmotionOptionToggle,
            onOpenEmotionSettings = onOpenEmotionOptionSettings,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun JournalMoodEmotionTopBar(
    entryTime: String,
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Hoje, $entryTime"),
        navigateUp = TopAppBarAction(onClick = onBack)
    )
}

@Composable
private fun JournalMoodEmotionBottomBar(
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
private fun JournalMoodEmotionContent(
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
private fun JournalMoodStepOneScreenPreview() {
    LelloTheme {
        JournalMoodEmotionContainer(
            entryTime = "12:41",
            emotionOptions = EmotionOptionMock.list,
            onEmotionOptionToggle = { _ -> },
            onOpenEmotionOptionSettings = {},
            onBack = {},
            onNext = {},
            onFinish = {}
        )
    }
}