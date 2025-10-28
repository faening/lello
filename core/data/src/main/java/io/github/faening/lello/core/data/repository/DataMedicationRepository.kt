package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.MedicationDao
import io.github.faening.lello.core.database.model.medication.toEntity
import io.github.faening.lello.core.database.model.medication.toModel
import io.github.faening.lello.core.domain.repository.MedicationRepository
import io.github.faening.lello.core.model.medication.Medication
import jakarta.inject.Inject

class DataMedicationRepository @Inject constructor(
    private val dao : MedicationDao
): MedicationRepository<Medication> {

    override suspend fun getAll(): List<Medication> {
        return dao.getAllWithOptions().map { it.toModel() }
    }

    override suspend fun getById(id: Long): Medication? {
        return dao.getByIdWithOptions(id)?.toModel()
    }

    override suspend fun insert(entry: Medication): Long {
        return dao.insert(entry.toEntity())
    }

    override suspend fun update(entry: Medication) {
        dao.update(entry.toEntity())
    }

    override suspend fun disable(id: Long) {
        dao.disable(id)
    }
}