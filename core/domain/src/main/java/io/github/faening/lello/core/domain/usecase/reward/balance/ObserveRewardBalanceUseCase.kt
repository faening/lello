package io.github.faening.lello.core.domain.usecase.reward.balance

import io.github.faening.lello.core.domain.repository.RewardBalanceRepository
import io.github.faening.lello.core.model.reward.RewardBalance
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveRewardBalanceUseCase @Inject constructor(
    private val repository: RewardBalanceRepository<RewardBalance>
) {
    operator fun invoke(): Flow<RewardBalance> {
        return repository.observeBalance()
    }
}