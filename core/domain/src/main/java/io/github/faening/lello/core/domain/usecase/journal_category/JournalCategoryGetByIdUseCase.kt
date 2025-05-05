package io.github.faening.lello.core.domain.usecase.journal_category

import io.github.faening.lello.core.domain.repository.JournalCategoryResources
import io.github.faening.lello.core.model.journal.JournalCategory
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class JournalCategoryGetByIdUseCase @Inject constructor(
    private val repository: JournalCategoryResources<JournalCategory>
) {
    operator fun invoke(id: Int) : Flow<JournalCategory>? {
        if (id <= 0) {
            throw IllegalArgumentException("O ID não pode ser nulo ou menor que 1")
        }

        return repository.getById(id)
    }
}