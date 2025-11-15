package io.github.faening.lello.core.domain.repository

import io.github.faening.lello.core.model.notification.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun schedule(notification: Notification)
    suspend fun cancel(notification: Notification)
}

interface NotificationSettingsResources {
    suspend fun getJournalRewardsNotificationEnabled(): Boolean
    suspend fun setJournalRewardsNotificationEnabled(enabled: Boolean)
    fun observeJournalRewardsNotificationEnabled(): Flow<Boolean>

    suspend fun getMedicationNotificationEnabled(): Boolean
    suspend fun setMedicationNotificationEnabled(enabled: Boolean)
    fun observeMedicationNotificationEnabled(): Flow<Boolean>

    suspend fun getMascotEnergyNotificationEnabled(): Boolean
    suspend fun setMascotEnergyNotificationEnabled(enabled: Boolean)
    fun observeMascotEnergyNotificationEnabled(): Flow<Boolean>
}