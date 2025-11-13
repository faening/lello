package io.github.faening.lello.core.domain.usecase.options.medication.skipreason

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.MedicationSkipReasonOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMedicationSkipReasonOptionByIdUseCase @Inject constructor(
    private val repository: OptionRepository<MedicationSkipReasonOption>
) {
    operator fun invoke(id: Long): Flow<MedicationSkipReasonOption>? {
        id.validateId()
        return repository.getById(id)
    }
}
