package io.github.faening.lello.core.domain.usecase.options.dosageform

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.option.DosageFormOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllDosageFormOptionUseCase @Inject constructor(
    private val repository: OptionRepository<DosageFormOption>
) {
    operator fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<DosageFormOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }
}
