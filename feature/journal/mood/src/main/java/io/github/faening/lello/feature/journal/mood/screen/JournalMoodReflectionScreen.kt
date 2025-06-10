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
import io.github.faening.lello.core.designsystem.component.LelloTextArea
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.journal.mood.JournalMoodViewModel

@Composable
internal fun JournalMoodReflectionScreen(
    viewModel: JournalMoodViewModel,
    onBack: () -> Unit,
    onFinish: () -> Unit
) {
    val mood by viewModel.currentMood.collectAsState()
    val entryTime by viewModel.entryDateTime.collectAsState()
    val reflection by viewModel.reflection.collectAsState()

    LelloTheme(scheme = mood.colorScheme) {
        JournalMoodReflectionContainer(
            entryTime = entryTime,
            reflection = reflection,
            onValueChange = viewModel::updateReflection,
            onBack = onBack,
            onFinish = onFinish
        )
    }
}

@Composable
private fun JournalMoodReflectionContainer(
    entryTime: String,
    reflection: String,
    onValueChange: (String) -> Unit,
    onBack: () -> Unit,
    onFinish: () -> Unit
) {
    Scaffold(
        topBar = { JournalMoodReflectionTopBar(entryTime, onBack) },
        bottomBar = { JournalMoodReflectionBottomBar(onFinish) }
    ) { paddingValues ->
        JournalMoodReflectionContent(
            reflection = reflection,
            onValueChange = onValueChange,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@Composable
private fun JournalMoodReflectionTopBar(
    entryTime: String,
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Hoje, $entryTime"),
        navigateUp = TopAppBarAction(onClick = onBack)
    )
}

@Composable
private fun JournalMoodReflectionBottomBar(
    onFinish: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(Dimension.Medium)
    ) {
        LelloFilledButton(
            label = "Concluir",
            onClick = onFinish
        )
    }
}

@Composable
private fun JournalMoodReflectionContent(
    reflection: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(Dimension.Medium)
    ) {
        Text(
            text = "Quer anotar algum detalhe importante ou uma reflexão neste diário?",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(Dimension.ExtraLarge))
        LelloTextArea(
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
private fun JournalMoodStepOneScreenPreview() {
    LelloTheme {
        JournalMoodReflectionContainer(
            entryTime = "12:41",
            reflection = "",
            onValueChange = {},
            onBack = {},
            onFinish = {}
        )
    }
}