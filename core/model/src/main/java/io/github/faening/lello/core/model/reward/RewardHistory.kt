package io.github.faening.lello.core.model.reward

data class RewardHistory(
    val id: Long = 0L,
    val rewardOrigin: RewardOrigin,
    val rewardAmount: Int,
    val createdAt: Long
)