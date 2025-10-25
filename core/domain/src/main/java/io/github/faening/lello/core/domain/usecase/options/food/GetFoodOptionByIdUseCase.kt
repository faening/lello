package io.github.faening.lello.core.domain.usecase.options.food

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.FoodOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFoodOptionByIdUseCase @Inject constructor(
    private val repository: OptionRepository<FoodOption>
) {
    operator fun invoke(id: Long): Flow<FoodOption>? {
        id.validateId()
        return repository.getById(id)
    }
}
