package io.github.faening.lello.core.domain.usecase.options.health

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.option.HealthOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllHealthOptionUseCase @Inject constructor(
    private val repository: OptionRepository<HealthOption>
) {
    operator fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<HealthOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }
}
