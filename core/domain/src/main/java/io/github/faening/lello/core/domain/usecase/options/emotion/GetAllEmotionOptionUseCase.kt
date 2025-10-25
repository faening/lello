package io.github.faening.lello.core.domain.usecase.options.emotion

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.option.EmotionOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllEmotionOptionUseCase @Inject constructor(
    private val repository: OptionRepository<EmotionOption>
) {
    operator fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<EmotionOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }
}
