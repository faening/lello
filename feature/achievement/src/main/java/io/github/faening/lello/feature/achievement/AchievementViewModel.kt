package io.github.faening.lello.feature.achievement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.service.MascotVitalityManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AchievementViewModel @Inject constructor(
    private val vitalityManager: MascotVitalityManager
) : ViewModel() {

    private val _vitality = MutableStateFlow(0)
    val vitality: StateFlow<Int> = _vitality.asStateFlow()

    init {
        viewModelScope.launch {
            _vitality.value = vitalityManager.syncVitalityDecay().vitality
        }
    }

    fun feedMascot(amount: Int) {
        viewModelScope.launch {
            _vitality.value = vitalityManager.feedMascot(amount).vitality
        }
    }
}