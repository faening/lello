package io.github.faening.lello.core.database.model.reward

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.reward.RewardBalance

@Entity(tableName = "reward_balance")
data class RewardBalanceEntity(
    @PrimaryKey
    val id: Int = 1, // sempre 1, pois é único no app single-user

    @ColumnInfo(name = "total_coins")
    val totalCoins: Int = 0,

    @ColumnInfo(name = "last_mood_reward")
    val lastMoodReward: Long? = null,

    @ColumnInfo(name = "last_meal_reward")
    val lastMealReward: Long? = null,

    @ColumnInfo(name = "last_sleep_reward")
    val lastSleepReward: Long? = null,

    @ColumnInfo(name = "last_medication_reward")
    val lastMedicationReward: Long? = null,

    @ColumnInfo(name = "last_daily_achievement_reward")
    val lastDailyAchievementReward: Long? = null
)

fun RewardBalanceEntity.toModel() = RewardBalance(
    id = id,
    totalCoins = totalCoins,
    lastMoodReward = lastMoodReward,
    lastMealReward = lastMealReward,
    lastSleepReward = lastSleepReward,
    lastMedicationReward = lastMedicationReward,
    lastDailyAchievementReward = lastDailyAchievementReward
)

fun RewardBalance.toEntity() = RewardBalanceEntity(
    id = id,
    totalCoins = totalCoins,
    lastMoodReward = lastMoodReward,
    lastMealReward = lastMealReward,
    lastSleepReward = lastSleepReward,
    lastMedicationReward = lastMedicationReward,
    lastDailyAchievementReward = lastDailyAchievementReward
)