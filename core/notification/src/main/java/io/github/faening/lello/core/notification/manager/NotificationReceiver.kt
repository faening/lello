package io.github.faening.lello.core.notification.manager

import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import io.github.faening.lello.core.designsystem.R

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        val notificationId = intent.getIntExtra("notification_id", 0)
        val title = intent.getStringExtra("notification_title")
        val message = intent.getStringExtra("notification_message")
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val builder = NotificationCompat.Builder(context, "lello_channel")
            .setSmallIcon(R.drawable.ic_lello_logo_custom)
            .setContentTitle(title)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        notificationManager.notify(notificationId, builder.build())
    }
}