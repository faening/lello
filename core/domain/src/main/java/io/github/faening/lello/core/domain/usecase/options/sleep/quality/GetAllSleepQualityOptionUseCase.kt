package io.github.faening.lello.core.domain.usecase.options.sleep.quality

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.option.SleepQualityOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSleepQualityOptionUseCase @Inject constructor(
    private val repository: OptionRepository<SleepQualityOption>
) {
    fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<SleepQualityOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }
}