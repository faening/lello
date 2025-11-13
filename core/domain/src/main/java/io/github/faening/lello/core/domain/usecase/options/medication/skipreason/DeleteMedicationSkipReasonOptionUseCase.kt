package io.github.faening.lello.core.domain.usecase.options.medication.skipreason

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.domain.util.validateNotBlocked
import io.github.faening.lello.core.model.option.MedicationSkipReasonOption
import javax.inject.Inject

class DeleteMedicationSkipReasonOptionUseCase @Inject constructor(
    private val repository: OptionRepository<MedicationSkipReasonOption>
) {
    suspend operator fun invoke(vararg items: MedicationSkipReasonOption) {
        items.forEach { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
        }
        items.forEach { item -> repository.delete(item) }
    }
}
