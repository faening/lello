package io.github.faening.lello.core.domain.usecase.options.sleep.quality

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.SleepQualityOption
import javax.inject.Inject

class UpdateSleepQualityOptionActiveStatusUseCase @Inject constructor(
    private val repository: OptionRepository<SleepQualityOption>
) {
    suspend fun invoke(vararg items: SleepQualityOption) {
        items.forEach { item ->
            item.id.validateId()
        }
        items.forEach { item -> repository.update(item) }
    }
}