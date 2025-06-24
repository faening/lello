package io.github.faening.lello.core.domain.usecase.reward

import io.github.faening.lello.core.domain.repository.RewardBalanceRepository
import io.github.faening.lello.core.model.reward.RewardBalance
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RewardBalanceUseCase @Inject constructor(
    private val repository: RewardBalanceRepository<RewardBalance>
) {

    fun observeBalance(): Flow<RewardBalance> {
        return repository.observeBalance()
    }

    suspend fun getBalance(): RewardBalance? {
        return repository.getBalance()
    }

    suspend fun insertOrUpdate(balance: RewardBalance) {
        repository.insertOrUpdate(balance)
    }

    suspend fun clearBalance() {
        repository.clearBalance()
    }
}