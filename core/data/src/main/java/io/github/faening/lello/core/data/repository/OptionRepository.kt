package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.domain.repository.OptionResources
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

abstract class OptionRepository<M, E>(
    private val dao: OptionResources<E>
) : OptionResources<M> {

    abstract fun E.toModel(): M
    abstract fun M.toEntity(): E

    override fun getAll(
        useBlockedFilter: Boolean,
        isBlocked: Boolean,
        useActiveFilter: Boolean,
        isActive: Boolean
    ): Flow<List<M>> {
        return dao.getAll().map { list -> list.map { it.toModel() } }
    }

    override fun getById(id: Long): Flow<M>? {
        return dao.getById(id)?.map { it.toModel() }
    }

    override suspend fun insert(item: M): Long {
        return dao.insert(item.toEntity())
    }

    override suspend fun update(item: M) {
        dao.update(item.toEntity())
    }

    override suspend fun delete(item: M) {
        dao.delete(item.toEntity())
    }
}