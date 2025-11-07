package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import io.github.faening.lello.core.database.model.journal.JournalCategoryEntity
import io.github.faening.lello.core.domain.repository.JournalCategoryDaoContract
import kotlinx.coroutines.flow.Flow

@Suppress("unused")
@Dao
interface JournalCategoryDao : JournalCategoryDaoContract<JournalCategoryEntity> {

    @Transaction
    @Query(
        value = """
            SELECT * FROM journal_categories
            WHERE 
                CASE WHEN :useBlockedFilter
                    THEN blocked = :isBlocked
                    ELSE 1 END
            AND
                CASE WHEN :useActiveFilter
                    THEN active = :isActive
                    ELSE 1 END
            ORDER BY name ASC
        """
    )
    override fun getAllJournalCategories(
        useBlockedFilter: Boolean,
        isBlocked: Boolean,
        useActiveFilter: Boolean,
        isActive: Boolean
    ): Flow<List<JournalCategoryEntity>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM journal_categories
            WHERE id = :id
            LIMIT 1
        """
    )
    override fun getJournalCategoryById(id: Long): Flow<JournalCategoryEntity>?
}