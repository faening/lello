package io.github.faening.lello.feature.journal.meal.screen

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
import io.github.faening.lello.core.designsystem.component.LelloFilledButton
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.journal.meal.MealJournalViewModel
import io.github.faening.lello.core.designsystem.R as designsystemR

@Composable
internal fun MealJournalSummaryScreen(
    viewModel: MealJournalViewModel,
    onExit: () -> Unit
) {

    LelloTheme(scheme = LelloColorScheme.DEFAULT) {
        MealJournalSummaryContainer(
            onExit = onExit
        )
    }
}

@Composable
private fun MealJournalSummaryContainer(
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
            bottomBar = { MealJournalSummaryBottomBar(onExit) }
        ) { paddingValues ->
            MealJournalSummaryContent(
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}

@Composable
private fun MealJournalSummaryBottomBar(
    onExit: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.Medium)
    ) {
        LelloFilledButton(
            label = "Sair",
            onClick = onExit
        )
    }
}

@Composable
private fun MealJournalSummaryContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimension.Medium),
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
private fun MealJournalSummaryScreenPreview() {
    LelloTheme {
        MealJournalSummaryContainer(
            onExit = {},
        )
    }
}