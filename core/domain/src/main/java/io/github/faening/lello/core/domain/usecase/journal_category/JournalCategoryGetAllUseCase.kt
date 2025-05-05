package io.github.faening.lello.core.domain.usecase.journal_category

import io.github.faening.lello.core.domain.repository.JournalCategoryResources
import io.github.faening.lello.core.model.journal.JournalCategory
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class JournalCategoryGetAllUseCase @Inject constructor(
    private val repository: JournalCategoryResources<JournalCategory>
) {

    operator fun invoke(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ) : Flow<List<JournalCategory>> {
        return repository.getAll(
            useBlockedFilter = useBlockedFilter,
            isBlocked = isBlocked,
            useActiveFilter = useActiveFilter,
            isActive = isActive
        )
    }
}