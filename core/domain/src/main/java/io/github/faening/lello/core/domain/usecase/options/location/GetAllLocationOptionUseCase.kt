package io.github.faening.lello.core.domain.usecase.options.location

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.option.LocationOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllLocationOptionUseCase @Inject constructor(
    private val repository: OptionRepository<LocationOption>
) {
    operator fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<LocationOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }
}
