package io.github.faening.lello.core.domain.repository

interface WritableRepository<T> {
    suspend fun insert(vararg entities: T)
    suspend fun update(vararg entities: T)
    suspend fun delete(vararg entities: T)
}