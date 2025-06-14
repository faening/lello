package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntity
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntityLocationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntitySleepActivityOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntitySleepQualityOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntitySleepSensationOptionEntityCrossRef
import io.github.faening.lello.core.database.model.journal.sleep.SleepJournalEntityWithOptions
import io.github.faening.lello.core.domain.repository.JournalResources

@Dao
interface SleepJournalDao : JournalResources<SleepJournalEntity> {

    @Transaction
    @Query("SELECT * FROM sleep_journals ORDER BY date DESC")
    override suspend fun getAll(): List<SleepJournalEntity>

    @Transaction
    @Query(
        value = """
            SELECT * FROM sleep_journals
            WHERE sleepJournalId = :id
            LIMIT 1
        """
    )
    override suspend fun getById(id: Long): SleepJournalEntity?

    @Transaction
    @Query(
        value = """
            SELECT * FROM sleep_journals
            WHERE sleepJournalId = :id
            LIMIT 1
        """
    )
    suspend fun getByIdWithOptions(id: Long): SleepJournalEntityWithOptions?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(entry: SleepJournalEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSleepSensationRefs(refs: List<SleepJournalEntitySleepSensationOptionEntityCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSleepQualityRefs(refs: List<SleepJournalEntitySleepQualityOptionEntityCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSleepActivityRefs(refs: List<SleepJournalEntitySleepActivityOptionEntityCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocationRefs(refs: List<SleepJournalEntityLocationOptionEntityCrossRef>)

    @Delete
    override suspend fun delete(id: SleepJournalEntity)
}
