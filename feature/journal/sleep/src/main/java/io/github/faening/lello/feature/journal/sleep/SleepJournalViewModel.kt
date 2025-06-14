package io.github.faening.lello.feature.journal.sleep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.options.LocationOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepActivityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepQualityOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.SleepSensationOptionUseCase
import io.github.faening.lello.core.model.journal.LocationOption
import io.github.faening.lello.core.model.journal.SleepActivityOption
import io.github.faening.lello.core.model.journal.SleepQualityOption
import io.github.faening.lello.core.model.journal.SleepSensationOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.collections.map

@HiltViewModel
class SleepJournalViewModel @Inject constructor(
    sleepSensationOptionUseCase: SleepSensationOptionUseCase,
    sleepQualityOptionUseCase: SleepQualityOptionUseCase,
    sleepAcitivityOptionUseCase: SleepActivityOptionUseCase,
    locationOptionUseCase: LocationOptionUseCase,
) : ViewModel() {

    private val _sleepDuration = MutableStateFlow("")
    val sleepDuration: StateFlow<String> = _sleepDuration

    private val _sleepSensationOptions = MutableStateFlow<List<SleepSensationOption>>(emptyList())
    val sleepSensationOptions: StateFlow<List<SleepSensationOption>> = _sleepSensationOptions

    private val _sleepQualityOptions = MutableStateFlow<List<SleepQualityOption>>(emptyList())
    val sleepQualityOptions: StateFlow<List<SleepQualityOption>> = _sleepQualityOptions

    private val _sleepActivityOptions = MutableStateFlow<List<SleepActivityOption>>(emptyList())
    val sleepActivityOptions: StateFlow<List<SleepActivityOption>> = _sleepActivityOptions

    private val _locationOptions = MutableStateFlow<List<LocationOption>>(emptyList())
    val locationOptions: StateFlow<List<LocationOption>> = _locationOptions

    private val _sleeplessTime = MutableStateFlow("")
    val sleeplessTime: StateFlow<String> = _sleeplessTime

    init {
        viewModelScope.launch {
            sleepSensationOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _sleepSensationOptions.value = it }
        }
        viewModelScope.launch {
            sleepQualityOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _sleepQualityOptions.value = it }
        }
        viewModelScope.launch {
            sleepAcitivityOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _sleepActivityOptions.value = it }
        }
        viewModelScope.launch {
            locationOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _locationOptions.value = it }
        }
    }

    /**
     * Atualiza a duração do sono.
     *
     * @param duration The new sleep duration to set.
     */
    fun updateSleepDuration(duration: String) {
        _sleepDuration.value = duration
    }

    fun updateSleeplessTime(time: String) {
        _sleeplessTime.value = time
    }

    fun toggleSleepSensationSelection(description: String) {
        _sleepSensationOptions.update { list ->
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

    fun toggleSleepActivitySelection(description: String) {
        _sleepActivityOptions.update { list ->
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
}