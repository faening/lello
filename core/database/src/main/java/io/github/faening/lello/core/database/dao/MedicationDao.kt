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
import io.github.faening.lello.core.domain.repository.MedicationDaoContract
import kotlinx.coroutines.flow.Flow

@Suppress("unused")
@Dao
interface MedicationDao :
    MedicationDaoContract<MedicationEntityWithOptions, MedicationEntity, MedicationDosageEntity> {

    @Transaction
    @Query(
        value = """
            SELECT * FROM medications
        """,
    )
    override fun getAllMedications(): Flow<List<MedicationEntityWithOptions>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM medications
            WHERE medicationId = :id
            LIMIT 1
        """,
    )
    override fun getMedicationById(id: Long): MedicationEntityWithOptions?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insert(item: MedicationEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun insertDosages(dosages: List<MedicationDosageEntity>)

    @Update
    override suspend fun update(item: MedicationEntity)

    @Transaction
    override suspend fun disable(medicationId: Long) {
        disableMedicationById(medicationId)
        disableDosagesForMedicationId(medicationId)
    }

    @Transaction
    @Query(
        value = """
            UPDATE medications
            SET active = 0
            WHERE medicationId = :id
        """,
    )
    suspend fun disableMedicationById(id: Long)

    @Query(
        value = """
            UPDATE medication_dosages
            SET active = 0
            WHERE medication_id = :medicationId
        """
    )
    suspend fun disableDosagesForMedicationId(medicationId: Long)

    @Query(
        value = """
            DELETE FROM medication_dosages
            WHERE medication_id = :medicationId
        """
    )
    override suspend fun deleteDosagesForMedicationId(medicationId: Long)
}