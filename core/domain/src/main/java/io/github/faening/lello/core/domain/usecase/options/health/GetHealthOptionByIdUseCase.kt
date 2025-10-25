package io.github.faening.lello.core.domain.usecase.options.health

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.HealthOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetHealthOptionByIdUseCase @Inject constructor(
    private val repository: OptionRepository<HealthOption>
) {
    operator fun invoke(id: Long): Flow<HealthOption>? {
        id.validateId()
        return repository.getById(id)
    }
}
