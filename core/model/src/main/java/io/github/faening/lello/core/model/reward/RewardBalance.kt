package io.github.faening.lello.core.model.reward

data class RewardBalance(
    val id: Int = 1,
    val totalCoins: Int = 0,
    val lastMoodReward: Long? = null,
    val lastMealReward: Long? = null,
    val lastSleepReward: Long? = null,
    val lastMedicationReward: Long? = null,
    val lastDailyAchievementReward: Long? = null
)