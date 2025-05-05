package io.github.faening.lello.core.domain.usecase

import io.github.faening.lello.core.domain.R
import io.github.faening.lello.core.domain.repository.JournalCategoryResources
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

    fun getById(id: Int): Flow<JournalCategory>? {
        if (id <= 0) {
            val message = R.string.exception_invalid_id.toString()
            throw IllegalArgumentException(message)
        }

        return repository.getById(id)
    }
}