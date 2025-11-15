package io.github.faening.lello

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import dagger.hilt.android.HiltAndroidApp
import io.github.faening.lello.core.domain.usecase.notification.ScheduleDailyNotificationsUseCase
import io.github.faening.lello.core.notification.worker.DailyCheckInNotificationWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("SpellCheckingInspection")
@HiltAndroidApp
class LelloApplication : Application() {

    @Inject
    lateinit var scheduleDailyNotificationsUseCase: ScheduleDailyNotificationsUseCase

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        scheduleNotifications()
        scheduleWorkers()
    }

    @SuppressLint("ObsoleteSdkInt")
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "Lello"
            val descriptionText = "Notificações do Lello"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("lello_channel", name, importance).apply {
                description = descriptionText
            }
            val notificationManager: NotificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun scheduleNotifications() {
        CoroutineScope(Dispatchers.IO).launch {
            scheduleDailyNotificationsUseCase()
        }
    }

    private fun scheduleWorkers() {
        DailyCheckInNotificationWorker.schedule(this)
    }
}