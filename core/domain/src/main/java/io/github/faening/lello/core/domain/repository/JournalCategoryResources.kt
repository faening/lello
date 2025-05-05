package io.github.faening.lello.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface JournalCategoryResources<T> {
    fun getAll(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<T>>

    fun getById(id: Int): Flow<T>?
}