package io.github.faening.lello.core.domain.usecase.options.portion

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.PortionOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPortionOptionByIdUseCase @Inject constructor(
    private val repository: OptionRepository<PortionOption>
) {
    operator fun invoke(id: Long): Flow<PortionOption>? {
        id.validateId()
        return repository.getById(id)
    }
}
