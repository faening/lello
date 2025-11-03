package io.github.faening.lello.core.domain.usecase.options.sleep.activity

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.domain.util.validateNotBlocked
import io.github.faening.lello.core.model.option.SleepActivityOption
import javax.inject.Inject

class DeleteSleepActivityOptionUseCase @Inject constructor(
    private val repository: OptionRepository<SleepActivityOption>
) {
    suspend fun invoke(vararg items: SleepActivityOption) {
        items.forEach { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
        }
        items.forEach { item -> repository.delete(item) }
    }
}