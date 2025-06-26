package io.github.faening.lello.core.domain.usecase.reward

import io.github.faening.lello.core.domain.repository.RewardHistoryRepository
import io.github.faening.lello.core.model.reward.RewardHistory
import io.github.faening.lello.core.model.reward.RewardOrigin
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RewardHistoryUseCase @Inject constructor(
    private val repository: RewardHistoryRepository<RewardHistory>
) {

    fun observeHistory(): Flow<List<RewardHistory>> {
        return repository.observeHistory()
    }

    suspend fun getHistory(): List<RewardHistory> {
        return repository.getHistory()
    }

    suspend fun getHistoryByOrigin(origin: RewardOrigin): List<RewardHistory> {
        return repository.getHistoryByOrigin(origin)
    }

    suspend fun getRewardAmountByOrigin(origin: RewardOrigin, originId: Long): Int? {
        return repository.getRewardAmountByOrigin(origin, originId)
    }

    suspend fun insert(history: RewardHistory) {
        repository.insert(history)
    }

    suspend fun clearHistory() {
        repository.clearHistory()
    }
}