package io.github.faening.lello.core.domain.usecase.reward.history

import io.github.faening.lello.core.domain.repository.RewardHistoryRepository
import io.github.faening.lello.core.model.reward.RewardHistory
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveRewardHistoryUseCase @Inject constructor(
    private val repository: RewardHistoryRepository<RewardHistory>
) {
    operator fun invoke(): Flow<List<RewardHistory>> {
        return repository.observeHistory()
    }
}