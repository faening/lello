package io.github.faening.lello.core.notification

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import io.github.faening.lello.core.domain.usecase.notification.GetNotificationPreferencesUseCase
import io.github.faening.lello.core.domain.usecase.notification.ScheduleDailyNotificationsUseCase
import io.github.faening.lello.core.notification.worker.DailyCheckInNotificationWorker
import io.github.faening.lello.core.notification.worker.MedicationNotificationWorker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationScheduler @Inject constructor(
    @ApplicationContext private val context: Context,
    private val getNotificationPreferencesUseCase: GetNotificationPreferencesUseCase,
    private var scheduleDailyNotificationsUseCase: ScheduleDailyNotificationsUseCase
) {
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    fun initialize() {
        scope.launch {
            getNotificationPreferencesUseCase.invoke().collect { preferences ->
                handleMedicationNotifications(preferences.medicationEnabled)
                handleJournalRewardsNotifications(preferences.journalRewardsEnabled)
            }
        }
    }

    private fun scheduleNotifications() {
        CoroutineScope(Dispatchers.IO).launch {
            scheduleDailyNotificationsUseCase()
        }
    }

    private fun handleJournalRewardsNotifications(enabled: Boolean) {
        if (enabled) {
            DailyCheckInNotificationWorker.schedule(context)
        } else {
            DailyCheckInNotificationWorker.cancel(context)
        }
    }

    private fun handleMedicationNotifications(enabled: Boolean) {
        if (enabled) {
            MedicationNotificationWorker.schedule(context)
        } else {
            MedicationNotificationWorker.cancel(context)
        }
    }
}