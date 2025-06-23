package io.github.faening.lello.feature.journal.sleep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.journal.SleepJournalUseCase
import io.github.faening.lello.core.domain.usecase.options.LocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepActivityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepDurationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepQualityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepSensationOptionUseCase
import io.github.faening.lello.core.domain.util.toEpochMillis
import io.github.faening.lello.core.model.journal.SleepJournal
import io.github.faening.lello.core.model.option.LocationOption
import io.github.faening.lello.core.model.option.SleepActivityOption
import io.github.faening.lello.core.model.option.SleepDurationOption
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
    private val sleepDurationOptionUseCase: SleepDurationOptionUseCase,
    private val locationOptionUseCase: LocationOptionUseCase
) : ViewModel() {

    private val _currentSleepDuration = MutableStateFlow<SleepDurationOption?>(null)

    private val _locationOptions = MutableStateFlow<List<LocationOption>>(emptyList())
    val locationOptions: StateFlow<List<LocationOption>> = _locationOptions

    private val _sleepActivityOptions = MutableStateFlow<List<SleepActivityOption>>(emptyList())
    val sleepActivityOptions: StateFlow<List<SleepActivityOption>> = _sleepActivityOptions

    private val _sleepDurationOptions = MutableStateFlow<List<SleepDurationOption>>(emptyList())
    val sleepDurationOptions: StateFlow<List<SleepDurationOption>> = _sleepDurationOptions

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
        loadSleepDurationOptions()
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

    private fun loadSleepDurationOptions() {
        viewModelScope.launch {
            sleepDurationOptionUseCase.getAll()
                .map { list ->
                    val activeList = list
                        .filter { it.active }
                        .sortedByDescending { it.id }
                    activeList.mapIndexed { idx, opt -> opt.copy(selected = idx == 2) }
                }
                .collect { options ->
                    _sleepDurationOptions.value = options
                    _currentSleepDuration.value = options.firstOrNull { it.selected }
                }
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

    fun toggleSleepDurationSelection(option: SleepDurationOption) {
        _sleepDurationOptions.update { list ->
            list.map { it.copy(selected = it.id == option.id) }
        }
        _currentSleepDuration.value = option
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
            sleepDuration = _sleepDurationOptions.value.firstOrNull { it.selected },
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