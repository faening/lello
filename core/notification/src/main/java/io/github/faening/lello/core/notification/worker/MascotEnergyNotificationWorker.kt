package io.github.faening.lello.core.notification.worker

import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.hilt.work.HiltWorker
import androidx.work.Constraints
import androidx.work.CoroutineWorker
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.domain.usecase.mascot.GetMascotVitalityHistoryUseCase
import io.github.faening.lello.core.domain.usecase.notification.GetNotificationPreferencesUseCase
import io.github.faening.lello.core.notification.NotificationHelper
import kotlinx.coroutines.flow.first
import java.util.concurrent.TimeUnit

@HiltWorker
class MascotEnergyNotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val getMascotVitalityHistoryUseCase: GetMascotVitalityHistoryUseCase,
    private val getNotificationPreferencesUseCase: GetNotificationPreferencesUseCase,
    // private val notificationRepository: NotificationRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val preferences = getNotificationPreferencesUseCase.invoke().first()

            if (!preferences.mascotEnergyEnabled) {
                return Result.success()
            }

            val vitalityHistory = getMascotVitalityHistoryUseCase.invoke()
            val currentVitality = vitalityHistory.firstOrNull()?.value ?: 100

            if (currentVitality < 20) {
                showLowEnergyNotification(currentVitality)
            }

            Result.success()
        } catch (_: Exception) {
            Result.retry()
        }
    }

    private fun showLowEnergyNotification(currentVitality: Int) {
        val notification = NotificationCompat.Builder(applicationContext, NotificationHelper.getChannelId())
            .setSmallIcon(LelloIcons.Filled.Capybara.resId)
            .setContentTitle("❤️ Lello está fraco!")
            .setContentText("O Lello está com pouca energia ($currentVitality%). Cuide dele para aumentar sua vitalidade.")
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(applicationContext).notify(NOTIFICATION_ID_MASCOT_ENERGY, notification)
    }

    companion object {
        private const val WORK_NAME = "mascot_energy_notification"
        private const val NOTIFICATION_ID_MASCOT_ENERGY = 300

        fun schedule(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build()

            val workRequest = PeriodicWorkRequestBuilder<MascotEnergyNotificationWorker>(
                repeatInterval = 6,
                repeatIntervalTimeUnit = TimeUnit.HOURS
            )
                .setConstraints(constraints)
                .build()

            WorkManager.getInstance(context).enqueueUniquePeriodicWork(
                WORK_NAME,
                ExistingPeriodicWorkPolicy.KEEP,
                workRequest
            )
        }

        fun cancel(context: Context) {
            WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
        }
    }
}