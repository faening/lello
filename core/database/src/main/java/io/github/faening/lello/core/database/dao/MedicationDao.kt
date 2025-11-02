package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.github.faening.lello.core.database.model.medication.MedicationDosageEntity
import io.github.faening.lello.core.database.model.medication.MedicationEntity
import io.github.faening.lello.core.database.model.medication.MedicationEntityWithOptions
import io.github.faening.lello.core.domain.repository.MedicationRepository

@Suppress("unused")
@Dao
interface MedicationDao : MedicationRepository<MedicationEntity> {

    @Transaction
    @Query(
        value = """
            SELECT * FROM medications
        """,
    )
    override suspend fun getAll(): List<MedicationEntity>

    @Transaction
    @Query(
        value = """
        SELECT * FROM medications
    """,
    )
    suspend fun getAllWithOptions(): List<MedicationEntityWithOptions>

    @Transaction
    @Query(
        value = """
            SELECT * FROM medications
            WHERE medicationId = :id
            LIMIT 1
        """,
    )
    override suspend fun getById(id: Long): MedicationEntity?

    @Transaction
    @Query(
        value = """
        SELECT * FROM medications
        WHERE medicationId = :id
        LIMIT 1
    """,
    )
    suspend fun getByIdWithOptions(id: Long): MedicationEntityWithOptions?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(entry: MedicationEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDosages(dosages: List<MedicationDosageEntity>)

    @Update
    override suspend fun update(entry: MedicationEntity)

    @Query(
        value = """
        UPDATE medication_dosages
        SET active = 0
        WHERE medication_id = :medicationId
    """
    )
    suspend fun disableDosagesForMedication(medicationId: Long)

    @Transaction
    suspend fun disableMedicationWithDosages(medicationId: Long) {
        disable(medicationId)
        disableDosagesForMedication(medicationId)
    }

    @Transaction
    @Query(
        value = """
            UPDATE medications
            SET active = 0
            WHERE medicationId = :id
        """,
    )
    override suspend fun disable(id: Long)
}