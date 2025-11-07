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
import io.github.faening.lello.core.domain.repository.MealJournalDaoContract
import kotlinx.coroutines.flow.Flow

@Dao
interface MealJournalDao : MealJournalDaoContract<MealJournalEntityWithOptions, MealJournalEntity> {

    @Transaction
    @Query("SELECT * FROM meal_journals ORDER BY created_at DESC")
    override fun getAllJournals(): Flow<List<MealJournalEntityWithOptions>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM meal_journals
            WHERE mealJournalId = :id
            LIMIT 1
        """
    )
    override fun getJournalById(id: Long): Flow<MealJournalEntityWithOptions>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(item: MealJournalEntity): Long

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

    @Insert
    override suspend fun insert(items: List<MealJournalEntity>): List<Long>

    @Delete
    override suspend fun delete(item: MealJournalEntity)
}