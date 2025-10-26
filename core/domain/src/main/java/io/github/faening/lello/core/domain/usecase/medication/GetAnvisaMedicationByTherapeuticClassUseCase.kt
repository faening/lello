package io.github.faening.lello.core.domain.usecase.medication

import io.github.faening.lello.core.domain.repository.AnvisaMedicationRepository
import io.github.faening.lello.core.model.AnvisaMedication
import jakarta.inject.Inject

class GetAnvisaMedicationByTherapeuticClassUseCase @Inject constructor(
    private val repository: AnvisaMedicationRepository<AnvisaMedication>
) {
    suspend operator fun invoke(therapeuticClass: String): List<AnvisaMedication>? {
        return repository.getMedicationByTherapeuticClass(therapeuticClass)
    }
}