package io.github.faening.lello.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.faening.lello.core.domain.usecase.options.JournalCategoryUseCase
import io.github.faening.lello.core.domain.usecase.reward.DailyCheckInUseCase
import io.github.faening.lello.core.domain.usecase.reward.RewardBalanceUseCase
import io.github.faening.lello.core.model.journal.JournalBonusState
import io.github.faening.lello.core.model.journal.JournalCategory
import io.github.faening.lello.core.model.reward.DailyCheckInState
import io.github.faening.lello.core.model.reward.RewardBalance
import io.github.faening.lello.core.model.reward.RewardCooldown
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val journalCategoryUseCase: JournalCategoryUseCase,
    private val rewardBalanceUseCase: RewardBalanceUseCase,
    private val dailyCheckInUseCase: DailyCheckInUseCase
) : ViewModel() {

    private val _journalCategories = MutableStateFlow<List<JournalCategory>>(emptyList())
    val journalCategories: StateFlow<List<JournalCategory>> = _journalCategories.asStateFlow()

    private val _journalBonusState = MutableStateFlow(JournalBonusState(0, 0, 0, 0))
    val journalBonusState: StateFlow<JournalBonusState> = _journalBonusState.asStateFlow()

    private val _dailyCheckInState = MutableStateFlow(DailyCheckInState())
    val dailyCheckInState: StateFlow<DailyCheckInState> = _dailyCheckInState.asStateFlow()

    // Mantém o último balance para o timer usar sempre o valor mais recente
    private var lastRewardBalance: RewardBalance? = null

    init {
        // Observa os diários normalmente
        viewModelScope.launch {
            journalCategoryUseCase.getAll().collect { categories ->
                _journalCategories.value = categories.sortedBy { it.id }
            }
        }

        // Observa o reward balance do banco e atualiza o timer sempre que mudar
        viewModelScope.launch {
            rewardBalanceUseCase.observeBalance().collect { balance ->
                lastRewardBalance = balance
                _journalBonusState.value = mapToBonusState(balance)
            }
        }

        viewModelScope.launch {
            dailyCheckInUseCase.observeDailyCheckIn().collect { state ->
                _dailyCheckInState.value = state
            }
        }

        // Atualiza o timer a cada 1 minuto, mesmo que nada mude no banco
        viewModelScope.launch {
            while (true) {
                delay(60_000)
                lastRewardBalance?.let {
                    _journalBonusState.value = mapToBonusState(it)
                }
            }
        }
    }

    private fun mapToBonusState(balance: RewardBalance): JournalBonusState {
        val now = System.currentTimeMillis()
        return JournalBonusState(
            moodRemaining = (balance.lastMoodReward
                ?.plus(RewardCooldown.MOOD_JOURNAL.millis))
                ?.minus(now) ?: 0,
            mealRemaining = (balance.lastMealReward
                ?.plus(RewardCooldown.MEAL_JOURNAL.millis))
                ?.minus(now) ?: 0,
            sleepRemaining = (balance.lastSleepReward
                ?.plus(RewardCooldown.SLEEP_JOURNAL.millis))
                ?.minus(now) ?: 0,
            medicationRemaining = (balance.lastMedicationReward
                ?.plus(RewardCooldown.MEDICATION_JOURNAL.millis))
                ?.minus(now) ?: 0,
        )
    }
}