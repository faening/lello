package io.github.faening.lello.feature.diary

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.journal.MealJournalUseCase
import io.github.faening.lello.core.domain.usecase.journal.MoodJournalUseCase
import io.github.faening.lello.core.domain.usecase.journal.SleepJournalUseCase
import io.github.faening.lello.core.domain.usecase.reward.RewardHistoryUseCase
import io.github.faening.lello.core.model.journal.MealJournal
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.journal.SleepJournal
import io.github.faening.lello.core.model.reward.RewardOrigin
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
    private val sleepJournalUseCase: SleepJournalUseCase,
    private val rewardHistoryUseCase: RewardHistoryUseCase
) : ViewModel() {

    private val _moodJournals = MutableStateFlow<List<MoodJournal>>(emptyList())
    val moodJournals: StateFlow<List<MoodJournal>> = _moodJournals

    private val _mealJournals = MutableStateFlow<List<MealJournal>>(emptyList())
    val mealJournals: StateFlow<List<MealJournal>> = _mealJournals

    private val _sleepJournals = MutableStateFlow<List<SleepJournal>>(emptyList())
    val sleepJournals: StateFlow<List<SleepJournal>> = _sleepJournals

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()

    private val _selectedMoodJournal = MutableStateFlow<MoodJournal?>(null)
    val selectedMoodJournal: StateFlow<MoodJournal?> = _selectedMoodJournal.asStateFlow()

    init {
        loadMoodJournals()
        loadMealJournal()
        loadSleepJournal()
    }

    private fun loadMoodJournals() {
        viewModelScope.launch {
            moodJournalUseCase
                .getAll()
                .collect { _moodJournals.value = it }
        }
    }

    private fun loadMealJournal() {
        viewModelScope.launch {
            mealJournalUseCase
                .getAll()
                .collect { _mealJournals.value = it }
        }
    }

    private fun loadSleepJournal() {
        viewModelScope.launch {
            sleepJournalUseCase
                .getAll()
                .collect { _sleepJournals.value = it }
        }
    }

    fun setSelectedDate(date: LocalDate) {
        _selectedDate.value = date
    }

    fun setSelectedMoodJournal(journalId: Long) {
        viewModelScope.launch {
            _selectedMoodJournal.value = _moodJournals.value.find { it.id == journalId }
        }
    }

    suspend fun getRewardAmount(origin: RewardOrigin, originId: Long): Int {
        return rewardHistoryUseCase.getRewardAmountByOrigin(origin, originId) ?: 0
    }
}