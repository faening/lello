package io.github.faening.lello.feature.journal.sleep

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.options.SleepSensationOptionUseCase
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
) : ViewModel() {

    private val _sleepDuration = MutableStateFlow("")
    val sleepDuration: StateFlow<String> = _sleepDuration

    private val _sleepSensationOptions = MutableStateFlow<List<SleepSensationOption>>(emptyList())
    val sleepSensationOptions: StateFlow<List<SleepSensationOption>> = _sleepSensationOptions

    init {
        viewModelScope.launch {
            sleepSensationOptionUseCase.getAll()
                .map { list -> list.filter { it.active } }
                .collect { _sleepSensationOptions.value = it }
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

    fun toggleSleepSensationSelection(description: String) {
        _sleepSensationOptions.update { list ->
            list.map {
                if (it.description == description) it.copy(selected = !it.selected) else it
            }
        }
    }


}