package io.github.faening.lello.feature.diary.screen

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import io.github.faening.lello.core.designsystem.component.appbar.LelloCalendarTopAppBar
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.journal.MealJournal
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.journal.SleepJournal
import io.github.faening.lello.feature.diary.DiaryViewModel
import java.time.LocalDate

@Composable
fun DiaryScreen(
    viewModel: DiaryViewModel
) {
    val selectedDate by viewModel.selectedDate.collectAsState()
    val moodJournal by viewModel.moodJournal.collectAsState()
    val mealJournal by viewModel.mealJournal.collectAsState()
    val sleepJournal by viewModel.sleepJournal.collectAsState()

    LelloTheme {
        DiaryContainer(
            selectedDate = selectedDate,
            moodJournals = moodJournal,
            mealJournals = mealJournal,
            sleepJournals = sleepJournal,
            onSelectDate = viewModel::setSelectedDate
        )
    }
}

@Composable
private fun DiaryContainer(
    selectedDate: LocalDate,
    moodJournals: List<MoodJournal>,
    mealJournals: List<MealJournal>,
    sleepJournals: List<SleepJournal>,
    onSelectDate: (LocalDate) -> Unit = {}
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
            moodJournals = moodJournals,
            mealJournals = mealJournals,
            sleepJournals = sleepJournals,
            modifier = Modifier.padding(paddingValues)
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
    moodJournals: List<MoodJournal>,
    mealJournals: List<MealJournal>,
    sleepJournals: List<SleepJournal>,
    modifier: Modifier = Modifier
) {

}