package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.RewardBalanceDao
import io.github.faening.lello.core.database.model.reward.toEntity
import io.github.faening.lello.core.database.model.reward.toModel
import io.github.faening.lello.core.domain.repository.RewardBalanceRepository
import io.github.faening.lello.core.model.reward.RewardBalance
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataRewardBalanceRepository @Inject constructor(
    private val dao: RewardBalanceDao
) : RewardBalanceRepository<RewardBalance> {

    override fun observeBalance(): Flow<RewardBalance> {
        return dao
            .observeBalance()
            .map { it.toModel() }
    }

    override suspend fun getBalance(): RewardBalance? {
        return dao.getBalance()?.toModel()
    }

    override suspend fun insertOrUpdate(balance: RewardBalance) {
        val entity = balance.toEntity()
        dao.insertOrUpdate(entity)
    }

    override suspend fun clearBalance() {
        dao.clearBalance()
    }
}