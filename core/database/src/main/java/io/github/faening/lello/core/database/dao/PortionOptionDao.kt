package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.github.faening.lello.core.database.model.PortionOptionEntity
import io.github.faening.lello.core.domain.repository.OptionResources
import kotlinx.coroutines.flow.Flow

@Suppress("unused")
@Dao
interface PortionOptionDao : OptionResources<PortionOptionEntity> {

    @Transaction
    @Query(
        value = """
            SELECT * FROM portion_options
            WHERE
                CASE WHEN :useBlockedFilter
                    THEN blocked = :isBlocked
                    ELSE 1 END
            AND
                CASE WHEN :useActiveFilter
                    THEN active = :isActive
                    ELSE 1 END
            ORDER BY description ASC
        """
    )
    override fun getAll(
        useBlockedFilter: Boolean,
        isBlocked: Boolean,
        useActiveFilter: Boolean,
        isActive: Boolean,
    ): Flow<List<PortionOptionEntity>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM portion_options
            WHERE portionOptionId = :id
            LIMIT 1
        """
    )
    override fun getById(id: Long): Flow<PortionOptionEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    override suspend fun insert(item: PortionOptionEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun update(item: PortionOptionEntity)

    @Delete
    override suspend fun delete(item: PortionOptionEntity)
}

