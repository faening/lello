package io.github.faening.lello.core.domain.usecase.options.emotion

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.EmotionOption
import javax.inject.Inject

class UpdateEmotionOptionActiveStatusUseCase @Inject constructor(
    private val repository: OptionRepository<EmotionOption>
) {
    /**
     * Update only the active status for the provided options.
     */
    suspend operator fun invoke(vararg items: EmotionOption) {
        items.forEach { item -> item.id.validateId() }
        items.forEach { item -> repository.update(item) }
    }
}
