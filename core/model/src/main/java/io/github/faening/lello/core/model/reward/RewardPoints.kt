package io.github.faening.lello.core.model.reward

enum class RewardPoints(
    val basePoints: Int,
    val extraPoints: Int
) {
    MOOD_JOURNAL(basePoints = 50, extraPoints = 5),
    MEAL_JOURNAL(basePoints = 50, extraPoints = 5),
    SLEEP_JOURNAL(basePoints = 50, extraPoints = 5),
    MEDICATION_JOURNAL(basePoints = 50, extraPoints = 5),
    DAILY_ACHIEVEMENTS(basePoints = 10, extraPoints = 0)
}