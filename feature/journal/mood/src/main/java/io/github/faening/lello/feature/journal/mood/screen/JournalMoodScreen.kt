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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.faening.lello.core.designsystem.component.LelloFloatingActionButton
import io.github.faening.lello.core.designsystem.component.LelloSliderVertical
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.journal.mood.JournalMoodViewModel
import io.github.faening.lello.feature.journal.mood.model.JournalMood
import io.github.faening.lello.core.designsystem.R as designsystemR

@Composable
internal fun JournalMoodRoute(
    onBack: () -> Unit,
    onNext: () -> Unit,
    viewModel: JournalMoodViewModel = hiltViewModel(),
) {
    val mood by viewModel.selectedMood.collectAsState()
    val entryDateTime by viewModel.entryDateTimeFormatted.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.captureEntryDateTime()
    }

    LelloTheme(scheme = mood.colorScheme) {
        JournalMoodScreen(
            mood = mood,
            entryTime = entryDateTime,
            viewModel = viewModel,
            onBack = onBack,
            onNext = onNext,
        )
    }
}

@Composable
private fun JournalMoodScreen(
    mood: JournalMood,
    entryTime: String,
    viewModel: JournalMoodViewModel,
    onBack: () -> Unit,
    onNext: () -> Unit
) {
    Column(Modifier.fillMaxSize()) {
        JournalMoodScreenTopAppBar(
            title = entryTime,
            onBack = onBack
        )
        Spacer(modifier = Modifier.height(Dimension.Medium))
        JournalMoodScrenTitle()
        Spacer(modifier = Modifier.height(Dimension.Medium))
        Row(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = Dimension.Medium),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                JournalMood.entries.forEach {
                    Text(
                        text = it.label,
                        style = MaterialTheme.typography.bodyLarge,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
            Spacer(modifier = Modifier.width(Dimension.Medium))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.Start
            ) {
                LelloSliderVertical(
                    steps = JournalMood.entries.size,
                    currentStep = JournalMood.entries.indexOf(mood),
                    onStepSelected = { index ->
                        viewModel.updateMood(JournalMood.entries[index])
                    },
                    enableStepDrag = true
                )
            }
            Spacer(modifier = Modifier.width(Dimension.Medium))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                JournalMood.entries.forEach {
                    Icon(
                        painter = painterResource(it.iconRes),
                        contentDescription = it.label,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .size(68.dp)
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(Dimension.Medium))
        Box(
            modifier = Modifier
                .align(Alignment.End)
                .padding(Dimension.horizontalPadding)
        ) {
            LelloFloatingActionButton(
                icon = LelloIcons.customIcon(designsystemR.drawable.ic_arrow_large_right),
                contentDescription = "Próximo",
                onClick = onNext
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun JournalMoodScreenTopAppBar(
    title: String,
    onBack: () -> Unit = {}
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = title),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
private fun JournalMoodScrenTitle() {
    Row(
        modifier = Modifier.padding(horizontal = Dimension.horizontalPadding)
    ) {
        Text(
            text = "Como você descreve seu humor neste momento?",
            style = MaterialTheme.typography.headlineSmall
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
fun JournalMoodScreenPreview() {
    LelloTheme {
        JournalMoodScreen(
            mood = JournalMood.JOYFUL,
            entryTime = "12:00",
            viewModel = hiltViewModel(),
            onBack = {},
            onNext = {}
        )
    }
}