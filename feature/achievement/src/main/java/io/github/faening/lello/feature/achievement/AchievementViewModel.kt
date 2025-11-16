package io.github.faening.lello.feature.achievement

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.mascot.GetMascotStatusUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.GetRewardBalanceUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AchievementViewModel @Inject constructor(
    private val getMascotStatusUseCase: GetMascotStatusUseCase,
    private val getRewardBalanceUseCase: GetRewardBalanceUseCase
) : ViewModel() {

    private val _vitality = MutableStateFlow(0)
    val vitality: StateFlow<Int> = _vitality.asStateFlow()

    private val _money = MutableStateFlow(0)
    val money: StateFlow<Int> = _money.asStateFlow()

    init {
        loadVitality()
        loadMoney()
    }

    private fun loadVitality() {
        viewModelScope.launch {
            _vitality.value = getMascotStatusUseCase.invoke().vitality
        }
    }

    private fun loadMoney() {
        viewModelScope.launch {
            _money.value = getRewardBalanceUseCase.invoke()?.totalCoins ?: 0
        }
    }

    fun feedMascot(amount: Int) {

    }
}