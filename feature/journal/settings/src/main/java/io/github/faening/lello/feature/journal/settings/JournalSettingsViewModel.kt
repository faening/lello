package io.github.faening.lello.feature.journal.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.options.ClimateOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.EmotionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.LocationOptionUseCase
import io.github.faening.lello.core.model.journal.ClimateOption
import io.github.faening.lello.core.model.journal.EmotionOption
import io.github.faening.lello.core.model.journal.LocationOption
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class JournalSettingsViewModel @Inject constructor(
    emotionOptionUseCase: EmotionOptionUseCase,
    climateOptionUseCase: ClimateOptionUseCase,
    locationOptionUseCase: LocationOptionUseCase,
) : ViewModel() {

    private val _emotionOptions = MutableStateFlow<List<EmotionOption>>(emptyList())
    val emotionOptions: StateFlow<List<EmotionOption>> = _emotionOptions

    private val _climateOptions = MutableStateFlow<List<ClimateOption>>(emptyList())
    val climateOptions: StateFlow<List<ClimateOption>> = _climateOptions

    private val _locationOptions = MutableStateFlow<List<LocationOption>>(emptyList())
    val locationOptions: StateFlow<List<LocationOption>> = _locationOptions

    init {
        viewModelScope.launch {
            emotionOptionUseCase.getAll().collect { _emotionOptions.value = it }
        }
        viewModelScope.launch {
            climateOptionUseCase.getAll().collect { _climateOptions.value = it }
        }
        viewModelScope.launch {
            locationOptionUseCase.getAll().collect { _locationOptions.value = it }
        }
    }

    fun toggleEmotionOption(option: EmotionOption, active: Boolean) {
        _emotionOptions.value = _emotionOptions.value.map {
            if (it.id == option.id) it.copy(active = active) else it
        }
    }

    fun toggleClimateOption(option: ClimateOption, active: Boolean) {
        _climateOptions.value = _climateOptions.value.map {
            if (it.id == option.id) it.copy(active = active) else it
        }
    }

    fun toggleLocationOption(option: LocationOption, active: Boolean) {
        _locationOptions.value = _locationOptions.value.map {
            if (it.id == option.id) it.copy(active = active) else it
        }
    }

}
