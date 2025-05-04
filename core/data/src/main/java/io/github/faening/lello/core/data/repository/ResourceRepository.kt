package io.github.faening.lello.core.data.repository

import kotlinx.coroutines.flow.Flow

interface ReadOnlyRepository<T> {
    fun getAll(): Flow<List<T>>
    fun getById(id: Int): Flow<T>?
}

interface WritableRepository<T> {
    suspend fun insert(vararg entities: T)
    suspend fun update(vararg entities: T)
    suspend fun delete(vararg entities: T)
}