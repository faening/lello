package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.MedicationDao
import io.github.faening.lello.core.database.model.medication.toModel
import io.github.faening.lello.core.domain.repository.MedicationRepository
import io.github.faening.lello.core.model.Medication
import jakarta.inject.Inject

class DataMedicationRepository @Inject constructor(
    private val dao : MedicationDao
): MedicationRepository<Medication> {

    override suspend fun getMedicationById(id: Long): Medication? {
        return dao.getMedicationById(id)?.toModel()
    }

    override suspend fun getMedicationByProductName(productName: String): Medication? {
        return dao.getMedicationByProductName(productName)?.toModel()
    }

    override suspend fun getMedicationByTherapeuticClass(therapeuticClass: String): List<Medication> {
        return dao.getMedicationByTherapeuticClass(therapeuticClass).map { it.toModel() }
    }
}