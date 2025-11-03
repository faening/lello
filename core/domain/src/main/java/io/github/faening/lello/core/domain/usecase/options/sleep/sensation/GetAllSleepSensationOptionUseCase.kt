package io.github.faening.lello.core.domain.usecase.options.sleep.sensation

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.option.SleepSensationOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSleepSensationOptionUseCase @Inject constructor(
    private val repository: OptionRepository<SleepSensationOption>
) {
    fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<SleepSensationOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }
}