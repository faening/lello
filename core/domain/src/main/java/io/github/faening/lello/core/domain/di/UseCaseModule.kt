package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.JournalCategoryResources
import io.github.faening.lello.core.domain.repository.RewardBalanceRepository
import io.github.faening.lello.core.domain.repository.RewardHistoryRepository
import io.github.faening.lello.core.domain.usecase.options.JournalCategoryUseCase
import io.github.faening.lello.core.domain.usecase.reward.RewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.RewardHistoryUseCase
import io.github.faening.lello.core.domain.repository.OnboardingPreferencesRepository
import io.github.faening.lello.core.domain.usecase.onboarding.OnboardingPreferencesUseCase
import io.github.faening.lello.core.model.journal.JournalCategory
import io.github.faening.lello.core.model.reward.RewardBalance
import io.github.faening.lello.core.model.reward.RewardHistory

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideJournalCategoryUseCases(
        repository: JournalCategoryResources<JournalCategory>
    ) = JournalCategoryUseCase(repository)

    @Provides
    fun provideRewardBalanceUseCase(
        repository: RewardBalanceRepository<RewardBalance>
    ) = RewardBalanceUseCase(repository)

    @Provides
    fun provideRewardHistoryUseCase(
        repository: RewardHistoryRepository<RewardHistory>
    ) = RewardHistoryUseCase(repository)

    @Provides
    fun provideOnboardingPreferencesUseCase(
        repository: OnboardingPreferencesRepository
    ) = OnboardingPreferencesUseCase(repository)
}