package io.github.faening.lello.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.options.JournalCategoryUseCase
import io.github.faening.lello.core.model.journal.JournalCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val journalCategoryUseCase: JournalCategoryUseCase
) : ViewModel() {

    private val _journalCategories = MutableStateFlow<List<JournalCategory>>(emptyList())
    val journalCategories: StateFlow<List<JournalCategory>> = _journalCategories.asStateFlow()

    private val _selectedDate = MutableStateFlow(LocalDate.now())
    val selectedDate: StateFlow<LocalDate> = _selectedDate.asStateFlow()

    init {
        viewModelScope.launch {
            journalCategoryUseCase.getAll().collect { categories ->
                _journalCategories.value = categories
            }
        }
    }

    fun setSelectedDate(date: LocalDate) {
        _selectedDate.value = date
    }
}
