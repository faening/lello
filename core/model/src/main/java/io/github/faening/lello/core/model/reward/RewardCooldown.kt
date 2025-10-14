package io.github.faening.lello.core.model.reward

enum class RewardCooldown(val millis: Long) {
    MOOD_JOURNAL(6 * 60 * 60 * 1000L),         // 6 horas
    MEAL_JOURNAL(8 * 60 * 60 * 1000L),         // 8 horas
    SLEEP_JOURNAL(8 * 60 * 60 * 1000L),        // 8 horas
    MEDICATION_JOURNAL(24 * 60 * 60 * 1000L),  // 24 horas
    DAILY_ACHIEVEMENT(24 * 60 * 60 * 1000L);   // 24 horas

    companion object {
        fun fromOrigin(origin: RewardOrigin): RewardCooldown = when (origin) {
            RewardOrigin.MOOD_JOURNAL        -> MOOD_JOURNAL
            RewardOrigin.MEAL_JOURNAL        -> MEAL_JOURNAL
            RewardOrigin.SLEEP_JOURNAL       -> SLEEP_JOURNAL
            RewardOrigin.MEDICATION_JOURNAL  -> MEDICATION_JOURNAL
            RewardOrigin.DAILY_ACHIEVEMENT   -> DAILY_ACHIEVEMENT
        }
    }
}