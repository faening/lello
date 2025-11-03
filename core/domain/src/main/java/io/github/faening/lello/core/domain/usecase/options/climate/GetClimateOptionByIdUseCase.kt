package io.github.faening.lello.core.domain.usecase.options.climate

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.ClimateOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetClimateOptionByIdUseCase @Inject constructor(
    private val repository: OptionRepository<ClimateOption>
) {
    operator fun invoke(id: Long): Flow<ClimateOption>? {
        id.validateId()
        return repository.getById(id)
    }
}
