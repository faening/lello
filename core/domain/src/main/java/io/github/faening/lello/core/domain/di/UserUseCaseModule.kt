package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.UserRepository
import io.github.faening.lello.core.domain.usecase.user.GetUserBiometricPreferencesUseCase
import io.github.faening.lello.core.domain.usecase.user.GetUserEmailUseCase
import io.github.faening.lello.core.domain.usecase.user.SaveUserEmailUseCase
import io.github.faening.lello.core.domain.usecase.user.SetUserBiometricPreferencesUseCase

@Module
@InstallIn(SingletonComponent::class)
object UserUseCaseModule {

    @Provides
    fun provideGetUserBiometricPreferencesUseCase(
        repository: UserRepository
    ) = GetUserBiometricPreferencesUseCase(repository)

    @Provides
    fun provideGetUserEmailUseCase(
        repository: UserRepository
    ) = GetUserEmailUseCase(repository)

    @Provides
    fun provideSaveUserEmailUseCase(
        repository: UserRepository
    ) = SaveUserEmailUseCase(repository)

    @Provides
    fun provideSetUserBiometricPreferencesUseCase(
        repository: UserRepository
    ) = SetUserBiometricPreferencesUseCase(repository)
}
