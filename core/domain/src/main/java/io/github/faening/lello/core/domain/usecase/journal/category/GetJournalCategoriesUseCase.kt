package io.github.faening.lello.core.domain.usecase.journal.category

import io.github.faening.lello.core.domain.repository.JournalCategoryRepository
import io.github.faening.lello.core.model.journal.JournalCategory
import javax.inject.Inject

class GetJournalCategoriesUseCase @Inject constructor(
    private val repository: JournalCategoryRepository<JournalCategory>
) {
    operator fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ) = repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
}