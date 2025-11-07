package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.DailyCheckInRepository
import io.github.faening.lello.core.domain.repository.RewardBalanceRepository
import io.github.faening.lello.core.domain.repository.RewardHistoryRepository
import io.github.faening.lello.core.domain.usecase.reward.GetDailyCheckInUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.ClearRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.GetRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.ObserveRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.SaveOrUpdateRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.ClearRewardHistoryUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.GetRewardAmountByOriginUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.GetRewardHistoryByOriginUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.GetRewardHistoryUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.ObserveRewardHistoryUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.SaveRewardHistoryUseCase
import io.github.faening.lello.core.model.reward.RewardBalance
import io.github.faening.lello.core.model.reward.RewardHistory
import jakarta.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RewardUseCaseModule {

    @Provides
    @Singleton
    fun provideDailyCheckInRepository(
        useCase: GetDailyCheckInUseCase
    ): DailyCheckInRepository = useCase

    @Provides
    fun provideClearRewardBalanceUseCase(
        repository: RewardBalanceRepository<RewardBalance>
    ) = ClearRewardBalanceUseCase(repository)

    @Provides
    fun provideGetRewardBalanceUseCase(
        repository: RewardBalanceRepository<RewardBalance>
    ) = GetRewardBalanceUseCase(repository)

    @Provides
    fun provideObserveRewardBalanceUseCase(
        repository: RewardBalanceRepository<RewardBalance>
    ) = ObserveRewardBalanceUseCase(repository)

    @Provides
    fun provideSaveOrUpdateRewardBalanceUseCase(
        repository: RewardBalanceRepository<RewardBalance>
    ) = SaveOrUpdateRewardBalanceUseCase(repository)

    @Provides
    fun provideObserveRewardHistoryUseCase(
        repository: RewardHistoryRepository<RewardHistory>
    ) = ObserveRewardHistoryUseCase(repository)

    @Provides
    fun provideGetRewardHistoryUseCase(
        repository: RewardHistoryRepository<RewardHistory>
    ) = GetRewardHistoryUseCase(repository)

    @Provides
    fun provideGetRewardHistoryByOriginUseCase(
        repository: RewardHistoryRepository<RewardHistory>
    ) = GetRewardHistoryByOriginUseCase(repository)

    @Provides
    fun provideGetRewardAmountByOriginUseCase(
        repository: RewardHistoryRepository<RewardHistory>
    ) = GetRewardAmountByOriginUseCase(repository)

    @Provides
    fun provideSaveRewardHistoryUseCase(
        repository: RewardHistoryRepository<RewardHistory>
    ) = SaveRewardHistoryUseCase(repository)

    @Provides
    fun provideClearRewardHistoryUseCase(
        repository: RewardHistoryRepository<RewardHistory>
    ) = ClearRewardHistoryUseCase(repository)
}
