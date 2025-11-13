package io.github.faening.lello.core.domain.usecase.medication

import io.github.faening.lello.core.domain.repository.MedicationRepository
import io.github.faening.lello.core.model.medication.Medication
import jakarta.inject.Inject

class GetMedicationByIdUseCase @Inject constructor(
    private val repository: MedicationRepository<Medication>
) {
    operator fun invoke(id: Long): Medication? {
        return repository.getMedicationById(id)
    }
}