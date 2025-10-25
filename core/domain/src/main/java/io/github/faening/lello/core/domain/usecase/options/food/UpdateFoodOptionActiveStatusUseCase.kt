package io.github.faening.lello.core.domain.usecase.options.food

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.FoodOption
import javax.inject.Inject

class UpdateFoodOptionActiveStatusUseCase @Inject constructor(
    private val repository: OptionRepository<FoodOption>
) {
    /**
     * Update only the active status for the provided options.
     */
    suspend operator fun invoke(vararg items: FoodOption) {
        items.forEach { item -> item.id.validateId() }
        items.forEach { item -> repository.update(item) }
    }
}
