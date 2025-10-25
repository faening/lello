package io.github.faening.lello.core.domain.usecase.options.sleep.sensation

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.SleepSensationOption
import javax.inject.Inject

class UpdateSleepSensationOptionActiveStatusUseCase @Inject constructor(
    private val repository: OptionRepository<SleepSensationOption>
) {
    suspend fun invoke(vararg items: SleepSensationOption) {
        items.forEach { item ->
            item.id.validateId()
        }
        items.forEach { item -> repository.update(item) }
    }
}