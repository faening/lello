package io.github.faening.lello.core.domain.usecase.options.climate

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.option.ClimateOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllClimateOptionUseCase @Inject constructor(
    private val repository: OptionRepository<ClimateOption>
) {
    operator fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<ClimateOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }
}
