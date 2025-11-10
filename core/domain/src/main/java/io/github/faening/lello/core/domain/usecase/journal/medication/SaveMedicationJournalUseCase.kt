package io.github.faening.lello.core.domain.usecase.journal.medication

import io.github.faening.lello.core.domain.repository.MedicationJournalRepository
import io.github.faening.lello.core.domain.service.RewardCalculatorService
import io.github.faening.lello.core.domain.usecase.reward.balance.GetRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.SaveOrUpdateRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.SaveRewardHistoryUseCase
import io.github.faening.lello.core.model.journal.MedicationJournal
import io.github.faening.lello.core.model.reward.RewardBalance
import io.github.faening.lello.core.model.reward.RewardCooldown
import io.github.faening.lello.core.model.reward.RewardHistory
import io.github.faening.lello.core.model.reward.RewardOrigin
import jakarta.inject.Inject

class SaveMedicationJournalUseCase @Inject constructor(
    private val repository: MedicationJournalRepository<MedicationJournal>,
    private val rewardCalculatorService: RewardCalculatorService,
    private val saveOrUpdateRewardBalanceUseCase: SaveOrUpdateRewardBalanceUseCase,
    private val getRewardBalanceUseCase: GetRewardBalanceUseCase,
    private val saveRewardHistoryUseCase: SaveRewardHistoryUseCase
) {
    suspend operator fun invoke(journal: MedicationJournal): Long {
        val now = System.currentTimeMillis()
        val cooldown = RewardCooldown.MEDICATION_JOURNAL.millis
        val entryId = repository.insert(journal)
        val shouldReward = journal.taken || journal.skipReasonOption != null

        if (shouldReward) {
            val currentBalance = getRewardBalanceUseCase.invoke()
            val lastMedicationReward = currentBalance?.lastMedicationReward
            val canReward = lastMedicationReward == null || now - lastMedicationReward >= cooldown

            if (canReward) {
                val rewardAmount = rewardCalculatorService.calculateForMedicationJournal()
                val amountCoins = (currentBalance?.totalCoins ?: 0) + rewardAmount

                val updatedBalance = currentBalance?.copy(
                    totalCoins = amountCoins,
                    lastMedicationReward = now
                ) ?: RewardBalance(
                    totalCoins = amountCoins,
                    lastMedicationReward = now
                )
                saveOrUpdateRewardBalanceUseCase.invoke(updatedBalance)

                val history = RewardHistory(
                    originId = entryId,
                    rewardOrigin = RewardOrigin.MEDICATION_JOURNAL,
                    rewardAmount = rewardAmount,
                    createdAt = now
                )
                saveRewardHistoryUseCase.invoke(history)
            }
        }

        return entryId
    }
}