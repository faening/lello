package io.github.faening.lello.core.domain.usecase.options.medication.dosageunit

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.MedicationDosageUnitOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMedicationDosageUnitOptionByIdUseCase @Inject constructor(
    private val repository: OptionRepository<MedicationDosageUnitOption>
) {
    operator fun invoke(id: Long): Flow<MedicationDosageUnitOption>? {
        id.validateId()
        return repository.getById(id)
    }
}
