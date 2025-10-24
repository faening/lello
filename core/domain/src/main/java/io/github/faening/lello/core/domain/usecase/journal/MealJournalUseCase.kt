package io.github.faening.lello.core.domain.usecase.journal

import io.github.faening.lello.core.domain.repository.JournalRepository
import io.github.faening.lello.core.domain.service.RewardCalculatorService
import io.github.faening.lello.core.domain.usecase.reward.RewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.RewardHistoryUseCase
import io.github.faening.lello.core.model.journal.MealJournal
import io.github.faening.lello.core.model.reward.RewardBalance
import io.github.faening.lello.core.model.reward.RewardCooldown
import io.github.faening.lello.core.model.reward.RewardHistory
import io.github.faening.lello.core.model.reward.RewardOrigin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MealJournalUseCase @Inject constructor(
    private val repository: JournalRepository<MealJournal>,
    private val rewardCalculatorService: RewardCalculatorService,
    private val rewardBalanceUseCase: RewardBalanceUseCase,
    private val rewardHistoryUseCase: RewardHistoryUseCase
) {
    fun getAll(): Flow<List<MealJournal>> {
        return repository.getAll()
    }

    fun getById(id: Long): Flow<MealJournal>? {
        return repository.getById(id)
    }

    suspend fun save(vararg entries: MealJournal) {
        val now = System.currentTimeMillis()
        val cooldown = RewardCooldown.MEAL_JOURNAL.millis

        entries.forEach { entry ->
            val entryId = repository.insert(entry)

            // Buscar balance atual
            val currentBalance = rewardBalanceUseCase.getBalance()
            val lastMealReward = currentBalance?.lastMealReward

            // Checar se o cooldown permite uma nova recompensa
            val canReward = lastMealReward == null || now - lastMealReward >= cooldown

            if (canReward) {
                // Calcular a recompensa do diário
                val rewardAmount = rewardCalculatorService.calculateForMealJournal(entry)
                val amountCoins = (currentBalance?.totalCoins ?: 0) + rewardAmount

                // Atualizar balance com o novo saldo e novo lastMealReward
                val updatedBalance = currentBalance?.copy(
                    totalCoins = amountCoins,
                    lastMealReward = now
                ) ?: RewardBalance(
                    totalCoins = amountCoins,
                    lastMealReward = now
                )
                rewardBalanceUseCase.insertOrUpdate(updatedBalance)

                // Registrar histórico
                val history = RewardHistory(
                    originId = entryId,
                    rewardOrigin = RewardOrigin.MEAL_JOURNAL,
                    rewardAmount = rewardAmount,
                    createdAt = now
                )
                rewardHistoryUseCase.insert(history)
            }
        }
    }

    suspend fun delete(vararg entries: MealJournal) {
        entries.forEach { repository.delete(it) }
    }
}
