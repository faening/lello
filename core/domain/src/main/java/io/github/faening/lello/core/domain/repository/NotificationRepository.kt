package io.github.faening.lello.core.domain.repository

import io.github.faening.lello.core.model.notification.Notification

interface NotificationRepository {
    suspend fun schedule(notification: Notification)
    suspend fun cancel(notification: Notification)
}