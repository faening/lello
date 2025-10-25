package io.github.faening.lello.core.domain.usecase.journal.category

import io.github.faening.lello.core.domain.repository.JournalCategoryRepository
import io.github.faening.lello.core.model.journal.JournalCategory
import javax.inject.Inject

class GetJournalCategoryByIdUseCase @Inject constructor(
    private val repository: JournalCategoryRepository<JournalCategory>
) {
    operator fun invoke(id: Long) = repository.getById(id)
}