package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.MedicationJournalRepository
import io.github.faening.lello.core.domain.usecase.journal.medication.GetAllMedicationJournalsUseCase
import io.github.faening.lello.core.domain.usecase.journal.medication.GetMedicationJournalsByDayUseCase
import io.github.faening.lello.core.domain.usecase.journal.medication.GetMedicationJournalsByMedicationUseCase
import io.github.faening.lello.core.domain.usecase.journal.medication.GetMedicationJournalsByTakenStatusUseCase
import io.github.faening.lello.core.domain.usecase.journal.medication.InsertMedicationJournalUseCase
import io.github.faening.lello.core.domain.usecase.journal.medication.InsertMedicationJournalsUseCase
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
    fun provideInsertMedicationJournalUseCase(
        repository: MedicationJournalRepository<MedicationJournal>
    ) = InsertMedicationJournalUseCase(repository)

    @Provides
    fun provideInsertMedicationJournalsUseCase(
        repository: MedicationJournalRepository<MedicationJournal>
    ) = InsertMedicationJournalsUseCase(repository)
}
