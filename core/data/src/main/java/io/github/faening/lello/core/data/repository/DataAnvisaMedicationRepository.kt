package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.AnvisaMedicationDao
import io.github.faening.lello.core.database.model.medication.toModel
import io.github.faening.lello.core.domain.repository.AnvisaMedicationRepository
import io.github.faening.lello.core.model.AnvisaMedication
import jakarta.inject.Inject

class DataAnvisaMedicationRepository @Inject constructor(
    private val dao : AnvisaMedicationDao
): AnvisaMedicationRepository<AnvisaMedication> {

    override suspend fun getMedicationById(id: Long): AnvisaMedication? {
        return dao.getMedicationById(id)?.toModel()
    }

    override suspend fun getMedicationByProductName(productName: String): AnvisaMedication? {
        return dao.getMedicationByProductName(productName)?.toModel()
    }

    override suspend fun getMedicationByTherapeuticClass(therapeuticClass: String): List<AnvisaMedication> {
        return dao.getMedicationByTherapeuticClass(therapeuticClass).map { it.toModel() }
    }
}