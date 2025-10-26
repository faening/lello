package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import io.github.faening.lello.core.database.model.medication.AnvisaMedicationEntity
import io.github.faening.lello.core.domain.repository.AnvisaMedicationRepository

@Suppress("unused")
@Dao
interface AnvisaMedicationDao : AnvisaMedicationRepository<AnvisaMedicationEntity> {

    @Transaction
    @Query(
        value = """
            SELECT * FROM anvisa_medications
            WHERE id = :id
            LIMIT 1
        """,
    )
    override suspend fun getMedicationById(id: Long): AnvisaMedicationEntity?

    @Transaction
    @Query(
        value = """
            SELECT * FROM anvisa_medications
            WHERE LOWER(product_name) LIKE '%' || LOWER(:productName) || '%'
            LIMIT 1
        """,
    )
    override suspend fun getMedicationByProductName(productName: String): AnvisaMedicationEntity?

    @Transaction
    @Query(
        value = """
            SELECT * FROM anvisa_medications
            WHERE LOWER(therapeutic_class) LIKE '%' || LOWER(:therapeuticClass) || '%'
        """,
    )
    override suspend fun getMedicationByTherapeuticClass(therapeuticClass: String): List<AnvisaMedicationEntity>
}