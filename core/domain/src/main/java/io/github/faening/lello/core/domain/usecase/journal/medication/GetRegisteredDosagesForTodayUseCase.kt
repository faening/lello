package io.github.faening.lello.core.domain.usecase.journal.medication

import io.github.faening.lello.core.domain.repository.MedicationJournalRepository
import io.github.faening.lello.core.model.journal.MedicationJournal
import jakarta.inject.Inject

class GetRegisteredDosagesForTodayUseCase @Inject constructor(
    private val repository: MedicationJournalRepository<MedicationJournal>
) {
    suspend operator fun invoke(medicationId: Long): List<Long> {
        return repository.getRegisteredDosageIdsForToday(medicationId, System.currentTimeMillis())
    }
}
