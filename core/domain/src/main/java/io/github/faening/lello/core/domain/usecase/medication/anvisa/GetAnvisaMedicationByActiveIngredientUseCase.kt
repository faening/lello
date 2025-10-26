package io.github.faening.lello.core.domain.usecase.medication.anvisa

import io.github.faening.lello.core.domain.repository.AnvisaMedicationRepository
import io.github.faening.lello.core.model.AnvisaMedication
import javax.inject.Inject

class GetAnvisaMedicationByActiveIngredientUseCase @Inject constructor(
    private val repository: AnvisaMedicationRepository<AnvisaMedication>
) {
    suspend operator fun invoke(activeIngredient: String): List<AnvisaMedication>? {
        return repository.getByActiveIngredient(activeIngredient)
    }
}