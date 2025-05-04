package io.github.faening.lello.core.domain.repository

import kotlinx.coroutines.flow.Flow

interface ReadOnlyRepository<T> {
    fun getAll(): Flow<List<T>>
    fun getById(id: Int): Flow<T>?
}