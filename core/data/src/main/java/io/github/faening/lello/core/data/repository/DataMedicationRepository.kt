package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.MedicationDao
import io.github.faening.lello.core.database.model.medication.toEntity
import io.github.faening.lello.core.database.model.medication.toModel
import io.github.faening.lello.core.domain.repository.MedicationRepository
import io.github.faening.lello.core.model.medication.Medication
import jakarta.inject.Inject

class DataMedicationRepository @Inject constructor(
    private val dao: MedicationDao
) : MedicationRepository<Medication> {

    override suspend fun getAll(): List<Medication> {
        return dao.getAllWithOptions().map { it.toModel() }
    }

    override suspend fun getById(id: Long): Medication? {
        return dao.getByIdWithOptions(id)?.toModel()
    }

    override suspend fun insert(entry: Medication): Long {
        val medicationId = dao.insert(entry.toEntity())
        val dosageEntities = entry.dosages.map { it.toEntity(medicationId) }
        dao.insertDosages(dosageEntities)
        return medicationId
    }

    override suspend fun update(entry: Medication) {
        dao.update(entry.toEntity())
        dao.disableDosagesForMedication(entry.id ?: 0L)
        val dosageEntities = entry.dosages.map { it.toEntity(entry.id ?: 0L) }
        dao.insertDosages(dosageEntities)
    }

    override suspend fun disable(id: Long) {
        dao.disableMedicationWithDosages(id)
    }
}