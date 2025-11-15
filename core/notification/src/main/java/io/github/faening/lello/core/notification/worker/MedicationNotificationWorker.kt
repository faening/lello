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
import io.github.faening.lello.core.domain.usecase.medication.GetAllMedicationsUseCase
import io.github.faening.lello.core.domain.usecase.notification.GetNotificationPreferencesUseCase
import io.github.faening.lello.core.notification.NotificationHelper
import kotlinx.coroutines.flow.first
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit

@HiltWorker
class MedicationNotificationWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val getAllMedicationsUseCase: GetAllMedicationsUseCase,
    private val getNotificationPreferencesUseCase: GetNotificationPreferencesUseCase,
    // private val notificationRepository: NotificationRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val preferences = getNotificationPreferencesUseCase.invoke().first()

            if (!preferences.medicationEnabled) {
                return Result.success()
            }

            val medications = getAllMedicationsUseCase.invoke().first()
            val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

            medications.forEach { medication ->
                medication.dosages.forEach { dosage ->
                    val targetTime = LocalTime.parse(dosage.time.toString(), timeFormatter)
                    if (shouldNotifyNow(targetTime)) {
                        showMedicationNotification(
                            id = generateNotificationId(medication.id!!, dosage.id!!),
                            message = "Está na hora de tomar o(a) ${medication.activeIngredientOption?.description}"
                        )
                    }
                }
            }

            Result.success()
        } catch (_: Exception) {
            Result.retry()
        }
    }

    private fun shouldNotifyNow(target: LocalTime): Boolean {
        val now = LocalTime.now()
        return now.hour == target.hour && now.minute == target.minute
    }

    private fun generateNotificationId(medicationId: Long, dosageId: Long): Int {
        return (NOTIFICATION_ID_BASE + medicationId * 1000 + dosageId).toInt()
    }

    private fun showMedicationNotification(id: Int, message: String) {
        val notification = NotificationCompat.Builder(applicationContext, NotificationHelper.getChannelId())
            .setSmallIcon(LelloIcons.Outlined.Clock.resId)
            .setContentTitle("⏰ Hora do remédio")
            .setContentText(message)
            .setAutoCancel(true)
            .build()

        NotificationManagerCompat.from(applicationContext).notify(id, notification)
    }

    companion object {
        private const val WORK_NAME = "medication_notification"
        private const val NOTIFICATION_ID_BASE = 200

        fun schedule(context: Context) {
            val constraints = Constraints.Builder()
                .setRequiredNetworkType(NetworkType.NOT_REQUIRED)
                .build()

            val workRequest = PeriodicWorkRequestBuilder<MedicationNotificationWorker>(
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