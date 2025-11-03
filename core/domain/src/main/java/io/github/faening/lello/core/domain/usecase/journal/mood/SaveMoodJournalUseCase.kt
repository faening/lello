package io.github.faening.lello.core.domain.usecase.journal.mood

import io.github.faening.lello.core.domain.repository.JournalRepository
import io.github.faening.lello.core.domain.service.RewardCalculatorService
import io.github.faening.lello.core.domain.usecase.reward.balance.GetRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.SaveOrUpdateRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.SaveRewardHistoryUseCase
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.reward.RewardBalance
import io.github.faening.lello.core.model.reward.RewardCooldown
import io.github.faening.lello.core.model.reward.RewardHistory
import io.github.faening.lello.core.model.reward.RewardOrigin
import javax.inject.Inject

class SaveMoodJournalUseCase @Inject constructor(
    private val repository: JournalRepository<MoodJournal>,
    private val rewardCalculatorService: RewardCalculatorService,
    private val saveOrUpdateRewardBalanceUseCase: SaveOrUpdateRewardBalanceUseCase,
    private val getRewardBalanceUseCase: GetRewardBalanceUseCase,
    private val saveRewardHistoryUseCase: SaveRewardHistoryUseCase
) {
    suspend operator fun invoke(vararg entries: MoodJournal) {
        val now = System.currentTimeMillis()
        val cooldown = RewardCooldown.MOOD_JOURNAL.millis

        entries.forEach { entry ->
            val entryId = repository.insert(entry)

            val currentBalance = getRewardBalanceUseCase.invoke()
            val lastMoodReward = currentBalance?.lastMoodReward

            val canReward = lastMoodReward == null || now - lastMoodReward >= cooldown

            if (canReward) {
                val rewardAmount = rewardCalculatorService.calculateForMoodJournal(entry)
                val amountCoins = (currentBalance?.totalCoins ?: 0) + rewardAmount

                val updatedBalance = currentBalance?.copy(
                    totalCoins = amountCoins,
                    lastMoodReward = now
                ) ?: RewardBalance(
                    totalCoins = amountCoins,
                    lastMoodReward = now
                )
                saveOrUpdateRewardBalanceUseCase.invoke(updatedBalance)

                val history = RewardHistory(
                    originId = entryId,
                    rewardOrigin = RewardOrigin.MOOD_JOURNAL,
                    rewardAmount = rewardAmount,
                    createdAt = now
                )
                saveRewardHistoryUseCase.invoke(history)
            }
        }
    }
}