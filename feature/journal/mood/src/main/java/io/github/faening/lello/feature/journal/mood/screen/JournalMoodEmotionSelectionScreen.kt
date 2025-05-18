package io.github.faening.lello.feature.journal.mood.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.feature.journal.mood.JournalMoodViewModel

/**
 * Screen 2: Parte do fluxo de diário de humor e que se trata de uma etapa de seleção de emoções.
 */
@Composable
fun JournalMoodEmotionSelectionRoute(
    onBack: () -> Unit,
    onNext: () -> Unit,
    onOpenMenu: () -> Unit,
    viewModel: JournalMoodViewModel = hiltViewModel(),
) {


    JournalMoodEmotionSelectionScreen(
        onBack = onBack,
        onNext = onNext,
        onOpenMenu = onOpenMenu,
    )
}

@Composable
private fun JournalMoodEmotionSelectionScreen(
    onBack: () -> Unit,
    onNext: () -> Unit,
    onOpenMenu: () -> Unit,
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            TopAppBar(
                // title = startTime.toString(),
                onBack = onBack,
                onOpenMenu = onOpenMenu
            )
        }
    ) { paddingValues ->
        Column(
            modifier = modifier.padding(paddingValues)
        ) {
            Text(
                text = "Journal Mood Screen - Emotion Selection",
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopAppBar(
    title: String = "",
    onBack: () -> Unit = {},
    onOpenMenu: () -> Unit,
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = title),
        navigateUp = TopAppBarAction(onClick = onBack),
        actions = listOf(
            TopAppBarAction(onClick = onOpenMenu)
        )
    )
}