package io.github.faening.lello.core.domain.usecase.medication

import io.github.faening.lello.core.domain.repository.MedicationRepository
import io.github.faening.lello.core.model.Medication
import jakarta.inject.Inject

class GetMedicationByProductNameUseCase @Inject constructor(
    private val repository: MedicationRepository<Medication>
) {
    suspend operator fun invoke(productName: String): Medication? {
        return repository.getMedicationByProductName(productName)
    }
}