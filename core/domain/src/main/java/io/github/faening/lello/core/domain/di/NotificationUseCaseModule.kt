package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.NotificationRepository
import io.github.faening.lello.core.domain.repository.NotificationSettingsResources
import io.github.faening.lello.core.domain.usecase.notification.GetNotificationPreferencesUseCase
import io.github.faening.lello.core.domain.usecase.notification.ScheduleDailyNotificationsUseCase
import io.github.faening.lello.core.domain.usecase.notification.SetJournalRewardsNotificationUseCase
import io.github.faening.lello.core.domain.usecase.notification.SetMascotEnergyNotificationUseCase
import io.github.faening.lello.core.domain.usecase.notification.SetMedicationNotificationUseCase

@Module
@InstallIn(SingletonComponent::class)
object NotificationUseCaseModule {

    @Provides
    fun provideGetNotificationPreferencesUseCase(
        repository: NotificationSettingsResources
    ) = GetNotificationPreferencesUseCase(repository)

    @Provides
    fun provideScheduleDailyNotificationsUseCase(
        repository: NotificationRepository
    ) = ScheduleDailyNotificationsUseCase(repository)

    @Provides
    fun provideSetJournalRewardsNotificationUseCase(
        repository: NotificationSettingsResources
    ) = SetJournalRewardsNotificationUseCase(repository)

    @Provides
    fun setMascotEnergyNotificationUseCase(
        repository: NotificationSettingsResources
    ) = SetMascotEnergyNotificationUseCase(repository)

    @Provides
    fun provideSetMedicationNotificationUseCase(
        repository: NotificationSettingsResources
    ) = SetMedicationNotificationUseCase(repository)
}