package io.github.faening.lello.feature.journal.mood.screen

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
import androidx.compose.runtime.collectAsState
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
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.feature.journal.mood.MoodJournalViewModel
import kotlinx.coroutines.delay

@Composable
internal fun MoodJournalSummaryScreen(
    viewModel: MoodJournalViewModel,
    onExit: () -> Unit
) {
    val moodColor by viewModel.currentMood.collectAsState()
    val exoPlayer = viewModel.exoPlayer
    var isExiting by remember { mutableStateOf(false) }

    LaunchedEffect(isExiting) {
        if (isExiting) {
            delay(500)
            onExit()
        }
    }

    MoodJournalSummaryScreenContent(
        exoPlayer = exoPlayer,
        isExiting = isExiting,
        moodColor = moodColor,
        onExit = onExit
    )
}

@OptIn(ExperimentalStdlibApi::class)
@Composable
private fun MoodJournalSummaryScreenContent(
    exoPlayer: ExoPlayer?,
    isExiting: Boolean,
    moodColor: MoodColor,
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
                MoodJournalSummaryBottomBar(
                    moodColor = moodColor,
                    onExit = onExit
                )
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
private fun MoodJournalSummaryBottomBar(
    moodColor: MoodColor,
    onExit: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.spacingRegular)
    ) {
        LelloFilledButton(
            label = "Sair",
            onClick = onExit,
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
private fun MoodJournalSummaryScreenPreview_LightMode() {
    LelloTheme {
        MoodJournalSummaryScreenContent(
            exoPlayer = null,
            isExiting = false,
            moodColor = MoodColor.DEFAULT,
            onExit = {},
        )
    }
}

// endregion Previews