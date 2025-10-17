package io.github.faening.lello.core.notification.manager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import io.github.faening.lello.core.domain.repository.NotificationRepository
import io.github.faening.lello.core.model.notification.Notification
import java.util.Calendar
import javax.inject.Inject

class AndroidNotificationRepository @Inject constructor(
    private val context: Context
) : NotificationRepository {

    override suspend fun schedule(notification: Notification) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java).apply {
            putExtra("notification_id", notification.id)
            putExtra("notification_title", notification.title)
            putExtra("notification_message", notification.message)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notification.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, notification.hour)
            set(Calendar.MINUTE, notification.minute)
            set(Calendar.SECOND, 0)
        }

        if (calendar.timeInMillis < System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1)
        }

        alarmManager.setInexactRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            AlarmManager.INTERVAL_DAY,
            pendingIntent
        )
    }

    override suspend fun cancel(notification: Notification) {
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, NotificationReceiver::class.java)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notification.id,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }
}