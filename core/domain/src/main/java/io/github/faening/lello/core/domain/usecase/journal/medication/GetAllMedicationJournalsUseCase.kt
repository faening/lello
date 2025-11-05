package io.github.faening.lello.core.domain.usecase.journal.medication

import io.github.faening.lello.core.domain.repository.MedicationJournalRepository
import io.github.faening.lello.core.model.journal.MedicationJournal
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAllMedicationJournalsUseCase @Inject constructor(
    private val repository: MedicationJournalRepository<MedicationJournal>
) {
    operator fun invoke(): Flow<List<MedicationJournal>> {
        return repository.getAllJournals()
    }
}