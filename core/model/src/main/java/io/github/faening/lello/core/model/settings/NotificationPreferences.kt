package io.github.faening.lello.core.model.settings

data class NotificationPreferences(
    val journalRewardsEnabled: Boolean = true,
    val medicationEnabled: Boolean = true,
    val mascotEnergyEnabled: Boolean = true
)