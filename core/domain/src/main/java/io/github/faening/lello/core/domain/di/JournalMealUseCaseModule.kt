package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.MealJournalRepository
import io.github.faening.lello.core.domain.service.RewardCalculatorService
import io.github.faening.lello.core.domain.usecase.journal.meal.GetAllMealJournalsUseCase
import io.github.faening.lello.core.domain.usecase.journal.meal.GetMealJournalByIdUseCase
import io.github.faening.lello.core.domain.usecase.journal.meal.SaveMealJournalUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.GetRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.SaveOrUpdateRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.SaveRewardHistoryUseCase
import io.github.faening.lello.core.model.journal.MealJournal

@Module
@InstallIn(SingletonComponent::class)
object JournalMealUseCaseModule {

    @Provides
    fun provideGetAllMealJournalsUseCase(
        repository: MealJournalRepository<MealJournal>
    ) = GetAllMealJournalsUseCase(repository)

    @Provides
    fun provideGetMealJournalByIdUseCase(
        repository: MealJournalRepository<MealJournal>
    ) = GetMealJournalByIdUseCase(repository)

    @Provides
    fun provideSaveMealJournalUseCase(
        repository: MealJournalRepository<MealJournal>,
        rewardCalculatorService: RewardCalculatorService,
        saveOrUpdateRewardBalanceUseCase: SaveOrUpdateRewardBalanceUseCase,
        getRewardBalanceUseCase: GetRewardBalanceUseCase,
        saveRewardHistoryUseCase: SaveRewardHistoryUseCase
    ) = SaveMealJournalUseCase(
        repository,
        rewardCalculatorService,
        saveOrUpdateRewardBalanceUseCase,
        getRewardBalanceUseCase,
        saveRewardHistoryUseCase
    )
}
