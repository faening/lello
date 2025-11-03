package io.github.faening.lello.core.domain.usecase.options.social

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.option.SocialOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllSocialOptionUseCase @Inject constructor(
    private val repository: OptionRepository<SocialOption>
) {
    operator fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<SocialOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }
}
