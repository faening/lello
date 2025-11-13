package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.OnboardingRepository
import io.github.faening.lello.core.domain.usecase.onboarding.OnboardingUseCase

@Module
@InstallIn(SingletonComponent::class)
object OnboardingUseCaseModule {

    @Provides
    fun provideOnboardingUseCase(
        repository: OnboardingRepository
    ) = OnboardingUseCase(repository)
}
