package io.github.faening.lello.core.domain.repository

/**
 * Defines access operations for the mascot status table.
 */
interface MascotStatusResource<T> {
    suspend fun getStatus(): T?
    suspend fun insertOrUpdate(status: T)
}

