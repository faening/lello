package io.github.faening.lello.feature.diary.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.hilt.navigation.compose.hiltViewModel
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.pill.LelloFilledPill
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.domain.mock.MoodJournalMock
import io.github.faening.lello.core.domain.util.toLocalDateTime
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.feature.diary.DiaryViewModel
import java.time.format.DateTimeFormatter

@Composable
fun DiaryMoodDetailsScreen(
    viewModel: DiaryViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val selectedMoodJournal by viewModel.selectedMoodJournal.collectAsState()

    DiaryMoodDetailsScreenContent(
        moodJournal = selectedMoodJournal ?: return,
        onBackClick = onBackClick
    )
}

@Composable
private fun DiaryMoodDetailsScreenContent(
    moodJournal: MoodJournal,
    onBackClick: () -> Unit,
) {
    LelloTheme {
        Scaffold(
            topBar = { TopAppBarSection(onBackClick) }
        ) { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
                    .padding(Dimension.spacingMedium)
                    .verticalScroll(rememberScrollState())
            ) {
                moodJournal.let { mood ->
                    Column {
                        MoodTimestampSection(mood)
                        MoodDetailsSection(mood)
                        EmotionDetailsSection(mood)
                        HealthDetailsSection(mood)
                        ClimateDetailsSection(mood)
                        LocationDetailsSection(mood)
                        SocialDetailsSection(mood)
                        NotesSection(mood)
                    }
                }
            }
        }
    }
}

@Composable
private fun TopAppBarSection(
    onBackClick: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Diário de Humor"),
        navigateUp = TopAppBarAction(onClick = onBackClick),
        moodColor = MoodColor.INVERSE
    )
}

@Composable
private fun MoodTimestampSection(
    mood: MoodJournal
) {
    DetailSection(
        title = "Data e hora",
        content = {
            val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy - HH'h' mm'm'")
            Text(
                text = formatter.format(mood.createdAt.toLocalDateTime()),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    )
}

@Composable
private fun MoodDetailsSection(
    mood: MoodJournal
) {
    DetailSection(
        title = "Como você descreveu seu humor?",
        content = {
            LelloFilledPill(mood.mood.label)
        }
    )
}

@Composable
private fun EmotionDetailsSection(
    mood: MoodJournal
) {
    DetailSection(
        title = "Quais emoções faziam mais sentido naquele momento?",
        content = {
            if (mood.emotionOptions.isEmpty()) {
                LelloFilledPill("Não especificado")
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimension.spacingSmall),
                    verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
                ) {
                    mood.emotionOptions.forEach { it ->
                        LelloFilledPill(it.description)
                    }
                }
            }
        }
    )
}

@Composable
private fun HealthDetailsSection(
    mood: MoodJournal
) {
    DetailSection(
        title = "Sentiu alguma mudança física?",
        content = {
            if (mood.healthOptions.isEmpty()) {
                LelloFilledPill("Não")
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimension.spacingSmall),
                    verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
                ) {
                    mood.healthOptions.forEach { it ->
                        LelloFilledPill(it.description)
                    }
                }
            }
        }
    )
}

@Composable
private fun ClimateDetailsSection(
    mood: MoodJournal
) {
    DetailSection(
        title = "Como estava o clima?",
        content = {
            if (mood.climateOptions.isEmpty()) {
                LelloFilledPill("Não especificado")
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimension.spacingSmall),
                    verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
                ) {
                    mood.climateOptions.forEach { it ->
                        LelloFilledPill(it.description)
                    }
                }
            }
        }
    )
}

@Composable
private fun LocationDetailsSection(
    mood: MoodJournal
) {
    DetailSection(
        title = "Onde você estava?",
        content = {
            if (mood.locationOptions.isEmpty()) {
                LelloFilledPill("Não especificado")
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimension.spacingSmall),
                    verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
                ) {
                    mood.locationOptions.forEach { it ->
                        LelloFilledPill(it.description)
                    }
                }
            }
        }
    )
}

@Composable
private fun SocialDetailsSection(
    mood: MoodJournal
) {
    DetailSection(
        title = "Com quem você estava?",
        content = {
            if (mood.socialOptions.isEmpty()) {
                LelloFilledPill("Não especificado")
            } else {
                FlowRow(
                    horizontalArrangement = Arrangement.spacedBy(Dimension.spacingSmall),
                    verticalArrangement = Arrangement.spacedBy(Dimension.spacingSmall)
                ) {
                    mood.socialOptions.forEach { it ->
                        LelloFilledPill(it.description)
                    }
                }
            }
        }
    )
}

@Composable
private fun NotesSection(
    mood: MoodJournal
) {
    mood.reflection?.takeIf { it.isNotBlank() }?.let { notes ->
        DetailSection(
            title = "Notas",
            content = {
                Text(
                    text = notes,
                    style = MaterialTheme.typography.bodyLarge
                )
            }
        )
    }
}

@Composable
private fun DetailSection(
    title: String,
    content: @Composable () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Dimension.spacingLarge),
        verticalArrangement = Arrangement.spacedBy(Dimension.spacingMedium)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge
        )
        content()
    }
}

// region Previews

@Preview(
    name = "Default",
    group = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun DiaryMoodDetailsScreenPreview_LightMode() {
    DiaryMoodDetailsScreenContent(
        moodJournal = MoodJournalMock.list.first(),
        onBackClick = {},
    )
}

@Preview(
    name = "Default",
    group = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun DiaryMoodDetailsScreenPreview_DarkMode() {
    DiaryMoodDetailsScreenContent(
        moodJournal = MoodJournalMock.list.first(),
        onBackClick = {},
    )
}

// endregion Previews