package io.github.faening.lello.feature.journal.mood.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
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
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.faening.lello.core.designsystem.component.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.component.LelloSelectablePill
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
 * Screen 2: Parte do fluxo de diário de humor e que se trata de uma etapa de seleção de emoções.
 */
@Composable
fun JournalMoodEmotionSelectionRoute(
    onBack: () -> Unit,
    onNext: () -> Unit,
    onOpenRegistration: () -> Unit,
    viewModel: JournalMoodViewModel = hiltViewModel()
) {
    val mood by viewModel.selectedMood.collectAsState()
    val entryTime by viewModel.entryTimeFormatted.collectAsState()
    val emotions by viewModel.emotionOptions.collectAsState()

    LelloTheme(scheme = mood.colorScheme) {
        JournalMoodEmotionSelectionScreen(
            entryTime = entryTime,
            emotions = emotions,
            onBack = onBack,
            onNext = onNext,
            onOpenRegistration = onOpenRegistration
        )
    }
}

@Composable
private fun JournalMoodEmotionSelectionScreen(
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
        floatingActionButton = {
            LelloFloatingActionButton(
                icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
                contentDescription = "Próximo",
                colorScheme = colorScheme,
                onClick = onNext,
            )
        },
        modifier = Modifier
    ) { paddingValues ->
        Column(
            modifier = Modifier
            .padding(paddingValues)
            .padding(16.dp)
        ) {
            Text(
                text = "Quais emoções fazem mais sentido neste momento?",
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(Dimension.ExtraLarge))
            LazyVerticalGrid(
                columns = GridCells.Adaptive(minSize = 120.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(emotions) { emotion ->
                    LelloSelectablePill(
                        label = emotion.description,
                        selected = selected.contains(emotion.description),
                        onClick = {
                            selected =
                                if (selected.contains(emotion.description)) selected - emotion.description
                                else selected + emotion.description
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
fun JournalMoodEmotionSelectionScreenPreview() {
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
            description = "Enérgico",
            blocked = false,
            active = true
        )
    )

    LelloTheme {
        JournalMoodEmotionSelectionScreen(
            emotions = emotions,
            entryTime = "12:41",
            onBack = {},
            onNext = {},
            onOpenRegistration = {}
        )
    }
}