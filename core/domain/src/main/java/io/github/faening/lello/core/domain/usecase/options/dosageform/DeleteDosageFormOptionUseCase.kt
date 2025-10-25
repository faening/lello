package io.github.faening.lello.core.domain.usecase.options.dosageform

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.domain.util.validateNotBlocked
import io.github.faening.lello.core.model.option.DosageFormOption
import javax.inject.Inject

class DeleteDosageFormOptionUseCase @Inject constructor(
    private val repository: OptionRepository<DosageFormOption>
) {
    suspend operator fun invoke(vararg items: DosageFormOption) {
        items.forEach { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
        }
        items.forEach { item -> repository.delete(item) }
    }
}
