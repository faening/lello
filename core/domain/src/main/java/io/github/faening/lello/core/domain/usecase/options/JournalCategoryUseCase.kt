package io.github.faening.lello.core.domain.usecase.options

import io.github.faening.lello.core.domain.repository.JournalCategoryResources
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.model.journal.JournalCategory
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class JournalCategoryUseCase @Inject constructor(
    private val repository: JournalCategoryResources<JournalCategory>
) {

    fun getAll(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<JournalCategory>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }

    fun getById(id: Long): Flow<JournalCategory>? {
        id.validateId()
        return repository.getById(id)
    }
}