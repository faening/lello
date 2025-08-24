package io.github.faening.lello.feature.journal.mood.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.textfield.LelloMultilineTextField
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.journal.mood.MoodJournalViewModel

@Composable
internal fun MoodJournalReflectionScreen(
    viewModel: MoodJournalViewModel,
    onBack: () -> Unit,
    onFinish: () -> Unit
) {
    val mood by viewModel.currentMood.collectAsState()
    val entryTime by viewModel.entryDateTime.collectAsState()
    val reflection by viewModel.reflection.collectAsState()
    val coinsAcquired by viewModel.coinsAcquired.collectAsState()

    LelloTheme(moodColor = mood.colorScheme) {
        MoodJournalReflectionContainer(
            entryTime = entryTime,
            reflection = reflection,
            onValueChange = viewModel::updateReflection,
            onSave = viewModel::saveMoodJournal,
            onBack = onBack,
            onFinish = onFinish,
            coinsAcquired = coinsAcquired
        )
    }
}

@Composable
private fun MoodJournalReflectionContainer(
    entryTime: String,
    reflection: String,
    onValueChange: (String) -> Unit,
    onSave: () -> Unit,
    onBack: () -> Unit,
    onFinish: () -> Unit,
    coinsAcquired: Int,
) {
    Scaffold(
        topBar = { MoodJournalReflectionTopBar(entryTime, onBack) },
        bottomBar = { MoodJournalReflectionBottomBar(onSave, onFinish) }
    ) { paddingValues ->
        MoodJournalReflectionContent(
            reflection = reflection,
            onValueChange = onValueChange,
            coinsAcquired = coinsAcquired,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun MoodJournalReflectionTopBar(
    entryTime: String,
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Hoje, $entryTime"),
        navigateUp = TopAppBarAction(onClick = onBack)
    )
}

@Composable
private fun MoodJournalReflectionBottomBar(
    onSave: () -> Unit,
    onFinish: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.spacingRegular)
    ) {
        LelloFilledButton(
            label = "Concluir",
            onClick = {
                onSave()
                onFinish()
            }
        )
    }
}

@Composable
private fun MoodJournalReflectionContent(
    reflection: String,
    onValueChange: (String) -> Unit,
    coinsAcquired: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(Dimension.spacingRegular)
    ) {
        Text(
            text = "Quer anotar algum detalhe importante ou uma reflexão neste diário?",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(Dimension.spacingRegular))

        Text(
            text = "Ganhe $coinsAcquired moeads ao concluir",
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(Dimension.spacingExtraLarge))

        LelloMultilineTextField(
            value = reflection,
            onValueChange = onValueChange,
            placeholder = "Digite sua reflexão livre aqui...",
            maxLength = 500,
            showCounter = true,
            modifier = Modifier.fillMaxWidth()
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
private fun MoodJournalReflectionScreenPreview() {
    LelloTheme {
        MoodJournalReflectionContainer(
            entryTime = "09:41",
            reflection = "",
            onValueChange = {},
            onSave = {},
            onBack = {},
            onFinish = {},
            coinsAcquired = 100
        )
    }
}