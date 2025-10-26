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
        """,
    )
    override suspend fun getAll(): List<AnvisaMedicationEntity>

    @Transaction
    @Query(
        value = """
            SELECT * FROM anvisa_medications
            WHERE id = :id
            LIMIT 1
        """,
    )
    override suspend fun getById(id: Long): AnvisaMedicationEntity?

    @Transaction
    @Query(
        value = """
            SELECT * FROM anvisa_medications
            WHERE LOWER(product_name) LIKE '%' || LOWER(:productName) || '%'
        """,
    )
    override suspend fun getByProductName(productName: String): List<AnvisaMedicationEntity>?

    @Transaction
    @Query(
        value = """
            SELECT * FROM anvisa_medications
            WHERE LOWER(therapeutic_class) LIKE '%' || LOWER(:therapeuticClass) || '%'
        """,
    )
    override suspend fun getByTherapeuticClass(therapeuticClass: String): List<AnvisaMedicationEntity>?

    @Transaction
    @Query(
        value = """
            SELECT * FROM anvisa_medications
            WHERE LOWER(active_ingredient) LIKE '%' || LOWER(:activeIngredient) || '%'
        """,
    )
    override suspend fun getByActiveIngredient(activeIngredient: String): List<AnvisaMedicationEntity>?
}