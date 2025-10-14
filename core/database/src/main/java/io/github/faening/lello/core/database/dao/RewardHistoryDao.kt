package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.github.faening.lello.core.database.model.reward.RewardHistoryEntity
import io.github.faening.lello.core.domain.repository.RewardHistoryRepository
import io.github.faening.lello.core.model.reward.RewardOrigin
import kotlinx.coroutines.flow.Flow

@Suppress("unused")
@Dao
interface RewardHistoryDao : RewardHistoryRepository<RewardHistoryEntity> {

    @Query("SELECT * FROM reward_history ORDER BY created_at DESC")
    override fun observeHistory(): Flow<List<RewardHistoryEntity>>

    @Query("SELECT * FROM reward_history ORDER BY created_at DESC")
    override suspend fun getHistory(): List<RewardHistoryEntity>

    @Query("SELECT * FROM reward_history WHERE reward_origin = :origin ORDER BY created_at DESC")
    override suspend fun getHistoryByOrigin(origin: RewardOrigin): List<RewardHistoryEntity>

    @Query("SELECT reward_amount FROM reward_history WHERE reward_origin = :origin AND origin_id = :originId LIMIT 1")
    override suspend fun getRewardAmountByOrigin(origin: RewardOrigin, originId: Long): Int?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(history: RewardHistoryEntity)

    @Query("DELETE FROM reward_history")
    override suspend fun clearHistory()
}