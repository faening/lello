package io.github.faening.lello.core.domain.usecase.journal.medication

import io.github.faening.lello.core.domain.repository.MedicationJournalRepository
import io.github.faening.lello.core.model.journal.MedicationJournal
import jakarta.inject.Inject

class SaveMedicationJournalsUseCase @Inject constructor(
    private val repository: MedicationJournalRepository<MedicationJournal>
) {
    suspend operator fun invoke(journals: List<MedicationJournal>): List<Long> {
        return repository.insert(journals)
    }
}