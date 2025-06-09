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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    onOpenEmotionSettings: () -> Unit
) {
    val mood by viewModel.selectedMood.collectAsState()
    val entryTime by viewModel.entryTimeFormatted.collectAsState()
    val emotions by viewModel.emotions.collectAsState()

    LelloTheme(scheme = mood.colorScheme) {
        JournalMoodEmotionContainer(
            entryTime = entryTime,
            emotions = emotions,
            onBack = onBack,
            onNext = onNext,
            onFinish = onFinish,
            onOpenEmotionSettings = onOpenEmotionSettings
        )
    }
}

@Composable
private fun JournalMoodEmotionContainer(
    entryTime: String,
    emotions: List<EmotionOption>,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    onOpenEmotionSettings: () -> Unit
) {
    var selected by remember { mutableStateOf(setOf<String>()) }

    Scaffold(
        topBar = { JournalMoodEmotionTopBar(entryTime, onBack) },
        bottomBar = { JournalMoodEmotionBottomBar(selected.isNotEmpty(), onNext, onFinish) }
    ) { paddingValues ->
        JournalMoodEmotionContent(
            emotions = emotions,
            selected = selected,
            onEmotionToggled = { emotion ->
                selected = if (selected.contains(emotion)) selected - emotion else selected + emotion
            },
            onOpenEmotionSettings = onOpenEmotionSettings,
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
    emotions: List<EmotionOption>,
    selected: Set<String>,
    onEmotionToggled: (String) -> Unit,
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
            options = emotions,
            isSelected = { selected.contains(it.description) },
            onToggle = { option ->
                onEmotionToggled(option.description)
            },
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
    val emotions = listOf(
        EmotionOption(
            id = 1,
            description = "Feliz",
            blocked = false,
            active = true
        ),
        EmotionOption(
            id = 1,
            description = "Triste",
            blocked = false,
            active = true
        ),
        EmotionOption(
            id = 1,
            description = "Cansado",
            blocked = false,
            active = true
        ),
        EmotionOption(
            id = 1,
            description = "Fome",
            blocked = false,
            active = true
        ),
        EmotionOption(
            id = 1,
            description = "Enérgico",
            blocked = false,
            active = true
        ),
        EmotionOption(
            id = 1,
            description = "Animado",
            blocked = false,
            active = true
        ),
        EmotionOption(
            id = 1,
            description = "Confiante",
            blocked = false,
            active = true
        )
    )

    LelloTheme {
        JournalMoodEmotionContainer(
            emotions = emotions,
            entryTime = "12:41",
            onBack = {},
            onNext = {},
            onFinish = {},
            onOpenEmotionSettings = {}
        )
    }
}