package io.github.faening.lello.core.model.journal

data class JournalBonusState(
    val moodRemaining: Long,
    val mealRemaining: Long,
    val sleepRemaining: Long,
    val medicationRemaining: Long
)