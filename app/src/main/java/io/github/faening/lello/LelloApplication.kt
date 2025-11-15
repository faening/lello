package io.github.faening.lello

import android.annotation.SuppressLint
import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import androidx.hilt.work.HiltWorkerFactory
import androidx.work.Configuration
import dagger.hilt.android.HiltAndroidApp
import io.github.faening.lello.core.notification.NotificationScheduler
import javax.inject.Inject

@Suppress("SpellCheckingInspection")
@HiltAndroidApp
class LelloApplication : Application(), Configuration.Provider {

    @Inject
    lateinit var notificationScheduler: NotificationScheduler

    @Inject
    lateinit var workerFactory: HiltWorkerFactory

    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
        notificationScheduler.initialize()
    }

    override val workManagerConfiguration: Configuration
        get() = Configuration.Builder()
            .setWorkerFactory(workerFactory)
            .build()

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
}