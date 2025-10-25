package io.github.faening.lello.feature.journal.sleep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.service.RewardCalculatorService
import io.github.faening.lello.core.domain.usecase.journal.sleep.SaveSleepJournalUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepActivityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepQualityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepSensationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.location.GetAllLocationOptionUseCase
import io.github.faening.lello.core.domain.util.toEpochMillis
import io.github.faening.lello.core.model.journal.SleepDurationOption
import io.github.faening.lello.core.model.journal.SleepJournal
import io.github.faening.lello.core.model.option.LocationOption
import io.github.faening.lello.core.model.option.SleepActivityOption
import io.github.faening.lello.core.model.option.SleepQualityOption
import io.github.faening.lello.core.model.option.SleepSensationOption
import io.github.faening.lello.core.model.option.SleeplessTimeOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class SleepJournalViewModel @Inject constructor(
    private val saveSleepJournalUseCase: SaveSleepJournalUseCase,
    private val rewardCalculatorService: RewardCalculatorService,
    // Options
    private val getAllLocationOptionUseCase: GetAllLocationOptionUseCase,
    private val sleepAcitivityOptionUseCase: SleepActivityOptionUseCase,
    private val sleepQualityOptionUseCase: SleepQualityOptionUseCase,
    private val sleepSensationOptionUseCase: SleepSensationOptionUseCase,
) : ViewModel() {

    private val _currentSleepDuration = MutableStateFlow<SleepDurationOption>(SleepDurationOption.BETWEEN_6_TO_8_HOURS)
    val currentSleepDuration: StateFlow<SleepDurationOption> = _currentSleepDuration

    private val _locationOptions = MutableStateFlow<List<LocationOption>>(emptyList())
    val locationOptions: StateFlow<List<LocationOption>> = _locationOptions

    private val _sleepActivityOptions = MutableStateFlow<List<SleepActivityOption>>(emptyList())
    val sleepActivityOptions: StateFlow<List<SleepActivityOption>> = _sleepActivityOptions

    val sleepDurationOptions: List<SleepDurationOption> = SleepDurationOption.entries

    private val _sleepQualityOptions = MutableStateFlow<List<SleepQualityOption>>(emptyList())
    val sleepQualityOptions: StateFlow<List<SleepQualityOption>> = _sleepQualityOptions

    private val _sleepSensationOptions = MutableStateFlow<List<SleepSensationOption>>(emptyList())
    val sleepSensationOptions: StateFlow<List<SleepSensationOption>> = _sleepSensationOptions

    private val _sleeplessTimeOption = MutableStateFlow<SleeplessTimeOption?>(null)
    val sleeplessTimeOption: StateFlow<SleeplessTimeOption?> = _sleeplessTimeOption

    private val _sleepJournal = MutableStateFlow<SleepJournal?>(null)

    private val _coinsAcquired = MutableStateFlow<Int>(50)
    val coinsAcquired: StateFlow<Int> = _coinsAcquired

    init {
        loadLocationOptions()
        loadSleepActivityOptions()
        loadSleepQualityOptions()
        loadSleepSensationOptions()
    }

    // region: Load options

    private fun loadLocationOptions() {
        viewModelScope.launch {
            getAllLocationOptionUseCase.invoke()
                .map { list -> list.filter { it.active } }
                .collect { _locationOptions.value = it }
        }
    }

    private fun loadSleepActivityOptions() {
        viewModelScope.launch {
            sleepAcitivityOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _sleepActivityOptions.value = it }
        }
    }

    private fun loadSleepQualityOptions() {
        viewModelScope.launch {
            sleepQualityOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _sleepQualityOptions.value = it }
        }
    }

    private fun loadSleepSensationOptions() {
        viewModelScope.launch {
            sleepSensationOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _sleepSensationOptions.value = it }
        }
    }

    // endregion

    fun toggleSleepDurationSelection(option: SleepDurationOption) {
        _currentSleepDuration.value = option
    }

    fun toggleLocationSelection(description: String) {
        _locationOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
        updateCoinsAcquired()
    }

    fun toggleSleepActivitySelection(description: String) {
        _sleepActivityOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
        updateCoinsAcquired()
    }

    fun toggleSleepQualitySelection(description: String) {
        _sleepQualityOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
        updateCoinsAcquired()
    }

    fun toggleSleepSensationSelection(description: String) {
        _sleepSensationOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
        updateCoinsAcquired()
    }

    fun toggleSleeplessTimeOption(option: SleeplessTimeOption) {
        _sleeplessTimeOption.value = option
        updateCoinsAcquired()
    }

    private fun updateCoinsAcquired() {
        val sleepJournal = buildSleepJournal()
        val points = rewardCalculatorService.calculateForSleepJournal(sleepJournal)
        _coinsAcquired.value = points
    }

    fun saveSleepJournal() {
        if (_sleepJournal.value == null) return
        viewModelScope.launch {
            val journal = buildSleepJournal()
            saveSleepJournalUseCase.invoke(journal)
        }
    }

    private fun buildSleepJournal(): SleepJournal {
        val millis = LocalDateTime.now().toEpochMillis()
        return SleepJournal(
            sleepDuration = currentSleepDuration.value,
            sleeplessTime = sleeplessTimeOption.value?.minutes ?: 0,
            sleepSensationOptions = _sleepSensationOptions.value.filter { it.selected },
            sleepQualityOptions = _sleepQualityOptions.value.filter { it.selected },
            sleepActivityOptions = _sleepActivityOptions.value.filter { it.selected },
            locationOptions = _locationOptions.value.filter { it.selected },
            createdAt = millis,
        ).also {
            _sleepJournal.value = it
        }
    }
}