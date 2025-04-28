package io.github.faening.lello.core.data.repository

import kotlinx.coroutines.flow.Flow

interface ResourceRepository<T> {
    fun getAll(): Flow<List<T>>
    fun getById(id: Long): Flow<T>?
    suspend fun insert(entity: T): Long
    suspend fun update(entity: T)
    suspend fun delete(id: Long)
}