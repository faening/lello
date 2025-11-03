package io.github.faening.lello.core.domain.usecase.options.medication.dosageform

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.MedicationDosageFormOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMedicationDosageFormOptionByIdUseCase @Inject constructor(
    private val repository: OptionRepository<MedicationDosageFormOption>
) {
    operator fun invoke(id: Long): Flow<MedicationDosageFormOption>? {
        id.validateId()
        return repository.getById(id)
    }
}
