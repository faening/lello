package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntity
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityAppetiteOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityFoodOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityLocationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityMealOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityPortionOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntitySocialOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.meal.MealJournalEntityWithOptions
import io.github.faening.lello.core.domain.repository.JournalRepository
import kotlinx.coroutines.flow.Flow

@Dao
interface MealJournalDao : JournalRepository<MealJournalEntity> {

    @Transaction
    @Query("SELECT * FROM meal_journals ORDER BY created_at DESC")
    fun getAllWithOptions(): Flow<List<MealJournalEntityWithOptions>>

    @Transaction
    @Query("SELECT * FROM meal_journals ORDER BY meal_time DESC")
    override fun getAll(): Flow<List<MealJournalEntity>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM meal_journals
            WHERE mealJournalId = :id
            LIMIT 1
        """
    )
    override fun getById(id: Long): Flow<MealJournalEntity>?

    @Transaction
    @Query(
        value = """
            SELECT * FROM meal_journals
            WHERE mealJournalId = :id
            LIMIT 1
        """
    )
    fun getByIdWithOptions(id: Long): Flow<MealJournalEntityWithOptions>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(entry: MealJournalEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMealRefs(refs: List<MealJournalEntityMealOptionEntityCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAppetiteRefs(refs: List<MealJournalEntityAppetiteOptionEntityCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFoodRefs(refs: List<MealJournalEntityFoodOptionEntityCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPortionRefs(refs: List<MealJournalEntityPortionOptionEntityCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocationRefs(refs: List<MealJournalEntityLocationOptionEntityCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSocialRefs(refs: List<MealJournalEntitySocialOptionEntityCrossRef>)

    @Delete
    override suspend fun delete(id: MealJournalEntity)
}
