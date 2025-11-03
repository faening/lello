package io.github.faening.lello.core.domain.usecase.reward.balance

import io.github.faening.lello.core.domain.repository.RewardBalanceRepository
import io.github.faening.lello.core.model.reward.RewardBalance
import javax.inject.Inject

class GetRewardBalanceUseCase @Inject constructor(
    private val repository: RewardBalanceRepository<RewardBalance>
) {
    suspend operator fun invoke(): RewardBalance? {
        return repository.getBalance()
    }
}