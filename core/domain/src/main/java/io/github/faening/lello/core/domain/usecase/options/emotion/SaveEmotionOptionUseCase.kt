package io.github.faening.lello.core.domain.usecase.options.emotion

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.domain.util.validateDescription
import io.github.faening.lello.core.model.option.EmotionOption
import javax.inject.Inject

class SaveEmotionOptionUseCase @Inject constructor(
    private val repository: OptionRepository<EmotionOption>
) {
    suspend operator fun invoke(vararg items: EmotionOption) {
        val formattedItems = items.map { item ->
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.insert(item) }
    }
}
