package io.github.faening.lello.feature.journal.mood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.options.EmotionOptionUseCase
import io.github.faening.lello.core.model.journal.EmotionOption
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
class JournalMoodViewModel @Inject constructor(
    emotionOptionUseCase: EmotionOptionUseCase
) : ViewModel() {

    // region: Gerenciamento de estado

    private val _selectedMood = MutableStateFlow(JournalMood.JOYFUL)
    val selectedMood: StateFlow<JournalMood> = _selectedMood

    private val _entryDateTime = MutableStateFlow<LocalDateTime?>(null)
    val entryTimeFormatted: StateFlow<String> = _entryDateTime
        .map { dt -> dt?.format(DateTimeFormatter.ofPattern("HH:mm")) ?: "" }
        .stateIn(viewModelScope, SharingStarted.Eagerly, "")

    /**
     * Atualiza o humor selecionado pelo usuário.
     */
    fun updateMood(mood: JournalMood) {
        _selectedMood.value = mood
    }

    /**
     * Captura o horário de início do diário apenas se ainda não tiver sido definido.
     * Essa função deve ser chamada ao abrir o fluxo de diário de humor.
     */
    fun captureEntryDateTime() {
        if (_entryDateTime.value == null) {
            _entryDateTime.value = LocalDateTime.now()
        }
    }

    // endregion

    // region: Carregamento de opções

    val emotionOptions: StateFlow<List<EmotionOption>> = emotionOptionUseCase
        .getAll()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    // endregion
}