package io.github.faening.lello.core.domain.usecase.options.location

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.LocationOption
import javax.inject.Inject

class UpdateLocationOptionActiveStatusUseCase @Inject constructor(
    private val repository: OptionRepository<LocationOption>
) {
    /**
     * Update only the active status for the provided options.
     */
    suspend operator fun invoke(vararg items: LocationOption) {
        items.forEach { item -> item.id.validateId() }
        items.forEach { item -> repository.update(item) }
    }
}
