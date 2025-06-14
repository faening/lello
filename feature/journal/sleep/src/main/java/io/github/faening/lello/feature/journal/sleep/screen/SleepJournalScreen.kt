package io.github.faening.lello.feature.journal.sleep.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.component.LelloTextField
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.journal.sleep.SleepJournalViewModel
import io.github.faening.lello.core.designsystem.R as designsystemR

@Composable
internal fun SleepJournalScreen(
    viewModel: SleepJournalViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    LelloTheme {
        SleepJournalContainer(
            sleepDuration = viewModel.sleepDuration.collectAsState().value,
            onSleepDurationChange = viewModel::updateSleepDuration,
            onBack = onBack,
            onNext = onNext
        )
    }
}

@Composable
private fun SleepJournalContainer(
    sleepDuration: String,
    onSleepDurationChange: (String) -> Unit,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    Scaffold(
        topBar = { SleepJournalTopBar(onBack) },
        bottomBar = { SleepJournaBottomBar(onNext) }
    ) { paddingValues ->
        MealJournalContent(
            sleepDuration = sleepDuration,
            onSleepDurationChange = onSleepDurationChange,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun SleepJournalTopBar(
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Sono"),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
private fun SleepJournaBottomBar(
    onNext: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentWidth(Alignment.End)
            .padding(Dimension.Medium)
    ) {
        LelloFloatingActionButton(
            icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
            contentDescription = "Próximo",
            onClick = onNext
        )
    }
}

@Composable
private fun MealJournalContent(
    sleepDuration: String,
    onSleepDurationChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(Dimension.Medium)
    ) {
        Text(
            text = "Quanto tempo você dormiu?",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(Dimension.ExtraLarge))

        LelloTextField(
            value = sleepDuration,
            onValueChange = onSleepDurationChange,
            placeholder = "Ex: 7h 30min",
            maxLength = 10,
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(Dimension.Medium))

        Text(
            text = "Dica: Considere apenas o tempo efetivamente dormido.",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
fun SleepJournalScreenPreview() {
    LelloTheme {
        SleepJournalContainer(
            sleepDuration = "7h 30m",
            onSleepDurationChange = {},
            onBack = {},
            onNext = {},
        )
    }
}