package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntity
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityClimateOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityEmotionOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityHealthOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityLocationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntitySocialOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.mood.MoodJournalEntityWithOptions
import io.github.faening.lello.core.domain.repository.MoodJournalDaoContract
import kotlinx.coroutines.flow.Flow

@Dao
interface MoodJournalDao : MoodJournalDaoContract<MoodJournalEntityWithOptions, MoodJournalEntity> {

    @Transaction
    @Query("SELECT * FROM mood_journals ORDER BY created_at DESC")
    override fun getAllJournals(): Flow<List<MoodJournalEntityWithOptions>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM mood_journals
            WHERE moodJournalId = :id
            LIMIT 1
        """
    )
    override fun getJournalById(id: Long): Flow<MoodJournalEntityWithOptions>?

    @Transaction
    @Query(
        value = """
            SELECT * FROM mood_journals
            WHERE mood = :mood
            ORDER BY created_at DESC
        """
    )
    override fun getJournalsByMood(mood: String): Flow<List<MoodJournalEntityWithOptions>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(item: MoodJournalEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(items: List<MoodJournalEntity>): List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmotionRefs(refs: List<MoodJournalEntityEmotionOptionEntityCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClimateRefs(refs: List<MoodJournalEntityClimateOptionEntityCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocationRefs(refs: List<MoodJournalEntityLocationOptionEntityCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSocialRefs(refs: List<MoodJournalEntitySocialOptionEntityCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHealthRefs(refs: List<MoodJournalEntityHealthOptionEntityCrossRef>)

    @Delete
    override suspend fun delete(item: MoodJournalEntity)
}