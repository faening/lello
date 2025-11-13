package io.github.faening.lello.core.database.dao

import androidx.room.Dao
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
import io.github.faening.lello.core.domain.repository.SleepJournalDaoContract
import kotlinx.coroutines.flow.Flow

@Dao
interface SleepJournalDao : SleepJournalDaoContract<SleepJournalEntityWithOptions, SleepJournalEntity> {

    @Transaction
    @Query("SELECT * FROM sleep_journals ORDER BY created_at DESC")
    override fun getAllJournals(): Flow<List<SleepJournalEntityWithOptions>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM sleep_journals
            WHERE sleepJournalId = :id
            LIMIT 1
        """
    )
    override fun getJournalById(id: Long): Flow<SleepJournalEntityWithOptions>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(item: SleepJournalEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSleepSensationRefs(refs: List<SleepJournalEntitySleepSensationOptionEntityCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSleepQualityRefs(refs: List<SleepJournalEntitySleepQualityOptionEntityCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSleepActivityRefs(refs: List<SleepJournalEntitySleepActivityOptionEntityCrossRef>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLocationRefs(refs: List<SleepJournalEntityLocationOptionEntityCrossRef>)

    @Insert
    override suspend fun insert(items: List<SleepJournalEntity>): List<Long>
}
