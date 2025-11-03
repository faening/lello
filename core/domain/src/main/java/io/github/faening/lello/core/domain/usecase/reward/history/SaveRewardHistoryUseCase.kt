package io.github.faening.lello.core.domain.usecase.reward.history

import io.github.faening.lello.core.domain.repository.RewardHistoryRepository
import io.github.faening.lello.core.model.reward.RewardHistory
import javax.inject.Inject

class SaveRewardHistoryUseCase @Inject constructor(
    private val repository: RewardHistoryRepository<RewardHistory>
) {
    suspend operator fun invoke(history: RewardHistory) {
        repository.insert(history)
    }
}