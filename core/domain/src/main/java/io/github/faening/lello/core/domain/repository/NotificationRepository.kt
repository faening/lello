package io.github.faening.lello.core.domain.repository

import io.github.faening.lello.core.model.notification.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun schedule(notification: Notification)
    suspend fun cancel(notification: Notification)
}

interface NotificationSettingsResources {
    suspend fun setJournalRewardsNotificationEnabled(enabled: Boolean)
    suspend fun getJournalRewardsNotificationEnabled(): Boolean
    fun observeJournalRewardsNotificationEnabled(): Flow<Boolean>
}