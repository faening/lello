package io.github.faening.lello.core.domain.usecase.options.meal

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.domain.util.validateDescription
import io.github.faening.lello.core.model.option.MealOption
import javax.inject.Inject

class SaveMealOptionUseCase @Inject constructor(
    private val repository: OptionRepository<MealOption>
) {
    suspend operator fun invoke(vararg items: MealOption) {
        val formattedItems = items.map { item ->
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.insert(item) }
    }
}
