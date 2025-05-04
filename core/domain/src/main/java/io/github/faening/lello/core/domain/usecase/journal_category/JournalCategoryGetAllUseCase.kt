package io.github.faening.lello.core.domain.usecase.journal_category

import io.github.faening.lello.core.domain.repository.ReadOnlyRepository
import io.github.faening.lello.core.model.journal.JournalCategory
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class JournalCategoryGetAllUseCase @Inject constructor(
    private val repository: ReadOnlyRepository<JournalCategory>
) {
    operator fun invoke() : Flow<List<JournalCategory>> = repository.getAll()
}