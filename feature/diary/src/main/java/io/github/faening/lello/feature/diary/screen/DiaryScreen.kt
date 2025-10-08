package io.github.faening.lello.feature.diary.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
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
    viewModel: DiaryViewModel
) {
    val selectedDate by viewModel.selectedDate.collectAsState()
    val moodJournal by viewModel.moodJournal.collectAsState()
    val mealJournal by viewModel.mealJournal.collectAsState()
    val sleepJournal by viewModel.sleepJournal.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.setSelectedDate(selectedDate)
    }

    DiaryScreenContent(
        selectedDate = selectedDate,
        moodJournals = moodJournal,
        mealJournals = mealJournal,
        sleepJournals = sleepJournal,
        onSelectDate = viewModel::setSelectedDate,
        getRewardAmount = viewModel::getRewardAmount
    )
}

@Composable
private fun DiaryScreenContent(
    selectedDate: LocalDate,
    moodJournals: List<MoodJournal>,
    mealJournals: List<MealJournal>,
    sleepJournals: List<SleepJournal>,
    onSelectDate: (LocalDate) -> Unit = {},
    getRewardAmount: suspend (RewardOrigin, Long) -> Int = { _, _ -> 0 }
) {
    val scrollState = rememberScrollState()
    val dayMoodJournals = moodJournals
        .filter { it.createdAt.isSameDay(selectedDate) }
        .sortedByDescending { it.createdAt }
    val dayMealJournals = mealJournals
        .filter { it.createdAt.isSameDay(selectedDate) }
        .sortedByDescending { it.createdAt }
    val daySleepJournals = sleepJournals
        .filter { it.createdAt.isSameDay(selectedDate) }
        .sortedByDescending { it.createdAt }

    LelloTheme {
        Scaffold(
            topBar = {
                LelloCalendarTopAppBar(
                    selectedDate = selectedDate,
                    onDateSelected = onSelectDate,
                    moodColor = MoodColor.INVERSE
                )
            },
        ) { paddingValues ->
            if (dayMoodJournals.isEmpty() && dayMealJournals.isEmpty() && daySleepJournals.isEmpty()) {
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
            } else {
                Column(
                    modifier = Modifier
                        .padding(paddingValues)
                        .padding(Dimension.spacingRegular)
                        .verticalScroll(scrollState),
                    horizontalAlignment =  Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    if (daySleepJournals.isNotEmpty()) {
                        JournalSection(
                            journals = daySleepJournals,
                            getJournalId = { it.id },
                            getCreatedAt = { it.createdAt },
                            getRewardOrigin = RewardOrigin.SLEEP_JOURNAL,
                            getCardOptions = { DiaryCardOptions.SleepJournal },
                            getRewardAmount = getRewardAmount,
                            modifier = Modifier.padding(bottom = Dimension.spacingMedium)
                        )
                    }
                    if (dayMealJournals.isNotEmpty()) {
                        JournalSection(
                            journals = dayMealJournals,
                            getJournalId = { it.id },
                            getCreatedAt = { it.createdAt },
                            getRewardOrigin = RewardOrigin.MEAL_JOURNAL,
                            getCardOptions = { DiaryCardOptions.MealJournal },
                            getRewardAmount = getRewardAmount,
                            modifier = Modifier.padding(bottom = Dimension.spacingMedium)
                        )
                    }
                    if (dayMoodJournals.isNotEmpty()) {
                        JournalSection(
                            journals = dayMoodJournals,
                            getJournalId = { it.id },
                            getCreatedAt = { it.createdAt },
                            getRewardOrigin = RewardOrigin.MOOD_JOURNAL,
                            getCardOptions = { journal ->
                                when (journal.mood.name) {
                                    "SERENE" -> DiaryCardOptions.MoodJournalSerene
                                    "JOYFUL" -> DiaryCardOptions.MoodJournalJoyful
                                    "BALANCED" -> DiaryCardOptions.MoodJournalBalanced
                                    "TROUBLED" -> DiaryCardOptions.MoodJournalTroubled
                                    "OVERWHELMED" -> DiaryCardOptions.MoodJournalOverwhelmed
                                    else -> DiaryCardOptions.MoodJournalBalanced
                                }
                            },
                            getRewardAmount = getRewardAmount,
                            modifier = Modifier.padding(bottom = Dimension.spacingMedium)
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun <T> JournalSection(
    journals: List<T>,
    getJournalId: (T) -> Long?,
    getCreatedAt: (T) -> Long,
    getRewardOrigin: RewardOrigin,
    getCardOptions: (T) -> DiaryCardOptions,
    getRewardAmount: suspend (RewardOrigin, Long) -> Int,
    modifier: Modifier = Modifier
) {
    journals.forEach { journal ->
        val journalId = getJournalId(journal) ?: 0L
        val reward by produceState(initialValue = 0, key1 = journalId) {
            value = getRewardAmount(getRewardOrigin, journalId)
        }
        LelloDiaryCard(
            properties = getCardOptions(journal),
            dateTime = Date(getCreatedAt(journal)),
            reward = reward,
            modifier = modifier
        )
    }
}

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
