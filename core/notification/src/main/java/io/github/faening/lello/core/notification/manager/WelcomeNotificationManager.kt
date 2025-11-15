package io.github.faening.lello.core.notification.manager

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.notification.NotificationHelper

object WelcomeNotificationManager {
    private const val NOTIFICATION_ID = 1001

    fun showWelcomeNotification(context: Context) {
        val notification = NotificationCompat.Builder(context, NotificationHelper.getChannelId())
            .setSmallIcon(LelloIcons.Filled.Capybara.resId)
            .setContentTitle("Bem-vindo!")
            .setContentText("Lello está com saudade de você. Interaja com os diários para ganhar moedas!")
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(NOTIFICATION_ID, notification)
    }
}