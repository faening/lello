package io.github.faening.lello.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface OptionResources<T> {
    fun getAll(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<T>>

    fun getById(id: Long): Flow<T>?

    suspend fun insert(item: T) : Long

    suspend fun update(item: T)

    suspend fun delete(item: T)
}