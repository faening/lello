package io.github.faening.lello.core.domain.usecase.options.sleep.activity

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.option.SleepActivityOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSleepActivityOptionByIdUseCase @Inject constructor(
    private val repository: OptionRepository<SleepActivityOption>
) {
    fun invoke(id: Long): Flow<SleepActivityOption>? {
        id.validateId()
        return repository.getById(id)
    }
}