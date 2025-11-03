package io.github.faening.lello.core.domain.usecase.medication

import io.github.faening.lello.core.domain.repository.MedicationRepository
import io.github.faening.lello.core.model.medication.Medication
import jakarta.inject.Inject

class DisableMedicationUseCase @Inject constructor(
    private val repository: MedicationRepository<Medication>
) {
    suspend operator fun invoke(id: Long) {
        repository.disable(id)
    }
}