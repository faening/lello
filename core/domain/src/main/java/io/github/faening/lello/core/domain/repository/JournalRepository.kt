package io.github.faening.lello.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface JournalRepository<T> {
    fun getAll(): Flow<List<T>>
    fun getById(id: Long): Flow<T>?
    suspend fun insert(entry: T) : Long
    suspend fun delete(id: T)
}