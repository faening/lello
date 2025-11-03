package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.github.faening.lello.core.database.model.option.MedicationDosageUnitOptionEntity
import io.github.faening.lello.core.domain.repository.OptionRepository
import kotlinx.coroutines.flow.Flow

@Suppress("unused")
@Dao
interface MedicationDosageUnitOptionDao : OptionRepository<MedicationDosageUnitOptionEntity> {

    @Transaction
    @Query(
        value = """
            SELECT * FROM medication_dosage_unit_options
            WHERE
                CASE WHEN :useBlockedFilter
                    THEN blocked = :isBlocked
                    ELSE 1 END
            AND
                CASE WHEN :useActiveFilter
                    THEN active = :isActive
                    ELSE 1 END
            ORDER BY description ASC
        """
    )
    override fun getAll(
        useBlockedFilter: Boolean,
        isBlocked: Boolean,
        useActiveFilter: Boolean,
        isActive: Boolean
    ): Flow<List<MedicationDosageUnitOptionEntity>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM medication_dosage_unit_options
            WHERE dosageUnitOptionId = :id
            LIMIT 1
        """
    )
    override fun getById(id: Long): Flow<MedicationDosageUnitOptionEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    override suspend fun insert(item: MedicationDosageUnitOptionEntity): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    override suspend fun update(item: MedicationDosageUnitOptionEntity)

    @Delete
    override suspend fun delete(item: MedicationDosageUnitOptionEntity)
}
