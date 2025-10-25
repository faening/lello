package io.github.faening.lello.core.domain.usecase.options.food

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.domain.util.validateNotBlocked
import io.github.faening.lello.core.model.option.FoodOption
import javax.inject.Inject

class DeleteFoodOptionUseCase @Inject constructor(
    private val repository: OptionRepository<FoodOption>
) {
    suspend operator fun invoke(vararg items: FoodOption) {
        items.forEach { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
        }
        items.forEach { item -> repository.delete(item) }
    }
}
