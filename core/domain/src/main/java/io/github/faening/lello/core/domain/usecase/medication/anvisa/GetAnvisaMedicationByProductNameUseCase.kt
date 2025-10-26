package io.github.faening.lello.core.domain.usecase.medication.anvisa

import io.github.faening.lello.core.domain.repository.AnvisaMedicationRepository
import io.github.faening.lello.core.model.AnvisaMedication
import jakarta.inject.Inject

class GetAnvisaMedicationByProductNameUseCase @Inject constructor(
    private val repository: AnvisaMedicationRepository<AnvisaMedication>
) {
    suspend operator fun invoke(productName: String): List<AnvisaMedication>? {
        return repository.getByProductName(productName)
    }
}