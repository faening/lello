package io.github.faening.lello.core.domain.usecase.options.meal

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.domain.util.validateNotBlocked
import io.github.faening.lello.core.model.option.MealOption
import javax.inject.Inject

class DeleteMealOptionUseCase @Inject constructor(
    private val repository: OptionRepository<MealOption>
) {
    suspend operator fun invoke(vararg items: MealOption) {
        items.forEach { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
        }
        items.forEach { item -> repository.delete(item) }
    }
}
