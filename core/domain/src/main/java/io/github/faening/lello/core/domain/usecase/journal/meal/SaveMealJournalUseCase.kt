package io.github.faening.lello.core.domain.usecase.journal.meal

import io.github.faening.lello.core.domain.repository.JournalRepository
import io.github.faening.lello.core.domain.service.RewardCalculatorService
import io.github.faening.lello.core.domain.usecase.reward.balance.GetRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.SaveOrUpdateRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.SaveRewardHistoryUseCase
import io.github.faening.lello.core.model.journal.MealJournal
import io.github.faening.lello.core.model.reward.RewardBalance
import io.github.faening.lello.core.model.reward.RewardCooldown
import io.github.faening.lello.core.model.reward.RewardHistory
import io.github.faening.lello.core.model.reward.RewardOrigin
import javax.inject.Inject

class SaveMealJournalUseCase @Inject constructor(
    private val repository: JournalRepository<MealJournal>,
    private val rewardCalculatorService: RewardCalculatorService,
    private val saveOrUpdateRewardBalanceUseCase: SaveOrUpdateRewardBalanceUseCase,
    private val getRewardBalanceUseCase: GetRewardBalanceUseCase,
    private val saveRewardHistoryUseCase: SaveRewardHistoryUseCase
) {
    suspend operator fun invoke(vararg entries: MealJournal) {
        val now = System.currentTimeMillis()
        val cooldown = RewardCooldown.MEAL_JOURNAL.millis

        entries.forEach { entry ->
            val entryId = repository.insert(entry)

            val currentBalance = getRewardBalanceUseCase.invoke()
            val lastMealReward = currentBalance?.lastMealReward

            val canReward = lastMealReward == null || now - lastMealReward >= cooldown

            if (canReward) {
                val rewardAmount = rewardCalculatorService.calculateForMealJournal(entry)
                val amountCoins = (currentBalance?.totalCoins ?: 0) + rewardAmount

                val updatedBalance = currentBalance?.copy(
                    totalCoins = amountCoins,
                    lastMealReward = now
                ) ?: RewardBalance(
                    totalCoins = amountCoins,
                    lastMealReward = now
                )
                saveOrUpdateRewardBalanceUseCase.invoke(updatedBalance)

                val history = RewardHistory(
                    originId = entryId,
                    rewardOrigin = RewardOrigin.MEAL_JOURNAL,
                    rewardAmount = rewardAmount,
                    createdAt = now
                )
                saveRewardHistoryUseCase.invoke(history)
            }
        }
    }
}