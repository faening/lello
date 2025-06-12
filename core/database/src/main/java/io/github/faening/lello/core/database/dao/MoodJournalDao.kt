package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import io.github.faening.lello.core.database.model.MoodJournalEntity
import io.github.faening.lello.core.database.model.MoodJournalEntityClimateOptionEntityCrossRef
import io.github.faening.lello.core.database.model.MoodJournalEntityEmotionOptionEntityCrossRef
import io.github.faening.lello.core.database.model.MoodJournalEntityHealthOptionEntityCrossRef
import io.github.faening.lello.core.database.model.MoodJournalEntityLocationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.MoodJournalEntitySocialOptionEntityCrossRef
import io.github.faening.lello.core.domain.repository.JournalMoodResources

@Dao
interface MoodJournalDao : JournalMoodResources<MoodJournalEntity> {

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

//    // Consulta uma entrada completa por id
//    @Transaction
//    @Query("SELECT * FROM mood_journal_entries WHERE id = :entryId")
//    suspend fun getEntryWithOptions(entryId: Int): MoodJournalEntityWithOptions?
//
//    // Consulta todas as entradas completas
//    @Transaction
//    @Query("SELECT * FROM mood_journal_entries ORDER BY date DESC")
//    suspend fun getAllEntriesWithOptions(): List<MoodJournalEntityWithOptions>
}
