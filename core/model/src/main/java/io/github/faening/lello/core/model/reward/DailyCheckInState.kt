package io.github.faening.lello.core.model.reward

/**
 * Represents the current progress of the daily check-in.
 * @param currentStep Number of different journals filled today (0..4).
 * @param bonusReceived True when the daily achievement reward has been granted for today.
 */
data class DailyCheckInState(
    val currentStep: Int = 0,
    val bonusReceived: Boolean = false
)
