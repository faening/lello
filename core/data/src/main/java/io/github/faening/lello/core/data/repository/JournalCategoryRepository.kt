package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.JournalCategoryDao
import io.github.faening.lello.core.database.model.toModel
import io.github.faening.lello.core.model.journal.JournalCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class JournalCategoryRepository @Inject constructor(
    private val journalCategoryDao: JournalCategoryDao
) {

    fun getAll(): Flow<List<JournalCategory>> {
        return journalCategoryDao
            .getAll()
            .map { list -> list.map { it.toModel() } }
    }

    fun get(id: Int): Flow<JournalCategory>? {
        return journalCategoryDao
            .get(id)
            ?.map { it.toModel() }
    }
}