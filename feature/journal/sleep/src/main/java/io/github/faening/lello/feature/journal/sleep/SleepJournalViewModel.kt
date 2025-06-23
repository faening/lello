package io.github.faening.lello.feature.journal.sleep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.journal.SleepJournalUseCase
import io.github.faening.lello.core.domain.usecase.options.LocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepActivityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepQualityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepSensationOptionUseCase
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
    private val sleepJournalUseCase: SleepJournalUseCase,
    private val sleepSensationOptionUseCase: SleepSensationOptionUseCase,
    private val sleepQualityOptionUseCase: SleepQualityOptionUseCase,
    private val sleepAcitivityOptionUseCase: SleepActivityOptionUseCase,
    private val locationOptionUseCase: LocationOptionUseCase
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

    init {
        loadLocationOptions()
        loadSleepActivityOptions()
        loadSleepQualityOptions()
        loadSleepSensationOptions()
    }

    // region: Load options

    private fun loadLocationOptions() {
        viewModelScope.launch {
            locationOptionUseCase.getAll()
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
    }

    fun toggleSleepActivitySelection(description: String) {
        _sleepActivityOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
    }

    fun toggleSleepQualitySelection(description: String) {
        _sleepQualityOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
    }

    fun toggleSleepSensationSelection(description: String) {
        _sleepSensationOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
    }

    fun toggleSleeplessTimeOption(option: SleeplessTimeOption) {
        _sleeplessTimeOption.value = option
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

    fun saveSleepJournal() {
        if (_sleepJournal.value != null) return
        viewModelScope.launch {
            val journal = buildSleepJournal()
            sleepJournalUseCase.save(journal)
        }
    }
}