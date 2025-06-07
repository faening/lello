package io.github.faening.lello.feature.journal.mood.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.component.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.LelloSelectablePill
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.journal.EmotionOption
import io.github.faening.lello.feature.journal.mood.JournalMoodViewModel

/**
 * Screen 2: Parte do fluxo de diário de humor e que se trata de uma etapa de seleção de emoções.
 */
@Composable
fun JournalMoodEmotionRoute(
    viewModel: JournalMoodViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onFinish: () -> Unit,
    onOpenRegistration: () -> Unit
) {
    val mood by viewModel.selectedMood.collectAsState()
    val entryTime by viewModel.entryTimeFormatted.collectAsState()
    val emotions by viewModel.emotionOptions.collectAsState()

    LelloTheme(scheme = mood.colorScheme) {
        JournalMoodEmotionScreen(
            entryTime = entryTime,
            emotions = emotions,
            onBack = onBack,
            onNext = onNext,
            onOpenRegistration = onOpenRegistration
        )
    }
}

@Composable
private fun JournalMoodEmotionScreen(
    entryTime: String,
    emotions: List<EmotionOption>,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onOpenRegistration: () -> Unit
) {
    var selected by remember { mutableStateOf(setOf<String>()) }

    Scaffold(
        topBar = {
            LelloTopAppBar(
                title = TopAppBarTitle(text = "Hoje, $entryTime"),
                navigateUp = TopAppBarAction(onClick = onBack)
            )
        },
        bottomBar = {
            LelloFilledButton(
                label = "Concluir",
                enabled = selected.isNotEmpty(),
                onClick = onNext,
                modifier = Modifier
                    .padding(horizontal = Dimension.Medium, vertical = Dimension.Medium)
                    .fillMaxWidth()
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(horizontal = 16.dp, vertical = 24.dp)
        ) {
            Text(
                text = "Quais emoções fazem mais sentido neste momento?",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(24.dp))
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(Dimension.Small),
                verticalArrangement = Arrangement.spacedBy(Dimension.Small)
            ) {
                emotions.forEach { emotion ->
                    LelloSelectablePill(
                        label = emotion.description,
                        selected = selected.contains(emotion.description),
                        onClick = {
                            selected = if (selected.contains(emotion.description))
                                selected - emotion.description
                            else
                                selected + emotion.description
                        }
                    )
                }
            }
        }
    }
}

@Composable
@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
fun JournalMoodEmotionScreenPreview() {
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
        JournalMoodEmotionScreen(
            emotions = emotions,
            entryTime = "12:41",
            onBack = {},
            onNext = {},
            onOpenRegistration = {}
        )
    }
}