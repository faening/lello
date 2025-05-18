package io.github.faening.lello.feature.journal.mood.screen

import android.content.res.Configuration
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.feature.journal.mood.JournalMoodViewModel
import io.github.faening.lello.feature.journal.mood.model.JournalMood

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
            onBack = onBack,
            onNext = onNext,
        )
    }
}

@Composable
private fun JournalMoodScreen(
    mood: JournalMood,
    entryTime: String,
    onBack: () -> Unit,
    onNext: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier.fillMaxSize()) {
        JournalMoodTopAppBar(
            title  = entryTime,
            onBack = onBack
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(verticalArrangement = Arrangement.SpaceBetween) {
                JournalMood.entries.forEach { Text(it.label, style = MaterialTheme.typography.bodyMedium) }
            }

            Column(verticalArrangement = Arrangement.SpaceBetween) {
                JournalMood.entries.forEach {
                    Icon(
                        painter = painterResource(it.iconRes),
                        contentDescription = it.label
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(24.dp))
        Box(
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            FloatingActionButton(
                onClick = onNext,
                containerColor = mood.colorScheme.getScheme(isSystemInDarkTheme()).primary
            ) {
                Icon(Icons.Default.ArrowForward, contentDescription = "Próximo")
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun JournalMoodTopAppBar(
    title: String,
    onBack: () -> Unit = {}
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = title),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

//@Composable
//fun JournalMoodVerticalSlider(
//    mood: JournalMood,
//    onValueChange: (JournalMood) -> Unit,
//    trackColor: Color,
//    modifier: Modifier = Modifier
//) {
//    // traduzir enum -> 0f..1f e de volta
//    val value = remember(mood) { JournalMood.entries.indexOf(mood) / (JournalMood.entries.size - 1).toFloat() }
//    Slider(
//        value = value,
//        onValueChange = { frac ->
//            val index = (frac * (JournalMood.entries.size - 1)).roundToInt()
//            onValueChange(JournalMood.entries[index])
//        },
//        modifier = modifier
//            .graphicsLayer { rotationZ = -90f }
//            .padding(vertical = 24.dp),
//        colors = SliderDefaults.colors(
//            thumbColor = trackColor,
//            activeTrackColor = trackColor,
//            inactiveTrackColor = trackColor.copy(alpha = 0.3f)
//        )
//    )
//}

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
            onBack = {},
            onNext = {}
        )
    }
}