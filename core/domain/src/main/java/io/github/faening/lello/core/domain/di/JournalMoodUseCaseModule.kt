package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.MoodJournalRepository
import io.github.faening.lello.core.domain.service.RewardCalculatorService
import io.github.faening.lello.core.domain.usecase.journal.mood.GetAllMoodJournalsUseCase
import io.github.faening.lello.core.domain.usecase.journal.mood.GetMoodJournalByIdUseCase
import io.github.faening.lello.core.domain.usecase.journal.mood.SaveMoodJournalUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.GetRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.SaveOrUpdateRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.SaveRewardHistoryUseCase
import io.github.faening.lello.core.model.journal.MoodJournal

@Module
@InstallIn(SingletonComponent::class)
object JournalMoodUseCaseModule {

    @Provides
    fun provideGetAllMoodJournalsUseCase(
        repository: MoodJournalRepository<MoodJournal>
    ) = GetAllMoodJournalsUseCase(repository)

    @Provides
    fun provideGetMoodJournalByIdUseCase(
        repository: MoodJournalRepository<MoodJournal>
    ) = GetMoodJournalByIdUseCase(repository)

    @Provides
    fun provideSaveMoodJournalUseCase(
        repository: MoodJournalRepository<MoodJournal>,
        rewardCalculatorService: RewardCalculatorService,
        saveOrUpdateRewardBalanceUseCase: SaveOrUpdateRewardBalanceUseCase,
        getRewardBalanceUseCase: GetRewardBalanceUseCase,
        saveRewardHistoryUseCase: SaveRewardHistoryUseCase
    ) = SaveMoodJournalUseCase(
        repository,
        rewardCalculatorService,
        saveOrUpdateRewardBalanceUseCase,
        getRewardBalanceUseCase,
        saveRewardHistoryUseCase
    )
}
