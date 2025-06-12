package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.github.faening.lello.core.database.model.MealOptionEntity
import io.github.faening.lello.core.domain.repository.OptionResources
import kotlinx.coroutines.flow.Flow

@Suppress("unused")
@Dao
interface MealOptionDao : OptionResources<MealOptionEntity> {

    @Transaction
    @Query(
        value = """
            SELECT * FROM meal_options
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
    ): Flow<List<MealOptionEntity>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM meal_options
            WHERE mealOptionId = :id
            LIMIT 1
        """
    )
    override fun getById(id: Int): Flow<MealOptionEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    override suspend fun insert(item: MealOptionEntity)

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    override suspend fun update(item: MealOptionEntity)

    @Delete
    override suspend fun delete(item: MealOptionEntity)
}
