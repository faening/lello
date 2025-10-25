package io.github.faening.lello.core.domain.usecase.reward.history

import io.github.faening.lello.core.domain.repository.RewardHistoryRepository
import io.github.faening.lello.core.model.reward.RewardHistory
import javax.inject.Inject

class GetRewardHistoryUseCase @Inject constructor(
    private val repository: RewardHistoryRepository<RewardHistory>
) {
    suspend operator fun invoke(): List<RewardHistory> {
        return repository.getHistory()
    }
}