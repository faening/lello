package io.github.faening.lello.core.notification.worker

import android.content.Context
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import androidx.work.WorkerParameters
import io.github.faening.lello.core.notification.manager.WelcomeNotificationManager
import java.util.concurrent.TimeUnit

class WelcomeNotificationWorker(
    context: Context,
    params: WorkerParameters
) : Worker(context, params) {

    override fun doWork(): Result {
        WelcomeNotificationManager.showWelcomeNotification(applicationContext)
        return Result.success()
    }

    companion object {
        fun schedule(context: Context) {
            val welcomeRequest = OneTimeWorkRequestBuilder<WelcomeNotificationWorker>()
                .setInitialDelay(5, TimeUnit.SECONDS)
                .build()

            WorkManager.getInstance(context).enqueueUniqueWork(
                "welcome_notification",
                androidx.work.ExistingWorkPolicy.KEEP,
                welcomeRequest
            )
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork("welcome_notification")
        }
    }
}