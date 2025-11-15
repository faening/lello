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
import io.github.faening.lello.core.domain.usecase.notification.GetNotificationPreferencesUseCase
import io.github.faening.lello.core.domain.usecase.reward.GetDailyCheckInUseCase
import io.github.faening.lello.core.model.notification.Notification
import kotlinx.coroutines.flow.first
import java.util.concurrent.TimeUnit

@HiltWorker
class DailyCheckInNotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val getDailyCheckInUseCase: GetDailyCheckInUseCase,
    private val getNotificationPreferencesUseCase: GetNotificationPreferencesUseCase,
    private val notificationRepository: NotificationRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val preferences = getNotificationPreferencesUseCase.invoke().first()

            if (!preferences.journalRewardsEnabled) {
                return Result.success()
            }

            val checkInState = getDailyCheckInUseCase.observeDailyCheckIn().first()

            if (checkInState.currentStep >= 4 && !checkInState.bonusReceived) {
                notificationRepository.schedule(
                    Notification(
                        id = NOTIFICATION_ID_DAILY_BONUS,
                        title = "🎉 Bônus disponível!",
                        message = "Você completou todos os diários hoje! Abra o app para receber sua recompensa.",
                        hour = 20, // 8 PM
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
        private const val WORK_NAME = "daily_check_in_notification"
        private const val NOTIFICATION_ID_DAILY_BONUS = 100

        fun schedule(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build()

            val workRequest = PeriodicWorkRequestBuilder<DailyCheckInNotificationWorker>(
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