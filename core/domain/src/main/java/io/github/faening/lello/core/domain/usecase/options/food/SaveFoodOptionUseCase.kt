package io.github.faening.lello.core.domain.usecase.options.food

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.domain.util.validateDescription
import io.github.faening.lello.core.model.option.FoodOption
import javax.inject.Inject

class SaveFoodOptionUseCase @Inject constructor(
    private val repository: OptionRepository<FoodOption>
) {
    suspend operator fun invoke(vararg items: FoodOption) {
        val formattedItems = items.map { item ->
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.insert(item) }
    }
}
