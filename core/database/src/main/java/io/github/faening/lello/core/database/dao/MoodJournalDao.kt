package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.github.faening.lello.core.database.model.MoodJournalEntity
import io.github.faening.lello.core.database.model.MoodJournalEntityClimateOptionEntityCrossRef
import io.github.faening.lello.core.database.model.MoodJournalEntityEmotionOptionEntityCrossRef
import io.github.faening.lello.core.database.model.MoodJournalEntityHealthOptionEntityCrossRef
import io.github.faening.lello.core.database.model.MoodJournalEntityLocationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.MoodJournalEntitySocialOptionEntityCrossRef
import io.github.faening.lello.core.domain.repository.MoodJournalResources

@Dao
interface MoodJournalDao : MoodJournalResources<MoodJournalEntity> {

    @Transaction
    @Query("SELECT * FROM mood_journals ORDER BY date DESC")
    override suspend fun getAll(): List<MoodJournalEntity>

    @Transaction
    @Query(
        value = """
            SELECT * FROM mood_journals
            WHERE moodJournalId = :id
            LIMIT 1
        """
    )
    override suspend fun getById(id: Long): MoodJournalEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(entry: MoodJournalEntity): Long

    // CrossRef inserts
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
    override suspend fun delete(id: MoodJournalEntity)
}
