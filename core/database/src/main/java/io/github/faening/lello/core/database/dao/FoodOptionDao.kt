package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.github.faening.lello.core.database.model.FoodOptionEntity
import io.github.faening.lello.core.domain.repository.OptionResources
import kotlinx.coroutines.flow.Flow

@Suppress("unused")
@Dao
interface FoodOptionDao : OptionResources<FoodOptionEntity> {

    @Transaction
    @Query(
        value = """
            SELECT * FROM food_options
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
    ): Flow<List<FoodOptionEntity>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM food_options
            WHERE foodOptionId = :id
            LIMIT 1
        """
    )
    override fun getById(id: Long): Flow<FoodOptionEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    override suspend fun insert(item: FoodOptionEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun update(item: FoodOptionEntity)

    @Delete
    override suspend fun delete(item: FoodOptionEntity)
}
