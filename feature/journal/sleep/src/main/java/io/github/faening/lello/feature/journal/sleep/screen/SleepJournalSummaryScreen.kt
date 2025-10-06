package io.github.faening.lello.feature.journal.sleep.screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.feature.journal.sleep.SleepJournalViewModel
import io.github.faening.lello.core.designsystem.R as designsystemR

@Composable
internal fun SleepJournalSummaryScreen(
    viewModel: SleepJournalViewModel,
    onExit: () -> Unit
) {

    LelloTheme(moodColor = MoodColor.DEFAULT) {
        SleepJournalSummaryContainer(
            onExit = onExit
        )
    }
}

@Composable
private fun SleepJournalSummaryContainer(
    onExit: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = designsystemR.drawable.journal_summary),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Scaffold(
            containerColor = Color.Transparent,
            bottomBar = { SleepJournalSummaryBottomBar(onExit) }
        ) { paddingValues ->
            SleepJournalSummaryContent(
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
private fun SleepJournalSummaryBottomBar(
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

@Composable
private fun SleepJournalSummaryContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimension.spacingRegular),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) { }
}

@Composable
@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
private fun SleepJournalSummaryScreenPreview() {
    LelloTheme {
        SleepJournalSummaryContainer(
            onExit = {},
        )
    }
}