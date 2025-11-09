package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import io.github.faening.lello.core.database.model.journal.medication.MedicationJournalEntity
import io.github.faening.lello.core.database.model.journal.medication.MedicationJournalEntityWithOptions
import io.github.faening.lello.core.domain.repository.MedicationJournalDaoContract
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicationJournalDao :
    MedicationJournalDaoContract<MedicationJournalEntityWithOptions, MedicationJournalEntity> {

    @Transaction
    @Query("SELECT * FROM medication_journals ORDER BY created_at DESC")
    override fun getAllJournals(): Flow<List<MedicationJournalEntityWithOptions>>

    @Transaction
    @Query(
        """
        SELECT * FROM medication_journals 
        WHERE scheduled_time >= :dayStart AND scheduled_time < :dayEnd 
        ORDER BY scheduled_time ASC
    """
    )
    override fun getJournalsByDay(dayStart: Long, dayEnd: Long): Flow<List<MedicationJournalEntityWithOptions>>

    @Transaction
    @Query(
        """
        SELECT * FROM medication_journals 
        WHERE taken = :taken 
        ORDER BY scheduled_time DESC
    """
    )
    override fun getJournalsByTakenStatus(taken: Boolean): Flow<List<MedicationJournalEntityWithOptions>>

    @Transaction
    @Query(
        """
        SELECT * FROM medication_journals 
        WHERE medication_id = :medicationId 
        ORDER BY scheduled_time DESC
    """
    )
    override fun getJournalsByMedication(medicationId: Long): Flow<List<MedicationJournalEntityWithOptions>>

    @Query(
        value = """
            SELECT medication_dosage_id 
            FROM medication_journals
            WHERE medication_id = :medicationId
            AND DATE(scheduled_time / 1000, 'unixepoch', 'localtime') = DATE(:currentDay / 1000, 'unixepoch', 'localtime')
        """
    )
    override suspend fun getRegisteredDosageIdsForToday(medicationId: Long, currentDay: Long): List<Long>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(item: MedicationJournalEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(items: List<MedicationJournalEntity>): List<Long>
}