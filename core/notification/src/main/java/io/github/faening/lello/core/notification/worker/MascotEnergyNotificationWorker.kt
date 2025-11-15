package io.github.faening.lello.core.notification.worker

import android.content.Context
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
import io.github.faening.lello.core.domain.repository.NotificationRepository
import io.github.faening.lello.core.domain.usecase.mascot.GetMascotVitalityHistoryUseCase
import io.github.faening.lello.core.domain.usecase.notification.GetNotificationPreferencesUseCase
import io.github.faening.lello.core.model.notification.Notification
import kotlinx.coroutines.flow.first
import java.util.concurrent.TimeUnit

@HiltWorker
class MascotEnergyNotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val getMascotVitalityHistoryUseCase: GetMascotVitalityHistoryUseCase,
    private val getNotificationPreferencesUseCase: GetNotificationPreferencesUseCase,
    private val notificationRepository: NotificationRepository
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
                notificationRepository.schedule(
                    Notification(
                        id = NOTIFICATION_ID_MASCOT_ENERGY,
                        title = "❤️ Lello está fraco!",
                        message = "O Lello está com pouca energia ($currentVitality%). Cuide dele para aumentar sua vitalidade.",
                        hour = 14, // 2 PM
                        minute = 0
                    )
                )
            }

            Result.success()
        } catch (e: Exception) {
            Result.retry()
        }
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