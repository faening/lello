package io.github.faening.lello.core.domain.usecase.medication.anvisa

import io.github.faening.lello.core.domain.repository.AnvisaMedicationRepository
import io.github.faening.lello.core.model.AnvisaMedication
import javax.inject.Inject

class GetAllAnvisaMedicationUseCase @Inject constructor(
    private val repository: AnvisaMedicationRepository<AnvisaMedication>
) {
    suspend operator fun invoke(): List<AnvisaMedication> {
        return repository.getAll()
    }
}