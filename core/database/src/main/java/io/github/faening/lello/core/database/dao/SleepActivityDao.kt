package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.github.faening.lello.core.database.model.SleepActivityOptionEntity
import io.github.faening.lello.core.domain.repository.OptionResources
import kotlinx.coroutines.flow.Flow

@Suppress("unused")
@Dao
interface SleepActivityDao : OptionResources<SleepActivityOptionEntity> {

    @Transaction
    @Query(
        value = """
            SELECT * FROM sleep_activity_options
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
        isActive: Boolean
    ): Flow<List<SleepActivityOptionEntity>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM sleep_activity_options
            WHERE sleepActivityOptionId = :id
            LIMIT 1
        """
    )
    override fun getById(id: Long): Flow<SleepActivityOptionEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    override suspend fun insert(item: SleepActivityOptionEntity): Long

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    override suspend fun update(item: SleepActivityOptionEntity)

    @Delete
    override suspend fun delete(item: SleepActivityOptionEntity)
}
