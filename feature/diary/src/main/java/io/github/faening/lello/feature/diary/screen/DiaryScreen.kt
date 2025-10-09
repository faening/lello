package io.github.faening.lello.feature.diary.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloCalendarTopAppBar
import io.github.faening.lello.core.designsystem.component.card.DiaryCardOptions
import io.github.faening.lello.core.designsystem.component.card.LelloDiaryCard
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.domain.mock.MealJournalMock
import io.github.faening.lello.core.domain.mock.MoodJournalMock
import io.github.faening.lello.core.domain.mock.SleepJournalMock
import io.github.faening.lello.core.domain.util.isSameDay
import io.github.faening.lello.core.model.journal.MealJournal
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.journal.SleepJournal
import io.github.faening.lello.core.model.reward.RewardOrigin
import io.github.faening.lello.feature.diary.DiaryViewModel
import java.time.LocalDate
import java.util.Date

@Composable
fun DiaryScreen(
    viewModel: DiaryViewModel,
    onMoodJournalClick: (Long) -> Unit = {},
    onMealJournalClick: (Long) -> Unit = {},
) {
    val selectedDate by viewModel.selectedDate.collectAsState()
    val moodJournals by viewModel.moodJournals.collectAsState()
    val mealJournals by viewModel.mealJournals.collectAsState()
    val sleepJournals by viewModel.sleepJournals.collectAsState()

    val dayMoodJournals = moodJournals
        .filter { it.createdAt.isSameDay(selectedDate) }
        .sortedByDescending { it.createdAt }

    val dayMealJournals = mealJournals
        .filter { it.createdAt.isSameDay(selectedDate) }
        .sortedByDescending { it.createdAt }

    val daySleepJournals = sleepJournals
        .filter { it.createdAt.isSameDay(selectedDate) }
        .sortedByDescending { it.createdAt }

    LaunchedEffect(Unit) {
        viewModel.setSelectedDate(selectedDate)
    }

    DiaryScreenContent(
        selectedDate = selectedDate,
        moodJournals = dayMoodJournals,
        onMoodJournalClick = onMoodJournalClick,
        mealJournals = dayMealJournals,
        onMealJournalClick = onMealJournalClick,
        sleepJournals = daySleepJournals,
        onSelectDate = viewModel::setSelectedDate,
        getRewardAmount = viewModel::getRewardAmount
    )
}

@Composable
private fun DiaryScreenContent(
    selectedDate: LocalDate,
    moodJournals: List<MoodJournal>,
    onMoodJournalClick: (Long) -> Unit = {},
    mealJournals: List<MealJournal>,
    onMealJournalClick: (Long) -> Unit = {},
    sleepJournals: List<SleepJournal>,
    onSelectDate: (LocalDate) -> Unit = {},
    getRewardAmount: suspend (RewardOrigin, Long) -> Int = { _, _ -> 0 }
) {
    LelloTheme {
        Scaffold(
            topBar = {
                TopAppBarSection(selectedDate, onSelectDate)
            }
        ) { paddingValues ->
            if (moodJournals.isEmpty() && mealJournals.isEmpty() && sleepJournals.isEmpty()) {
                EmptyContentSection(paddingValues)
            } else {
                val scrollState = rememberScrollState()

                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(Dimension.spacingRegular)
                        .verticalScroll(scrollState),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    SleepJournalsSection(
                        daySleepJournals = sleepJournals,
                        getRewardAmount = getRewardAmount
                    )

                    MealJournalsSection(
                        dayMealJournals = mealJournals,
                        onMealJournalClick = onMealJournalClick,
                        getRewardAmount = getRewardAmount
                    )

                    MoodJournalsSection(
                        dayMoodJournals = moodJournals,
                        onMoodJournalClick = onMoodJournalClick,
                        getRewardAmount = getRewardAmount
                    )
                }
            }
        }
    }
}

@Composable
private fun TopAppBarSection(
    selectedDate: LocalDate,
    onSelectDate: (LocalDate) -> Unit
) {
    LelloCalendarTopAppBar(
        selectedDate = selectedDate,
        onDateSelected = onSelectDate,
        moodColor = MoodColor.INVERSE
    )
}

@Composable
private fun EmptyContentSection(
    paddingValues: PaddingValues
) {
    Box(
        modifier = Modifier
            .padding(paddingValues)
            .padding(Dimension.spacingRegular)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "Não existem registros para o período selecionado",
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )
    }
}

@Composable
private fun SleepJournalsSection(
    daySleepJournals: List<SleepJournal>,
    getRewardAmount: suspend (RewardOrigin, Long) -> Int
) {
    if (daySleepJournals.isNotEmpty()) {
        daySleepJournals.forEach { journal ->
            val journalId = journal.id ?: 0L
            val reward by produceState(initialValue = 0, key1 = journalId) {
                value = getRewardAmount(RewardOrigin.SLEEP_JOURNAL, journalId)
            }
            LelloDiaryCard(
                properties = DiaryCardOptions.SleepJournal,
                dateTime = Date(journal.createdAt),
                reward = reward,
                modifier = Modifier.padding(bottom = Dimension.spacingMedium)
            )
        }
    }
}

@Composable
private fun MealJournalsSection(
    dayMealJournals: List<MealJournal>,
    onMealJournalClick: (Long) -> Unit = {},
    getRewardAmount: suspend (RewardOrigin, Long) -> Int
) {
    if (dayMealJournals.isNotEmpty()) {
        dayMealJournals.forEach { journal ->
            val journalId = journal.id ?: 0L
            val reward by produceState(initialValue = 0, key1 = journalId) {
                value = getRewardAmount(RewardOrigin.MEAL_JOURNAL, journalId)
            }
            LelloDiaryCard(
                properties = DiaryCardOptions.MealJournal,
                dateTime = Date(journal.createdAt),
                reward = reward,
                onClick = { onMealJournalClick(journalId) },
                modifier = Modifier.padding(bottom = Dimension.spacingMedium)
            )
        }
    }
}

@Composable
private fun MoodJournalsSection(
    dayMoodJournals: List<MoodJournal>,
    onMoodJournalClick: (Long) -> Unit = {},
    getRewardAmount: suspend (RewardOrigin, Long) -> Int
) {
    if (dayMoodJournals.isNotEmpty()) {
        dayMoodJournals.forEach { journal ->
            val journalId = journal.id ?: 0L
            val reward by produceState(initialValue = 0, key1 = journalId) {
                value = getRewardAmount(RewardOrigin.MOOD_JOURNAL, journalId)
            }
            LelloDiaryCard(
                properties = when (journal.mood.name) {
                    "SERENE" -> DiaryCardOptions.MoodJournalSerene
                    "JOYFUL" -> DiaryCardOptions.MoodJournalJoyful
                    "BALANCED" -> DiaryCardOptions.MoodJournalBalanced
                    "TROUBLED" -> DiaryCardOptions.MoodJournalTroubled
                    "OVERWHELMED" -> DiaryCardOptions.MoodJournalOverwhelmed
                    else -> DiaryCardOptions.MoodJournalBalanced
                },
                dateTime = Date(journal.createdAt),
                reward = reward,
                onClick = { onMoodJournalClick(journalId) },
                modifier = Modifier.padding(bottom = Dimension.spacingMedium)
            )
        }
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
private fun DiaryScreenPreview_LightMode_Default() {
    DiaryScreenContent(
        selectedDate = LocalDate.now(),
        moodJournals = MoodJournalMock.list.take(2),
        mealJournals = MealJournalMock.list.take(2),
        sleepJournals = SleepJournalMock.list.take(2)
    )
}

@Preview(
    name = "Empty",
    group = "Light Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
private fun DiaryScreenPreview_LightMode_Empty() {
    DiaryScreenContent(
        selectedDate = LocalDate.now(),
        moodJournals = emptyList(),
        mealJournals = emptyList(),
        sleepJournals = emptyList()
    )
}

@Preview(
    name = "Default",
    group = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun DiaryScreenPreview_DarkMode_Default() {
    DiaryScreenContent(
        selectedDate = LocalDate.now(),
        moodJournals = MoodJournalMock.list.take(2),
        mealJournals = MealJournalMock.list.take(2),
        sleepJournals = SleepJournalMock.list.take(2)
    )
}

@Preview(
    name = "Empty",
    group = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun DiaryScreenPreview_DarkMode_Empty() {
    DiaryScreenContent(
        selectedDate = LocalDate.now(),
        moodJournals = emptyList(),
        mealJournals = emptyList(),
        sleepJournals = emptyList()
    )
}

// endregion Previews