package io.github.faening.lello.core.domain.usecase.options.meal

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.option.MealOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllMealOptionUseCase @Inject constructor(
    private val repository: OptionRepository<MealOption>
) {
    operator fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<MealOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }
}
