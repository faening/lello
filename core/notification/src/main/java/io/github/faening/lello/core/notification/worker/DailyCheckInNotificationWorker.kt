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
import io.github.faening.lello.core.domain.usecase.notification.GetNotificationPreferencesUseCase
import io.github.faening.lello.core.domain.usecase.reward.GetDailyCheckInUseCase
import io.github.faening.lello.core.notification.NotificationHelper
import kotlinx.coroutines.flow.first
import java.util.concurrent.TimeUnit

@HiltWorker
class DailyCheckInNotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val getDailyCheckInUseCase: GetDailyCheckInUseCase,
    private val getNotificationPreferencesUseCase: GetNotificationPreferencesUseCase,
    // private val notificationRepository: NotificationRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val preferences = getNotificationPreferencesUseCase.invoke().first()

            if (!preferences.journalRewardsEnabled) {
                return Result.success()
            }

            val checkInState = getDailyCheckInUseCase.observeDailyCheckIn().first()

            if (checkInState.currentStep >= 4 && !checkInState.bonusReceived) {
                // Opção A: publicar agora usando o channel do Helper
                showDailyBonusNotification()

                // Opção B: se preferir manter o agendamento às 20\:00,
                // garanta que o NotificationRepository use NotificationHelper.getChannelId() ao construir a notificação.
                // notificationRepository.schedule(...)
            }

            Result.success()
        } catch (_: Exception) {
            Result.retry()
        }
    }

    private fun showDailyBonusNotification() {
        val notification = NotificationCompat.Builder(applicationContext, NotificationHelper.getChannelId())
            .setSmallIcon(LelloIcons.Filled.Capybara.resId)
            .setContentTitle("🎉 Bônus disponível!")
            .setContentText("Você completou todos os diários hoje! Abra o app para receber sua recompensa.")
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(applicationContext).notify(NOTIFICATION_ID_DAILY_BONUS, notification)
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