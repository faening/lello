package io.github.faening.lello.core.domain.usecase.options.medication.dosageunit

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.MedicationDosageUnitOption
import javax.inject.Inject

class UpdateMedicationDosageUnitOptionActiveStatusUseCase @Inject constructor(
    private val repository: OptionRepository<MedicationDosageUnitOption>
) {
    /**
     * Update only the active status for the provided options.
     */
    suspend operator fun invoke(vararg items: MedicationDosageUnitOption) {
        items.forEach { item -> item.id.validateId() }
        items.forEach { item -> repository.update(item) }
    }
}
