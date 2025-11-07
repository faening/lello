package io.github.faening.lello.core.domain.usecase.medication

import io.github.faening.lello.core.domain.repository.MedicationRepository
import io.github.faening.lello.core.model.medication.Medication
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMedicationsUseCase @Inject constructor(
    private val repository: MedicationRepository<Medication>
) {
    operator fun invoke(): Flow<List<Medication>> {
        return repository.getAllMedications()
    }
}