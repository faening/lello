package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.github.faening.lello.core.database.model.MoodJournalEntryEntity
import io.github.faening.lello.core.database.model.MoodJournalEntryClimateOptionCrossRef
import io.github.faening.lello.core.database.model.MoodJournalEntryEmotionOptionCrossRef
import io.github.faening.lello.core.database.model.MoodJournalEntryHealthOptionCrossRef
import io.github.faening.lello.core.database.model.MoodJournalEntryLocationOptionCrossRef
import io.github.faening.lello.core.database.model.MoodJournalEntrySocialOptionCrossRef
import io.github.faening.lello.core.database.model.MoodJournalEntryWithOptions

@Dao
interface MoodJournalEntryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEntry(entry: MoodJournalEntryEntity): Long

    // CrossRef inserts
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmotionRefs(refs: List<MoodJournalEntryEmotionOptionCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertClimateRefs(refs: List<MoodJournalEntryClimateOptionCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocationRefs(refs: List<MoodJournalEntryLocationOptionCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSocialRefs(refs: List<MoodJournalEntrySocialOptionCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHealthRefs(refs: List<MoodJournalEntryHealthOptionCrossRef>)

    // Consulta uma entrada completa por id
    @Transaction
    @Query("SELECT * FROM mood_journal_entries WHERE id = :entryId")
    suspend fun getEntryWithOptions(entryId: Int): MoodJournalEntryWithOptions?

    // Consulta todas as entradas completas
    @Transaction
    @Query("SELECT * FROM mood_journal_entries ORDER BY date DESC")
    suspend fun getAllEntriesWithOptions(): List<MoodJournalEntryWithOptions>
}
