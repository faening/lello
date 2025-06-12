package io.github.faening.lello.feature.journal.meal.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.LelloFilledButton
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.journal.meal.JournalMealViewModel

@Composable
internal fun JournalMealSummaryScreen(
    viewModel: JournalMealViewModel,
    onExit: () -> Unit
) {

    LelloTheme {
        JournalMealSummaryContainer(
            onExit = onExit
        )
    }
}

@Composable
private fun JournalMealSummaryContainer(
    onExit: () -> Unit
) {
    Scaffold(
        bottomBar = { JournalMealSummaryBottomBar(onExit) }
    ) { paddingValues ->
        JournalMealSummaryContent(
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun JournalMealSummaryBottomBar(
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
private fun JournalMealSummaryContent(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(Dimension.Medium),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Aqui virá a futura pontuação, conquistas, XP etc.
        Text(
            text = "Diário concluído!",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = Dimension.Large)
        )
        // Outras informações (pontuação, mascote etc.) vão aqui
    }
}

@Composable
@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
private fun JournalMealSummaryScreenPreview() {
    LelloTheme {
        JournalMealSummaryContainer(
            onExit = {},
        )
    }
}