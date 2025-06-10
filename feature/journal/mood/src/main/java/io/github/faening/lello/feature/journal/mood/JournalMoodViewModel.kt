package io.github.faening.lello.feature.journal.mood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.options.ClimateOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.EmotionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.LocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SocialOptionUseCase
import io.github.faening.lello.core.model.journal.ClimateOption
import io.github.faening.lello.core.model.journal.EmotionOption
import io.github.faening.lello.core.model.journal.LocationOption
import io.github.faening.lello.core.model.journal.SocialOption
import io.github.faening.lello.feature.journal.mood.model.JournalMood
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class JournalMoodViewModel @Inject constructor(
    emotionOptionUseCase: EmotionOptionUseCase,
    climateOptionUseCase: ClimateOptionUseCase,
    locationOptionUseCase: LocationOptionUseCase,
    socialOptionUseCase: SocialOptionUseCase
) : ViewModel() {

    init {
        viewModelScope.launch {
            emotionOptionUseCase.getAll().collect { options ->
                _emotions.value = options
            }
        }
    }

    // region: Mood State

    private val _currentMood = MutableStateFlow(JournalMood.JOYFUL)
    val currentMood: StateFlow<JournalMood> = _currentMood

    private val _entryDateTime = MutableStateFlow<LocalDateTime?>(null)
    val entryTimeFormatted: StateFlow<String> = _entryDateTime
        .map { dt -> dt?.format(DateTimeFormatter.ofPattern("HH:mm")) ?: "" }
        .stateIn(viewModelScope, SharingStarted.Eagerly, "")

    /**
     * Atualiza o humor selecionado pelo usuário.
     */
    fun updateMood(mood: JournalMood) {
        _currentMood.value = mood
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

    // region: Options

    private val _emotions = MutableStateFlow<List<EmotionOption>>(emptyList())
    val emotions: StateFlow<List<EmotionOption>> = _emotions

    val climates: StateFlow<List<ClimateOption>> = climateOptionUseCase
        .getAll()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val locations: StateFlow<List<LocationOption>> = locationOptionUseCase
        .getAll()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val socials: StateFlow<List<SocialOption>> = socialOptionUseCase
        .getAll()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    /**
     * Alterna a seleção de uma emoção específica.
     */
    fun toggleEmotionSelection(description: String) {
        _emotions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected)
                else it
            }
        }
    }

    //endregion
}