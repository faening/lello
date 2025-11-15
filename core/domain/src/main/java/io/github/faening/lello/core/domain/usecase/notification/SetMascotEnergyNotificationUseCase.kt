package io.github.faening.lello.core.domain.usecase.notification

import io.github.faening.lello.core.domain.repository.NotificationSettingsResources
import javax.inject.Inject

class SetMascotEnergyNotificationUseCase @Inject constructor(
    private val repository: NotificationSettingsResources
) {
    suspend operator fun invoke(enabled: Boolean) {
        repository.setMascotEnergyNotificationEnabled(enabled)
    }
}