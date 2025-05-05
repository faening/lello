package io.github.faening.lello.core.domain.usecase.climate_option

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.model.journal.ClimateOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClimateOptionGetByIdUseCase @Inject constructor(
    private val repository: OptionRepository<ClimateOption>
) {
    operator fun invoke(id: Int): Flow<ClimateOption>? {
        if (id <= 0) {
            throw IllegalArgumentException("O ID não pode ser nulo ou menor que 1")
        }

        return repository.getById(id)
    }
}