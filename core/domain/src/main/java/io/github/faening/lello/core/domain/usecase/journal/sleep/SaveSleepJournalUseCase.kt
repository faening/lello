package io.github.faening.lello.core.domain.usecase.journal.sleep

import io.github.faening.lello.core.domain.repository.JournalRepository
import io.github.faening.lello.core.domain.service.RewardCalculatorService
import io.github.faening.lello.core.domain.usecase.reward.balance.GetRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.SaveOrUpdateRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.SaveRewardHistoryUseCase
import io.github.faening.lello.core.model.journal.SleepJournal
import io.github.faening.lello.core.model.reward.RewardBalance
import io.github.faening.lello.core.model.reward.RewardCooldown
import io.github.faening.lello.core.model.reward.RewardHistory
import io.github.faening.lello.core.model.reward.RewardOrigin
import javax.inject.Inject

class SaveSleepJournalUseCase @Inject constructor(
    private val repository: JournalRepository<SleepJournal>,
    private val rewardCalculatorService: RewardCalculatorService,
    private val saveOrUpdateRewardBalanceUseCase: SaveOrUpdateRewardBalanceUseCase,
    private val getRewardBalanceUseCase: GetRewardBalanceUseCase,
    private val saveRewardHistoryUseCase: SaveRewardHistoryUseCase
) {
    suspend operator fun invoke(vararg entries: SleepJournal) {
        val now = System.currentTimeMillis()
        val cooldown = RewardCooldown.SLEEP_JOURNAL.millis

        entries.forEach { entry ->
            val entryId = repository.insert(entry)

            val currentBalance = getRewardBalanceUseCase.invoke()
            val lastSleepReward = currentBalance?.lastSleepReward

            val canReward = lastSleepReward == null || now - lastSleepReward >= cooldown

            if (canReward) {
                val rewardAmount = rewardCalculatorService.calculateForSleepJournal(entry)
                val amountCoins = (currentBalance?.totalCoins ?: 0) + rewardAmount

                val updatedBalance = currentBalance?.copy(
                    totalCoins = amountCoins,
                    lastSleepReward = now
                ) ?: RewardBalance(
                    totalCoins = amountCoins,
                    lastSleepReward = now
                )
                saveOrUpdateRewardBalanceUseCase.invoke(updatedBalance)

                val history = RewardHistory(
                    originId = entryId,
                    rewardOrigin = RewardOrigin.SLEEP_JOURNAL,
                    rewardAmount = rewardAmount,
                    createdAt = now
                )
                saveRewardHistoryUseCase.invoke(history)
            }
        }
    }
}