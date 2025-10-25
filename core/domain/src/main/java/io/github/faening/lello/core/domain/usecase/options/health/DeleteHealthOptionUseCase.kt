package io.github.faening.lello.core.domain.usecase.options.health

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.domain.util.validateNotBlocked
import io.github.faening.lello.core.model.option.HealthOption
import javax.inject.Inject

class DeleteHealthOptionUseCase @Inject constructor(
    private val repository: OptionRepository<HealthOption>
) {
    suspend operator fun invoke(vararg items: HealthOption) {
        items.forEach { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
        }
        items.forEach { item -> repository.delete(item) }
    }
}
