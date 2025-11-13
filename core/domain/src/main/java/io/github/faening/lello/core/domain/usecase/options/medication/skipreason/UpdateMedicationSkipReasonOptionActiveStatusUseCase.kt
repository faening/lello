package io.github.faening.lello.core.domain.usecase.options.medication.skipreason

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.MedicationSkipReasonOption
import javax.inject.Inject

class UpdateMedicationSkipReasonOptionActiveStatusUseCase @Inject constructor(
    private val repository: OptionRepository<MedicationSkipReasonOption>
) {
    /**
     * Update only the active status for the provided options.
     */
    suspend operator fun invoke(vararg items: MedicationSkipReasonOption) {
        items.forEach { item -> item.id.validateId() }
        items.forEach { item -> repository.update(item) }
    }
}
