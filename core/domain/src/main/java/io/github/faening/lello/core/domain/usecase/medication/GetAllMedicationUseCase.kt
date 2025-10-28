package io.github.faening.lello.core.domain.usecase.medication

import io.github.faening.lello.core.domain.repository.MedicationRepository
import io.github.faening.lello.core.model.medication.Medication
import javax.inject.Inject

class GetAllMedicationUseCase @Inject constructor(
    private val repository: MedicationRepository<Medication>
) {
    suspend operator fun invoke(): List<Medication> {
        return repository.getAll()
    }
}