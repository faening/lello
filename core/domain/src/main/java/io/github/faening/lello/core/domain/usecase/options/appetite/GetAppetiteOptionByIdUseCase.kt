package io.github.faening.lello.core.domain.usecase.options.appetite

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.AppetiteOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppetiteOptionByIdUseCase @Inject constructor(
    private val repository: OptionRepository<AppetiteOption>
) {
    operator fun invoke(id: Long): Flow<AppetiteOption>? {
        id.validateId()
        return repository.getById(id)
    }
}