package io.github.faening.lello.core.notification.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.NotificationRepository
import io.github.faening.lello.core.notification.manager.AndroidNotificationRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    @Provides
    @Singleton
    fun provideAndroidNotificationRepository(
        @ApplicationContext context: Context
    ): NotificationRepository {
        return AndroidNotificationRepository(context)
    }
}