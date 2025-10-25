package io.github.faening.lello.core.domain.usecase.options.portion

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.option.PortionOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllPortionOptionUseCase @Inject constructor(
    private val repository: OptionRepository<PortionOption>
) {
    operator fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<PortionOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }
}
