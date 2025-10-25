package io.github.faening.lello.core.domain.usecase.options.location

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.LocationOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetLocationOptionByIdUseCase @Inject constructor(
    private val repository: OptionRepository<LocationOption>
) {
    operator fun invoke(id: Long): Flow<LocationOption>? {
        id.validateId()
        return repository.getById(id)
    }
}
