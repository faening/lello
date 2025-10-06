package io.github.faening.lello.feature.diary.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import io.github.faening.lello.core.designsystem.component.appbar.LelloCalendarTopAppBar
import io.github.faening.lello.core.designsystem.component.card.LelloDiaryCard
import io.github.faening.lello.core.designsystem.component.card.DiaryCardOptions
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
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
    // val filteredMoodJournals by viewModel.filteredMoodJournals.collectAsState()

    // Força o filtro ao montar a tela
    LaunchedEffect(Unit) {
        viewModel.setSelectedDate(selectedDate)
    }

    LelloTheme {
        DiaryContainer(
            selectedDate = selectedDate,
            moodJournals = moodJournal,
            mealJournals = mealJournal,
            sleepJournals = sleepJournal,
            onSelectDate = viewModel::setSelectedDate,
            getRewardAmount = viewModel::getRewardAmount
        )
    }
}

@Composable
private fun DiaryContainer(
    selectedDate: LocalDate,
    moodJournals: List<MoodJournal>,
    mealJournals: List<MealJournal>,
    sleepJournals: List<SleepJournal>,
    onSelectDate: (LocalDate) -> Unit = {},
    getRewardAmount: suspend (RewardOrigin, Long) -> Int = { _, _ -> 0 }
) {
    Scaffold(
        topBar = {
            DiaryTopAppBar(
                selectedDate = selectedDate,
                onDateSelected = onSelectDate
            )
        },
    ) { paddingValues ->
        DiaryContent(
            selectedDate = selectedDate,
            moodJournals = moodJournals,
            mealJournals = mealJournals,
            sleepJournals = sleepJournals,
            modifier = Modifier.padding(paddingValues),
            getRewardAmount = getRewardAmount
        )
    }
}

@Composable
private fun DiaryTopAppBar(
    selectedDate: LocalDate,
    onDateSelected: (LocalDate) -> Unit = {}
) {
    LelloCalendarTopAppBar(
        selectedDate = selectedDate,
        onDateSelected = onDateSelected
    )
}

@Composable
private fun DiaryContent(
    selectedDate: LocalDate,
    moodJournals: List<MoodJournal>,
    mealJournals: List<MealJournal>,
    sleepJournals: List<SleepJournal>,
    modifier: Modifier = Modifier,
    getRewardAmount: suspend (RewardOrigin, Long) -> Int = { _, _ -> 0 }
) {
    val scrollState = rememberScrollState()

    // Agrupa e ordena todos os registros do dia, mais recentes primeiro
    val dayMoodJournals = moodJournals
        .filter { it.createdAt.isSameDay(selectedDate) }
        .sortedByDescending { it.createdAt }
    val dayMealJournals = mealJournals
        .filter { it.createdAt.isSameDay(selectedDate) }
        .sortedByDescending { it.createdAt }
    val daySleepJournals = sleepJournals
        .filter { it.createdAt.isSameDay(selectedDate) }
        .sortedByDescending { it.createdAt }

    Column(
        modifier = modifier
            .padding(Dimension.spacingRegular)
            .verticalScroll(scrollState)
    ) {
        // Sono
        if (daySleepJournals.isNotEmpty()) {
            daySleepJournals.forEach { journal ->
                val journalId = journal.id ?: 0L
                val reward by produceState(initialValue = 0, journalId) {
                    value = getRewardAmount(RewardOrigin.SLEEP_JOURNAL, journalId)
                }
                LelloDiaryCard(
                    properties = DiaryCardOptions.SleepJournal,
                    dateTime = Date(journal.createdAt),
                    reward = reward
                )
                Spacer(modifier = Modifier.padding(Dimension.paddingComponentExtraSmall))
            }
        }

        // Alimentação
        if (dayMealJournals.isNotEmpty()) {
            dayMealJournals.forEach { journal ->
                val journalId = journal.id ?: 0L
                val reward by produceState(initialValue = 0, journalId) {
                    value = getRewardAmount(RewardOrigin.MEAL_JOURNAL, journalId)
                }
                LelloDiaryCard(
                    properties = DiaryCardOptions.MealJournal,
                    dateTime = Date(journal.createdAt),
                    reward = reward
                )
                Spacer(modifier = Modifier.padding(Dimension.paddingComponentExtraSmall))
            }
        }

        // Humor
        if (dayMoodJournals.isNotEmpty()) {
            dayMoodJournals.forEach { journal ->
                val journalId = journal.id ?: 0L
                val reward by produceState(initialValue = 0, journalId) {
                    value = getRewardAmount(RewardOrigin.MOOD_JOURNAL, journalId)
                }
                LelloDiaryCard(
                    properties = when (journal.mood.name) { // ou outro critério para icone
                        "SERENE" -> DiaryCardOptions.MoodJournalSerene
                        "JOYFUL" -> DiaryCardOptions.MoodJournalJoyful
                        "BALANCED" -> DiaryCardOptions.MoodJournalBalanced
                        "TROUBLED" -> DiaryCardOptions.MoodJournalTroubled
                        "OVERWHELMED" -> DiaryCardOptions.MoodJournalOverwhelmed
                        else -> DiaryCardOptions.MoodJournalBalanced
                    },
                    dateTime = Date(journal.createdAt),
                    reward = reward
                )
                Spacer(modifier = Modifier.padding(Dimension.paddingComponentExtraSmall))
            }
        }
    }
}
