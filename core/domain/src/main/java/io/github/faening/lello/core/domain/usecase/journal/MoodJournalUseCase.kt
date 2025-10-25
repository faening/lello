package io.github.faening.lello.core.domain.usecase.journal

import io.github.faening.lello.core.domain.repository.JournalRepository
import io.github.faening.lello.core.domain.service.RewardCalculatorService
import io.github.faening.lello.core.domain.usecase.reward.RewardHistoryUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.GetRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.SaveOrUpdateRewardBalanceUseCase
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.reward.RewardBalance
import io.github.faening.lello.core.model.reward.RewardCooldown
import io.github.faening.lello.core.model.reward.RewardHistory
import io.github.faening.lello.core.model.reward.RewardOrigin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoodJournalUseCase @Inject constructor(
    private val repository: JournalRepository<MoodJournal>,
    private val rewardCalculatorService: RewardCalculatorService,
    private val saveOrUpdateRewardBalanceUseCase: SaveOrUpdateRewardBalanceUseCase,
    private val getRewardBalanceUseCase: GetRewardBalanceUseCase,
    private val rewardHistoryUseCase: RewardHistoryUseCase
) {
    fun getAll(): Flow<List<MoodJournal>> {
        return repository.getAll()
    }

    fun getById(id: Long): Flow<MoodJournal>? {
        return repository.getById(id)
    }

    suspend fun save(vararg entries: MoodJournal) {
        val now = System.currentTimeMillis()
        val cooldown = RewardCooldown.MOOD_JOURNAL.millis

        entries.forEach { entry ->
            val entryId = repository.insert(entry)

            // Buscar balance atual
            val currentBalance = getRewardBalanceUseCase.invoke()
            val lastMoodReward = currentBalance?.lastMoodReward

            // Checar se o cooldown permite uma nova recompensa
            val canReward = lastMoodReward == null || now - lastMoodReward >= cooldown

            if (canReward) {
                // Calcular a recompensa do diário
                val rewardAmount = rewardCalculatorService.calculateForMoodJournal(entry)
                val amountCoins = (currentBalance?.totalCoins ?: 0) + rewardAmount

                // Atualizar balance com o novo saldo e novo lastMoodReward
                val updatedBalance = currentBalance?.copy(
                    totalCoins = amountCoins,
                    lastMoodReward = now
                ) ?: RewardBalance(
                    totalCoins = amountCoins,
                    lastMoodReward = now
                )
                saveOrUpdateRewardBalanceUseCase.invoke(updatedBalance)

                // Registrar histórico
                val history = RewardHistory(
                    originId = entryId,
                    rewardOrigin = RewardOrigin.MOOD_JOURNAL,
                    rewardAmount = rewardAmount,
                    createdAt = now
                )
                rewardHistoryUseCase.insert(history)
            }
        }
    }


    suspend fun delete(vararg entries: MoodJournal) {
        entries.forEach { repository.delete(it) }
    }
}
