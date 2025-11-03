package io.github.faening.lello.core.domain.usecase.options.medication.activeingredient

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import javax.inject.Inject

class UpdateMedicationActiveIngredientOptionActiveStatusUseCase @Inject constructor(
    private val repository: OptionRepository<MedicationActiveIngredientOption>
) {
    /**
     * Update only the active status for the provided options.
     */
    suspend operator fun invoke(vararg items: MedicationActiveIngredientOption) {
        items.forEach { item -> item.id.validateId() }
        items.forEach { item -> repository.update(item) }
    }
}
