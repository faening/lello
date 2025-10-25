package io.github.faening.lello.core.domain.usecase.options.sleep.activity

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.option.SleepActivityOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSleepActivityOptionUseCase @Inject constructor(
    private val repository: OptionRepository<SleepActivityOption>
) {
    fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<SleepActivityOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }
}