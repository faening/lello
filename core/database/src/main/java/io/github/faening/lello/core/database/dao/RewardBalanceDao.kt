package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.faening.lello.core.database.model.reward.RewardBalanceEntity
import io.github.faening.lello.core.domain.repository.RewardBalanceRepository
import kotlinx.coroutines.flow.Flow

@Suppress("unused")
@Dao
interface RewardBalanceDao : RewardBalanceRepository<RewardBalanceEntity> {

    @Query("SELECT * FROM reward_balance WHERE id = 1")
    override fun observeBalance(): Flow<RewardBalanceEntity>

    @Query("SELECT * FROM reward_balance WHERE id = 1")
    override suspend fun getBalance(): RewardBalanceEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertOrUpdate(balance: RewardBalanceEntity)

    @Query("DELETE FROM reward_balance")
    override suspend fun clearBalance()
}