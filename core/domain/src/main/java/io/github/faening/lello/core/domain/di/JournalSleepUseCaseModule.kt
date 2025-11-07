package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.JournalRepository
import io.github.faening.lello.core.domain.service.RewardCalculatorService
import io.github.faening.lello.core.domain.usecase.journal.sleep.DeleteSleepJournalUseCase
import io.github.faening.lello.core.domain.usecase.journal.sleep.GetAllSleepJournalUseCase
import io.github.faening.lello.core.domain.usecase.journal.sleep.GetSleepJournalByIdUseCase
import io.github.faening.lello.core.domain.usecase.journal.sleep.SaveSleepJournalUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.GetRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.SaveOrUpdateRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.SaveRewardHistoryUseCase
import io.github.faening.lello.core.model.journal.SleepJournal

@Module
@InstallIn(SingletonComponent::class)
object JournalSleepUseCaseModule {

    @Provides
    fun provideGetAllSleepJournalUseCase(
        repository: JournalRepository<SleepJournal>
    ) = GetAllSleepJournalUseCase(repository)

    @Provides
    fun provideGetSleepJournalByIdUseCase(
        repository: JournalRepository<SleepJournal>
    ) = GetSleepJournalByIdUseCase(repository)

    @Provides
    fun provideSaveSleepJournalUseCase(
        repository: JournalRepository<SleepJournal>,
        rewardCalculatorService: RewardCalculatorService,
        saveOrUpdateRewardBalanceUseCase: SaveOrUpdateRewardBalanceUseCase,
        getRewardBalanceUseCase: GetRewardBalanceUseCase,
        saveRewardHistoryUseCase: SaveRewardHistoryUseCase
    ) = SaveSleepJournalUseCase(
        repository,
        rewardCalculatorService,
        saveOrUpdateRewardBalanceUseCase,
        getRewardBalanceUseCase,
        saveRewardHistoryUseCase
    )

    @Provides
    fun provideDeleteSleepJournalUseCase(
        repository: JournalRepository<SleepJournal>
    ) = DeleteSleepJournalUseCase(repository)
}
