package io.github.faening.lello.core.domain.usecase.options.appetite

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.option.AppetiteOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllAppetiteOptionUseCase @Inject constructor(
    private val repository: OptionRepository<AppetiteOption>
) {
    operator fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<AppetiteOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }
}