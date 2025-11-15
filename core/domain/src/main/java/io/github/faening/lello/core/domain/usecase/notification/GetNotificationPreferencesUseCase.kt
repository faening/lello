package io.github.faening.lello.core.domain.usecase.notification

import io.github.faening.lello.core.domain.repository.NotificationSettingsResources
import io.github.faening.lello.core.model.settings.NotificationPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import javax.inject.Inject

class GetNotificationPreferencesUseCase @Inject constructor(
    private val repository: NotificationSettingsResources
) {
    operator fun invoke(): Flow<NotificationPreferences> {
        return combine(
            repository.observeJournalRewardsNotificationEnabled(),
            repository.observeMedicationNotificationEnabled(),
            repository.observeMascotEnergyNotificationEnabled()
        ) { journalRewards, medication, mascotEnergy ->
            NotificationPreferences(
                journalRewardsEnabled = journalRewards,
                medicationEnabled = medication,
                mascotEnergyEnabled = mascotEnergy
            )
        }
    }
}