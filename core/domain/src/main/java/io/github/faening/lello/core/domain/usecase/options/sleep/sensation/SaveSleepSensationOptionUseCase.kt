package io.github.faening.lello.core.domain.usecase.options.sleep.sensation

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.domain.util.validateDescription
import io.github.faening.lello.core.model.option.SleepSensationOption
import javax.inject.Inject

class SaveSleepSensationOptionUseCase @Inject constructor(
    private val repository: OptionRepository<SleepSensationOption>
) {
    suspend fun invoke(vararg items: SleepSensationOption) {
        val formattedItems = items.map { item ->
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.insert(item) }
    }
}