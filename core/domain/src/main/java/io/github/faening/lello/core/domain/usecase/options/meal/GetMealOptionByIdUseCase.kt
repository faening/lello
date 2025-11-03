package io.github.faening.lello.core.domain.usecase.options.meal

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.MealOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetMealOptionByIdUseCase @Inject constructor(
    private val repository: OptionRepository<MealOption>
) {
    operator fun invoke(id: Long): Flow<MealOption>? {
        id.validateId()
        return repository.getById(id)
    }
}
