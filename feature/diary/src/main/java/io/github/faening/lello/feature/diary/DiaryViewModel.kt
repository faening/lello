package io.github.faening.lello.feature.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.journal.MealJournalUseCase
import io.github.faening.lello.core.domain.usecase.journal.MoodJournalUseCase
import io.github.faening.lello.core.domain.usecase.journal.SleepJournalUseCase
import io.github.faening.lello.core.model.journal.MealJournal
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.journal.SleepJournal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val moodJournalUseCase: MoodJournalUseCase,
    private val mealJournalUseCase: MealJournalUseCase,
    private val sleepJournalUseCase: SleepJournalUseCase
) : ViewModel() {

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()

    private val _moodJournal = MutableStateFlow<List<MoodJournal>>(emptyList())
    val moodJournal: StateFlow<List<MoodJournal>> = _moodJournal

    private val _mealJournal = MutableStateFlow<List<MealJournal>>(emptyList())
    val mealJournal: StateFlow<List<MealJournal>> = _mealJournal

    private val _sleepJournal = MutableStateFlow<List<SleepJournal>>(emptyList())
    val sleepJournal: StateFlow<List<SleepJournal>> = _sleepJournal

    init {
        loadMoodJournals()
        loadMealJournal()
        loadSleepJournal()
    }

    fun setSelectedDate(date: LocalDate) {
        _selectedDate.value = date
    }

    private fun loadMoodJournals() {
        viewModelScope.launch {
            moodJournalUseCase
                .getAll()
//                .collect { _moodJournal.value = it }
        }
    }

    private fun loadMealJournal() {
        viewModelScope.launch {
            mealJournalUseCase.getAll()
//                .collect { _mealJournal.value = it.firstOrNull() }
        }
    }

    private fun loadSleepJournal() {
        viewModelScope.launch {
            sleepJournalUseCase.getAll()
//                .collect { _sleepJournal.value = it.firstOrNull() }
        }
    }



}