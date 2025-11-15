package io.github.faening.lello.core.notification.manager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import io.github.faening.lello.core.designsystem.icon.LelloIcons

object NotificationHelper {
    private const val CHANNEL_ID = "lello_notifications"
    private const val CHANNEL_NAME = "Notificações do Lello"
    private const val CHANNEL_DESCRIPTION = "Notificações sobre o Lello e suas atividades"

    fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                CHANNEL_NAME,
                NotificationManager.IMPORTANCE_DEFAULT
            ).apply {
                description = CHANNEL_DESCRIPTION
            }

            val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun showWelcomeNotification(context: Context) {
        createNotificationChannel(context)

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(LelloIcons.Filled.Capybara.resId)
            .setContentTitle("Bem-vindo!")
            .setContentText("Lello está com saudade de você. Interaja com os diários para ganhar moedas!")
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(context).notify(1001, notification)
    }
}