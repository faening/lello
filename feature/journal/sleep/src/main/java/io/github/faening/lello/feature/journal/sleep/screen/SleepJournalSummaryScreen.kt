package io.github.faening.lello.feature.journal.sleep.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.media3.exoplayer.ExoPlayer
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.media.LelloSummaryBackground
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.journal.sleep.SleepJournalViewModel
import kotlinx.coroutines.delay

@Composable
internal fun SleepJournalSummaryScreen(
    viewModel: SleepJournalViewModel,
    onExit: () -> Unit
) {
    val exoPlayer = viewModel.exoPlayer
    var isExiting by remember { mutableStateOf(false) }

    LaunchedEffect(isExiting) {
        if (isExiting) {
            delay(500)
            onExit()
        }
    }

    SleepJournalSummaryScreenContent(
        exoPlayer = exoPlayer,
        isExiting = isExiting,
        onExit = onExit
    )
}

@Composable
private fun SleepJournalSummaryScreenContent(
    exoPlayer: ExoPlayer?,
    isExiting: Boolean,
    onExit: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        LelloSummaryBackground(
            exoPlayer = exoPlayer,
            isExiting = isExiting,
            modifier = Modifier.fillMaxSize()
        )

        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = {
                SleepJournalSummaryBottomAppBar(onExit)
            }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimension.spacingRegular),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) { }

            Box(modifier = Modifier.padding(paddingValues))
        }
    }
}

@Composable
private fun SleepJournalSummaryBottomAppBar(
    onExit: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.spacingRegular)
    ) {
        LelloFilledButton(
            label = "Sair",
            onClick = onExit
        )
    }
}

// region: Previews

@Composable
@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
private fun SleepJournalSummaryScreenPreview() {
    LelloTheme {
        SleepJournalSummaryScreenContent(
            exoPlayer = null,
            isExiting = false,
            onExit = {},
        )
    }
}

// endregion: Previews