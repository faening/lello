package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.MedicationJournalRepository
import io.github.faening.lello.core.domain.service.RewardCalculatorService
import io.github.faening.lello.core.domain.usecase.journal.medication.GetAllMedicationJournalsUseCase
import io.github.faening.lello.core.domain.usecase.journal.medication.GetMedicationJournalsByDayUseCase
import io.github.faening.lello.core.domain.usecase.journal.medication.GetMedicationJournalsByMedicationUseCase
import io.github.faening.lello.core.domain.usecase.journal.medication.GetMedicationJournalsByTakenStatusUseCase
import io.github.faening.lello.core.domain.usecase.journal.medication.GetRegisteredDosagesForTodayUseCase
import io.github.faening.lello.core.domain.usecase.journal.medication.SaveMedicationJournalUseCase
import io.github.faening.lello.core.domain.usecase.journal.medication.SaveMedicationJournalsUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.GetRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.balance.SaveOrUpdateRewardBalanceUseCase
import io.github.faening.lello.core.domain.usecase.reward.history.SaveRewardHistoryUseCase
import io.github.faening.lello.core.model.journal.MedicationJournal

@Module
@InstallIn(SingletonComponent::class)
object JournalMedicationUseCaseModule {

    @Provides
    fun provideGetAllMedicationJournalsUseCase(
        repository: MedicationJournalRepository<MedicationJournal>
    ) = GetAllMedicationJournalsUseCase(repository)

    @Provides
    fun provideGetMedicationJournalsByDayUseCase(
        repository: MedicationJournalRepository<MedicationJournal>
    ) = GetMedicationJournalsByDayUseCase(repository)

    @Provides
    fun provideGetMedicationJournalsByTakenStatusUseCase(
        repository: MedicationJournalRepository<MedicationJournal>
    ) = GetMedicationJournalsByTakenStatusUseCase(repository)

    @Provides
    fun provideGetMedicationJournalsByMedicationUseCase(
        repository: MedicationJournalRepository<MedicationJournal>
    ) = GetMedicationJournalsByMedicationUseCase(repository)

    @Provides
    fun provideSaveMedicationJournalUseCase(
        repository: MedicationJournalRepository<MedicationJournal>,
        rewardCalculatorService: RewardCalculatorService,
        saveOrUpdateRewardBalanceUseCase: SaveOrUpdateRewardBalanceUseCase,
        getRewardBalanceUseCase: GetRewardBalanceUseCase,
        saveRewardHistoryUseCase: SaveRewardHistoryUseCase
    ) = SaveMedicationJournalUseCase(
        repository,
        rewardCalculatorService,
        saveOrUpdateRewardBalanceUseCase,
        getRewardBalanceUseCase,
        saveRewardHistoryUseCase
    )

    @Provides
    fun provideSaveMedicationJournalsUseCase(
        repository: MedicationJournalRepository<MedicationJournal>
    ) = SaveMedicationJournalsUseCase(repository)

    @Provides
    fun provideGetRegisteredDosagesForTodayUseCase(
        repository: MedicationJournalRepository<MedicationJournal>
    ) = GetRegisteredDosagesForTodayUseCase(repository)
}