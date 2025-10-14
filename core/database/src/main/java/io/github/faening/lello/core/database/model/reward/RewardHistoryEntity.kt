package io.github.faening.lello.core.database.model.reward

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.reward.RewardHistory
import io.github.faening.lello.core.model.reward.RewardOrigin

@Entity(tableName = "reward_history")
data class RewardHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,

    @ColumnInfo(name = "origin_id")
    val originId: Long? = null,

    @ColumnInfo(name = "reward_origin")
    val rewardOrigin: RewardOrigin,

    @ColumnInfo(name = "reward_amount")
    val rewardAmount: Int,

    @ColumnInfo(name = "created_at")
    val createdAt: Long
)

fun RewardHistoryEntity.toModel() = RewardHistory(
    id = id,
    originId = originId,
    rewardOrigin = rewardOrigin,
    rewardAmount = rewardAmount,
    createdAt = createdAt
)

fun RewardHistory.toEntity() = RewardHistoryEntity(
    id = id,
    originId = originId,
    rewardOrigin = rewardOrigin,
    rewardAmount = rewardAmount,
    createdAt = createdAt
)