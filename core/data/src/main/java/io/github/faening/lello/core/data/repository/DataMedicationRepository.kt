package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.MedicationDao
import io.github.faening.lello.core.database.model.medication.toEntity
import io.github.faening.lello.core.database.model.medication.toModel
import io.github.faening.lello.core.domain.repository.MedicationRepository
import io.github.faening.lello.core.model.medication.Medication
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataMedicationRepository @Inject constructor(
    private val dao: MedicationDao
) : MedicationRepository<Medication> {

    override fun getAllMedications(): Flow<List<Medication>> {
        return dao.getAllMedications().map { entities -> entities.map { it.toModel() } }
    }

    override fun getMedicationById(id: Long): Medication? {
        return dao.getMedicationById(id)?.toModel()
    }

    override suspend fun insert(item: Medication): Long {
        val medicationId = dao.insert(item.toEntity())
        val dosageEntities = item.dosages.map { it.toEntity(medicationId) }
        dao.insertDosages(dosageEntities)
        return medicationId
    }

    override suspend fun update(item: Medication) {
        dao.update(item.toEntity())

        // Remove todas as dosagens antigas do medicamento
        dao.deleteDosagesForMedicationId(item.id ?: 0L)

        // Insere as novas dosagens
        val dosageEntities = item.dosages.map { it.toEntity(item.id ?: 0L) }
        dao.insertDosages(dosageEntities)
    }

    override suspend fun disable(id: Long) {
        dao.disable(id)
    }
}