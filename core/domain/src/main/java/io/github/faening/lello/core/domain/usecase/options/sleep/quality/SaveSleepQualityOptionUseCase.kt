package io.github.faening.lello.core.domain.usecase.options.sleep.quality

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.domain.util.validateDescription
import io.github.faening.lello.core.model.option.SleepQualityOption
import javax.inject.Inject

class SaveSleepQualityOptionUseCase @Inject constructor(
    private val repository: OptionRepository<SleepQualityOption>
) {
    suspend fun invoke(vararg items: SleepQualityOption) {
        val formattedItems = items.map { item ->
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.insert(item) }
    }
}