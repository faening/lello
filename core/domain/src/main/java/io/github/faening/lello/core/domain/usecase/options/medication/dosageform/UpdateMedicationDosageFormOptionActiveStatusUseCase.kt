package io.github.faening.lello.core.domain.usecase.options.medication.dosageform

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.MedicationDosageFormOption
import javax.inject.Inject

class UpdateMedicationDosageFormOptionActiveStatusUseCase @Inject constructor(
    private val repository: OptionRepository<MedicationDosageFormOption>
) {
    /**
     * Update only the active status for the provided options.
     */
    suspend operator fun invoke(vararg items: MedicationDosageFormOption) {
        items.forEach { item -> item.id.validateId() }
        items.forEach { item -> repository.update(item) }
    }
}
