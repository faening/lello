package io.github.faening.lello.core.domain.usecase.notification

import io.github.faening.lello.core.domain.repository.NotificationRepository
import io.github.faening.lello.core.model.notification.Notification
import javax.inject.Inject

class ScheduleDailyNotificationsUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) {
    suspend operator fun invoke() {
        notificationRepository.schedule(
            Notification(
                id = 1,
                title = "Lello quer sua atenção!",
                message = "Não se esqueça de interagir com seus diários para ganhar moedas.",
                hour = 9,
                minute = 0
            )
        )
        notificationRepository.schedule(
            Notification(
                id = 2,
                title = "Lello está com saudades!",
                message = "Que tal dar uma olhada nos seus diários e ganhar umas moedas?",
                hour = 15,
                minute = 0
            )
        )
        notificationRepository.schedule(
            Notification(
                id = 3,
                title = "Boa noite com o Lello!",
                message = "Antes de dormir, que tal registrar como foi seu dia nos diários?",
                hour = 21,
                minute = 0
            )
        )
    }
}