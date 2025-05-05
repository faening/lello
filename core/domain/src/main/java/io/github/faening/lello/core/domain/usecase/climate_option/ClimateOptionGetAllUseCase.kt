package io.github.faening.lello.core.domain.usecase.climate_option

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.journal.ClimateOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClimateOptionGetAllUseCase @Inject constructor(
    private val repository: OptionRepository<ClimateOption>
) {

    operator fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<ClimateOption>> = repository.getAll(
        useBlockedFilter = useBlockedFilter,
        isBlocked = isBlocked,
        useActiveFilter = useActiveFilter,
        isActive = isActive
    )
}