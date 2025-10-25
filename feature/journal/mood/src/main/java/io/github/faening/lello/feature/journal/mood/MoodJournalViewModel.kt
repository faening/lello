package io.github.faening.lello.feature.journal.mood

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.core.domain.service.RewardCalculatorService
import io.github.faening.lello.core.domain.usecase.journal.mood.SaveMoodJournalUseCase
import io.github.faening.lello.core.domain.usecase.options.emotion.GetAllEmotionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.HealthOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.LocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SocialOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.climate.GetAllClimateOptionUseCase
import io.github.faening.lello.core.domain.util.toEpochMillis
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.journal.MoodType
import io.github.faening.lello.core.model.option.ClimateOption
import io.github.faening.lello.core.model.option.EmotionOption
import io.github.faening.lello.core.model.option.HealthOption
import io.github.faening.lello.core.model.option.LocationOption
import io.github.faening.lello.core.model.option.SocialOption
import io.github.faening.lello.feature.journal.mood.model.MoodColorMapping
import io.github.faening.lello.feature.journal.mood.model.MoodJournalColorScheme
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
class MoodJournalViewModel @Inject constructor(
    private val saveMoodJournalUseCase: SaveMoodJournalUseCase,
    getAllEmotionOptionUseCase: GetAllEmotionOptionUseCase,
    private val getAllClimateOptionUseCase: GetAllClimateOptionUseCase,
    locationOptionUseCase: LocationOptionUseCase,
    socialOptionUseCase: SocialOptionUseCase,
    healthOptionUseCase: HealthOptionUseCase,
    private val rewardCalculatorService: RewardCalculatorService
) : ViewModel() {

    private val _currentMood = MutableStateFlow(MoodColor.DEFAULT)
    val currentMood: StateFlow<MoodColor> = _currentMood

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

    private val _coinsAcquired = MutableStateFlow<Int>(50)
    val coinsAcquired: StateFlow<Int> = _coinsAcquired

    init {
        viewModelScope.launch {
            getAllEmotionOptionUseCase.invoke()
                .map { list -> list.filter { it.active } }
                .collect { _emotionOptions.value = it }
        }
        viewModelScope.launch {
            healthOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _healthOptions.value = it }
        }
        viewModelScope.launch {
            getAllClimateOptionUseCase.invoke()
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
    fun updateMood(moodColor: MoodColor) {
        _currentMood.value = moodColor
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
        updateCoinsAcquired()
    }

    fun toggleClimateSelection(description: String) {
        _climateOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
        updateCoinsAcquired()
    }

    fun toggleLocationSelection(description: String) {
        _locationOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
        updateCoinsAcquired()
    }

    fun toggleSocialSelection(description: String) {
        _socialOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
        updateCoinsAcquired()
    }

    fun toggleHealthSelection(description: String) {
        _healthOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
        updateCoinsAcquired()
    }

    fun updateReflection(text: String) {
        _reflection.value = text
        updateCoinsAcquired()
    }

    private fun updateCoinsAcquired() {
        val moodJournal = buildMoodJournal()
        val points = rewardCalculatorService.calculateForMoodJournal(moodJournal)
        _coinsAcquired.value = points
    }

    fun saveMoodJournal() {
        // if (_moodJournal.value == null) return
        viewModelScope.launch {
            val journal = buildMoodJournal()
            saveMoodJournalUseCase.invoke(journal)
            _moodJournal.value = journal
        }
    }

    private fun buildMoodJournal(): MoodJournal {
        val millis = (_entryDateTime.value ?: LocalDateTime.now()).toEpochMillis()
        return MoodJournal(
            mood = getMoodTypeFromMoodColor(currentMood.value),
            reflection = reflection.value,
            emotionOptions = emotionOptions.value.filter { it.selected },
            climateOptions = climateOptions.value.filter { it.selected },
            locationOptions = locationOptions.value.filter { it.selected },
            socialOptions = socialOptions.value.filter { it.selected },
            healthOptions = healthOptions.value.filter { it.selected },
            createdAt = millis,
        )
    }

    private fun getMoodTypeFromMoodColor(moodColor: MoodColor): MoodType {
        return MoodColorMapping.moodMap[moodColor]?.moodType ?: MoodType.JOYFUL
    }

    private fun MoodJournalColorScheme.toMoodType(): MoodType = when (this) {
        MoodJournalColorScheme.SERENE -> MoodType.SERENE
        MoodJournalColorScheme.JOYFUL -> MoodType.JOYFUL
        MoodJournalColorScheme.BALANCED -> MoodType.BALANCED
        MoodJournalColorScheme.TROUBLED -> MoodType.TROUBLED
        MoodJournalColorScheme.OVERWHELMED -> MoodType.OVERWHELMED
    }
}