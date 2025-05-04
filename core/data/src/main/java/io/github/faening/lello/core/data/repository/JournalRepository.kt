package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.JournalDao
import io.github.faening.lello.core.database.model.toEntity
import io.github.faening.lello.core.database.model.toModel
import io.github.faening.lello.core.model.diary.Journal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class JournalRepository @Inject constructor(
    private val journalDao: JournalDao
) : ResourceRepository<Journal> {

    override fun getAll(): Flow<List<Journal>> {
        return journalDao
            .getAll()
            .map { list -> list.map { it.toModel() } }
    }

    override fun getById(id: Long): Flow<Journal>? {
        return journalDao
            .get(id)
            ?.map { it.toModel() }
    }

    override suspend fun insert(vararg entities: Journal) {
        entities.forEach { entity ->
            journalDao.insert(entity.toEntity())
        }
    }

    override suspend fun update(vararg entities: Journal) {
        entities.forEach { entity ->
            journalDao.update(entity.toEntity())
        }
    }

    override suspend fun delete(vararg entities: Journal) {
        entities.forEach { entity ->
            journalDao.delete(entity.toEntity())
        }
    }
}