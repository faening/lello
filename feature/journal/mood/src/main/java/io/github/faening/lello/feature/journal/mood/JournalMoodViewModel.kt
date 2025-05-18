package io.github.faening.lello.feature.journal.mood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.feature.journal.mood.model.JournalMood
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class JournalMoodViewModel @Inject constructor() : ViewModel() {

    private val _selectedMood = MutableStateFlow(JournalMood.JOYFUL)
    val selectedMood: StateFlow<JournalMood> = _selectedMood

    private val _entryDateTime = MutableStateFlow<LocalDateTime?>(null)
    val entryDateTime: StateFlow<LocalDateTime?> = _entryDateTime

    // Formata a hora para exibição no AppBar
    val entryDateTimeFormatted: StateFlow<String> = entryDateTime.map { dt ->
        dt?.format(DateTimeFormatter.ofPattern("HH:mm")) ?: ""
    }.stateIn(viewModelScope, SharingStarted.Eagerly, "")

    fun updateMood(mood: JournalMood) {
        _selectedMood.value = mood
    }

    /**
     * Define o valor de `entryDateTime` para a data e hora atuais somente se ainda não tiver sido definido.
     */
    fun captureEntryDateTime() {
        if (_entryDateTime.value == null) {
            _entryDateTime.value = LocalDateTime.now()
        }
    }
}