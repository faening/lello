package io.github.faening.lello.core.domain.usecase.options.health

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.HealthOption
import javax.inject.Inject

class UpdateHealthOptionActiveStatusUseCase @Inject constructor(
    private val repository: OptionRepository<HealthOption>
) {
    /**
     * Update only the active status for the provided options.
     */
    suspend operator fun invoke(vararg items: HealthOption) {
        items.forEach { item -> item.id.validateId() }
        items.forEach { item -> repository.update(item) }
    }
}
