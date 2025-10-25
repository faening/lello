package io.github.faening.lello.core.domain.usecase.options.emotion

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.EmotionOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetEmotionOptionByIdUseCase @Inject constructor(
    private val repository: OptionRepository<EmotionOption>
) {
    operator fun invoke(id: Long): Flow<EmotionOption>? {
        id.validateId()
        return repository.getById(id)
    }
}
