package io.github.faening.lello.core.data.repository

import kotlinx.coroutines.flow.Flow

interface ResourceRepository<T> {
    fun getAll(): Flow<List<T>>
    fun getById(id: Long): Flow<T>?
    suspend fun insert(vararg entities: T)
    suspend fun update(vararg entities: T)
    suspend fun delete(vararg entities: T)
}