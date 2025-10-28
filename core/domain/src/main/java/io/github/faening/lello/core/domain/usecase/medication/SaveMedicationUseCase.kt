package io.github.faening.lello.core.domain.usecase.medication

import io.github.faening.lello.core.domain.repository.MedicationRepository
import io.github.faening.lello.core.domain.util.validateNotZero
import io.github.faening.lello.core.model.medication.Medication
import javax.inject.Inject

class SaveMedicationUseCase @Inject constructor(
    private val repository: MedicationRepository<Medication>
) {
    suspend operator fun invoke(item: Medication) {
        val isStrengthValueValid = item.strengthValue.validateNotZero()
        if (!isStrengthValueValid) {
            throw IllegalArgumentException("Medication strength value must be greater than zero.")
        }
        repository.insert(item)
    }
}