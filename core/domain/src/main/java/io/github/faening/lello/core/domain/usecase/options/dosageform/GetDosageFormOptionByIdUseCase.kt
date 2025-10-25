package io.github.faening.lello.core.domain.usecase.options.dosageform

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.DosageFormOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetDosageFormOptionByIdUseCase @Inject constructor(
    private val repository: OptionRepository<DosageFormOption>
) {
    operator fun invoke(id: Long): Flow<DosageFormOption>? {
        id.validateId()
        return repository.getById(id)
    }
}
