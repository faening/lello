package io.github.faening.lello.core.domain.usecase.options.meal

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.MealOption
import javax.inject.Inject

class UpdateMealOptionActiveStatusUseCase @Inject constructor(
    private val repository: OptionRepository<MealOption>
) {
    /**
     * Update only the active status for the provided options.
     */
    suspend operator fun invoke(vararg items: MealOption) {
        items.forEach { item -> item.id.validateId() }
        items.forEach { item -> repository.update(item) }
    }
}
