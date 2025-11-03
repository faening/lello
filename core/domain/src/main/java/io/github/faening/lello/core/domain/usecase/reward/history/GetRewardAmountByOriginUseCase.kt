package io.github.faening.lello.core.domain.usecase.reward.history

import io.github.faening.lello.core.domain.repository.RewardHistoryRepository
import io.github.faening.lello.core.model.reward.RewardHistory
import io.github.faening.lello.core.model.reward.RewardOrigin
import javax.inject.Inject

class GetRewardAmountByOriginUseCase @Inject constructor(
    private val repository: RewardHistoryRepository<RewardHistory>
) {
    suspend operator fun invoke(origin: RewardOrigin, originId: Long): Int? {
        return repository.getRewardAmountByOrigin(origin, originId)
    }
}