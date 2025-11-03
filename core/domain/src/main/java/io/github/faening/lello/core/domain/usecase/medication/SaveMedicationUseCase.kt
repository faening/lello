package io.github.faening.lello.core.domain.usecase.medication

import io.github.faening.lello.core.domain.repository.MedicationRepository
import io.github.faening.lello.core.model.medication.Medication
import javax.inject.Inject

class SaveMedicationUseCase @Inject constructor(
    private val repository: MedicationRepository<Medication>
) {
    suspend operator fun invoke(item: Medication) {
        validateMedication(item)
        repository.insert(item)
    }

    private fun validateMedication(medication: Medication) {
        if (medication.dosages.isEmpty()) {
            throw IllegalArgumentException("O medicamento deve ter pelo menos 1 dosagem.")
        }

        medication.dosages.forEach { dosage ->
            if (dosage.quantity <= 0) {
                throw IllegalArgumentException("A quantidade da dosagem deve ser maior que zero.")
            }
            if ((dosage.unitOption?.id ?: 0L) <= 0L) {
                throw IllegalArgumentException("A dosagem deve ter uma unidade de medida válida.")
            }
            if (dosage.time == null) {
                throw IllegalArgumentException("A dosagem deve ter um horário definido.")
            }
        }
    }
}