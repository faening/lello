package io.github.faening.lello.core.domain.usecase.options.food

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.option.FoodOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllFoodOptionUseCase @Inject constructor(
    private val repository: OptionRepository<FoodOption>
) {
    operator fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<FoodOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }
}
