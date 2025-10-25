package io.github.faening.lello.core.domain.usecase.options.location

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.domain.util.validateNotBlocked
import io.github.faening.lello.core.model.option.LocationOption
import javax.inject.Inject

class DeleteLocationOptionUseCase @Inject constructor(
    private val repository: OptionRepository<LocationOption>
) {
    suspend operator fun invoke(vararg items: LocationOption) {
        items.forEach { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
        }
        items.forEach { item -> repository.delete(item) }
    }
}
