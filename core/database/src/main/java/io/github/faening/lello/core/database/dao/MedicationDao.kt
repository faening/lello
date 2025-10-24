package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import io.github.faening.lello.core.database.model.medication.MedicationEntity
import io.github.faening.lello.core.domain.repository.store.MedicationRepository

@Suppress("unused")
@Dao
interface MedicationDao : MedicationRepository<MedicationEntity> {

    @Transaction
    @Query(
        value = """
            SELECT * FROM medication
            WHERE id = :id
            LIMIT 1
        """,
    )
    override suspend fun getMedicationById(id: Long): MedicationEntity?

    @Transaction
    @Query(
        value = """
            SELECT * FROM medication
            WHERE LOWER(product_name) LIKE '%' || LOWER(:productName) || '%'
            LIMIT 1
        """,
    )
    override suspend fun getMedicationByProductName(productName: String): MedicationEntity?

    @Transaction
    @Query(
        value = """
            SELECT * FROM medication
            WHERE LOWER(therapeutic_class) LIKE '%' || LOWER(:therapeuticClass) || '%'
        """,
    )
    override suspend fun getMedicationByTherapeuticClass(therapeuticClass: String): List<MedicationEntity>
}