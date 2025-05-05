package io.github.faening.lello.core.domain.usecase.climate_option

import io.github.faening.lello.core.domain.repository.OptionResources
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.model.journal.ClimateOption
import jakarta.inject.Inject

class ClimateOptionUpdateUseCase @Inject constructor(
    private val repository: OptionResources<ClimateOption>
) {
    suspend fun invoke(item: ClimateOption) {
        if (item.id == null) {
            throw IllegalArgumentException("O ID não pode ser nulo")
        }

        if (item.description.isEmpty()) {
            throw IllegalArgumentException("A descrição não pode estar vazia")
        }

        if (item.blocked == true) {
            throw IllegalArgumentException("Não é possível atualizar uma opção bloqueada")
        }

        item.copy(description = item.description.capitalizeFirst())

        repository.update(item)
    }
}