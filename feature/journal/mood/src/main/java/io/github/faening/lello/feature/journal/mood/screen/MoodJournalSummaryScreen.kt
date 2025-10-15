package io.github.faening.lello.feature.journal.mood.screen

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.feature.journal.mood.MoodJournalViewModel

@Composable
internal fun MoodJournalSummaryScreen(
    viewModel: MoodJournalViewModel,
    onExit: () -> Unit
) {
    val moodColor by viewModel.currentMood.collectAsState()

    MoodJournalSummaryContainer(
        moodColor = moodColor,
        onExit = onExit
    )
}

@Composable
private fun MoodJournalSummaryContainer(
    moodColor: MoodColor,
    onExit: () -> Unit
) {
    LelloTheme(moodColor = moodColor) {
        Box(modifier = Modifier.fillMaxSize()) {
            // Background
            Image(
                painter = painterResource(LelloIcons.Graphic.JournalSummary.resId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

            // Foreground
            Scaffold(
                containerColor = Color.Transparent,
                bottomBar = {
                    BottomBarSection(
                        moodColor = moodColor,
                        onExit = onExit
                    )
            }
            ) { paddingValues ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(Dimension.spacingRegular),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) { }
            }
        }
    }
}

@Composable
private fun BottomBarSection(
    moodColor: MoodColor,
    onExit: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.spacingRegular)
    ) {
        LelloFilledButton(label = "Sair", onClick = onExit, moodColor = moodColor)
    }
}

// region Previews

@Composable
@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
private fun MoodJournalSummaryScreenPreview_LightMode() {
    LelloTheme {
        MoodJournalSummaryContainer(
            moodColor = MoodColor.DEFAULT,
            onExit = {},
        )
    }
}

// endregion Previews