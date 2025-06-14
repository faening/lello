package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.JournalCategoryDao
import io.github.faening.lello.core.database.model.journal.toModel
import io.github.faening.lello.core.domain.repository.JournalCategoryResources
import io.github.faening.lello.core.model.journal.JournalCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class JournalCategoryRepository @Inject constructor(
    private val journalCategoryDao: JournalCategoryDao
) : JournalCategoryResources<JournalCategory> {

    override fun getAll(
        useBlockedFilter: Boolean,
        isBlocked: Boolean,
        useActiveFilter: Boolean,
        isActive: Boolean
    ): Flow<List<JournalCategory>> {
        return journalCategoryDao
            .getAll(
                useBlockedFilter = useBlockedFilter,
                isBlocked = isBlocked,
                useActiveFilter = useActiveFilter,
                isActive = isActive
            )
            .map { list -> list.map { it.toModel() } }
    }

    override fun getById(id: Long): Flow<JournalCategory>? {
        return journalCategoryDao.getById(id)?.map { it.toModel() }
    }
}