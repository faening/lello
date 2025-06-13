package io.github.faening.lello.feature.journal.mood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.journalmood.JournalMoodUseCase
import io.github.faening.lello.core.domain.usecase.options.ClimateOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.EmotionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.HealthOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.LocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SocialOptionUseCase
import io.github.faening.lello.core.model.journal.ClimateOption
import io.github.faening.lello.core.model.journal.EmotionOption
import io.github.faening.lello.core.model.journal.HealthOption
import io.github.faening.lello.core.model.journal.LocationOption
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.journal.MoodType
import io.github.faening.lello.core.model.journal.SocialOption
import io.github.faening.lello.feature.journal.mood.model.MoodJournalColorScheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class MoodJournalViewModel @Inject constructor(
    private val journalMoodUseCase: JournalMoodUseCase,
    emotionOptionUseCase: EmotionOptionUseCase,
    climateOptionUseCase: ClimateOptionUseCase,
    locationOptionUseCase: LocationOptionUseCase,
    socialOptionUseCase: SocialOptionUseCase,
    healthOptionUseCase: HealthOptionUseCase
) : ViewModel() {

    private val _currentMood = MutableStateFlow(MoodJournalColorScheme.JOYFUL)
    val currentMood: StateFlow<MoodJournalColorScheme> = _currentMood

    private val _entryDateTime = MutableStateFlow<LocalDateTime?>(null)
    val entryDateTime: StateFlow<String> = _entryDateTime
        .map { dt -> dt?.format(DateTimeFormatter.ofPattern("HH:mm")) ?: "" }
        .stateIn(viewModelScope, SharingStarted.Eagerly, "")

    private val _emotionOptions = MutableStateFlow<List<EmotionOption>>(emptyList())
    val emotionOptions: StateFlow<List<EmotionOption>> = _emotionOptions

    private val _healthOptions = MutableStateFlow<List<HealthOption>>(emptyList())
    val healthOptions: StateFlow<List<HealthOption>> = _healthOptions

    private val _climateOptions = MutableStateFlow<List<ClimateOption>>(emptyList())
    val climateOptions: StateFlow<List<ClimateOption>> = _climateOptions

    private val _locationOptions = MutableStateFlow<List<LocationOption>>(emptyList())
    val locationOptions: StateFlow<List<LocationOption>> = _locationOptions

    private val _socialOptions = MutableStateFlow<List<SocialOption>>(emptyList())
    val socialOptions: StateFlow<List<SocialOption>> = _socialOptions

    private val _reflection = MutableStateFlow("")
    val reflection: StateFlow<String> = _reflection

    private val _moodJournal = MutableStateFlow<MoodJournal?>(null)

    init {
        viewModelScope.launch {
            emotionOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _emotionOptions.value = it }
        }
        viewModelScope.launch {
            healthOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _healthOptions.value = it }
        }
        viewModelScope.launch {
            climateOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _climateOptions.value = it }
        }
        viewModelScope.launch {
            locationOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _locationOptions.value = it }
        }
        viewModelScope.launch {
            socialOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _socialOptions.value = it }
        }
    }

    /**
     * Atualiza o humor selecionado pelo usuário.
     */
    fun updateMood(mood: MoodJournalColorScheme) {
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

    fun toggleEmotionSelection(description: String) {
        _emotionOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
    }

    fun toggleClimateSelection(description: String) {
        _climateOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
    }

    fun toggleLocationSelection(description: String) {
        _locationOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
    }

    fun toggleSocialSelection(description: String) {
        _socialOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
    }

    fun toggleHealthSelection(description: String) {
        _healthOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
    }

    fun updateReflection(text: String) {
        _reflection.value = text
    }

    private fun MoodJournalColorScheme.toMoodType(): MoodType = when (this) {
        MoodJournalColorScheme.SERENE -> MoodType.SERENE
        MoodJournalColorScheme.JOYFUL -> MoodType.JOYFUL
        MoodJournalColorScheme.BALANCED -> MoodType.BALANCED
        MoodJournalColorScheme.TROUBLED -> MoodType.TROUBLED
        MoodJournalColorScheme.OVERWHELMED -> MoodType.OVERWHELMED
    }

    private fun buildMoodJournal(): MoodJournal {
        val date = Date.from(
            (_entryDateTime.value ?: LocalDateTime.now())
                .atZone(ZoneId.systemDefault())
                .toInstant()
        )
        return MoodJournal(
            date = date,
            mood = _currentMood.value.toMoodType(),
            reflection = _reflection.value.ifBlank { null },
            emotionOptions = _emotionOptions.value.filter { it.selected },
            climateOptions = _climateOptions.value.filter { it.selected },
            locationOptions = _locationOptions.value.filter { it.selected },
            socialOptions = _socialOptions.value.filter { it.selected },
            healthOptions = _healthOptions.value.filter { it.selected }
        )
    }

    fun saveJournal() {
        if (_moodJournal.value != null) return
        viewModelScope.launch {
            val journal = buildMoodJournal()
            journalMoodUseCase.save(journal)
            _moodJournal.value = journal
        }
    }
}