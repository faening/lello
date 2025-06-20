package io.github.faening.lello.feature.journal.mood.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.component.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.component.LelloSliderVertical
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.journal.mood.MoodJournalViewModel
import io.github.faening.lello.feature.journal.mood.model.MoodJournalColorScheme
import io.github.faening.lello.core.designsystem.R as designsystemR

@Composable
internal fun MoodJournalScreen(
    viewModel: MoodJournalViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    val mood by viewModel.currentMood.collectAsState()
    val entryTime by viewModel.entryDateTime.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.captureEntryDateTime()
    }

    LelloTheme(scheme = mood.colorScheme) {
        MoodJournalContainer(
            mood = mood,
            entryTime = entryTime,
            onBack = onBack,
            onNext = onNext,
            onMoodChange = { viewModel.updateMood(it) }
        )
    }
}

@Composable
private fun MoodJournalContainer(
    mood: MoodJournalColorScheme,
    entryTime: String,
    onBack: () -> Unit,
    onNext: () -> Unit,
    onMoodChange: (MoodJournalColorScheme) -> Unit
) {
    Scaffold(
        topBar = { MoodJournalTopBar(entryTime, onBack) },
        bottomBar = { MoodJournalBottomBar(onNext) }
    ) { paddingValues ->
        MoodJournalContent(
            mood = mood,
            onMoodChange = onMoodChange,
            modifier = Modifier.padding(paddingValues)
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun MoodJournalTopBar(
    entryTime: String,
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Hoje, $entryTime"),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
private fun MoodJournalBottomBar(
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
private fun MoodJournalContent(
    mood: MoodJournalColorScheme,
    onMoodChange: (MoodJournalColorScheme) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight()
            .padding(Dimension.Medium)
    ) {
        Text(
            text = "Como você descreve seu humor neste momento?",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(Dimension.ExtraLarge))
        MoodJournalSelectorRow(
            mood = mood,
            onMoodChange = onMoodChange,
            modifier = Modifier.weight(1f)
        )
        Spacer(modifier = Modifier.height(Dimension.ExtraLarge))
        // MoodJournalBottomBar(onNext)
    }
}

@Composable
private fun MoodJournalSelectorRow(
    mood: MoodJournalColorScheme,
    onMoodChange: (MoodJournalColorScheme) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        MoodLabelColumn(modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(Dimension.Medium))
        MoodSliderColumn(mood = mood, onMoodChange = onMoodChange, modifier = Modifier.weight(1f))
        Spacer(modifier = Modifier.width(Dimension.Medium))
        MoodIconColumn(modifier = Modifier.weight(1f))
    }
}

@Composable
private fun MoodLabelColumn(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MoodJournalColorScheme.entries.forEach {
            Text(
                text = it.label,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun MoodSliderColumn(
    mood: MoodJournalColorScheme,
    onMoodChange: (MoodJournalColorScheme) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start
    ) {
        LelloSliderVertical(
            steps = MoodJournalColorScheme.entries.size,
            currentStep = MoodJournalColorScheme.entries.indexOf(mood),
            onStepSelected = { index -> onMoodChange(MoodJournalColorScheme.entries[index]) },
            enableStepDrag = true
        )
    }
}

@Composable
private fun MoodIconColumn(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MoodJournalColorScheme.entries.forEach {
            Icon(
                painter = painterResource(it.iconRes),
                contentDescription = it.label,
                tint = Color.Unspecified,
                modifier = Modifier.size(68.dp)
            )
        }
    }
}

@Composable
@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
fun MoodJournalScreenPreview() {
    LelloTheme {
        MoodJournalContainer(
            mood = MoodJournalColorScheme.JOYFUL,
            entryTime = "09:41",
            onBack = {},
            onNext = {},
            onMoodChange = {}
        )
    }
}