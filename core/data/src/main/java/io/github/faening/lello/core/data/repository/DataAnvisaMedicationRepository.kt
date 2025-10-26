package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.AnvisaMedicationDao
import io.github.faening.lello.core.database.model.medication.toModel
import io.github.faening.lello.core.domain.repository.AnvisaMedicationRepository
import io.github.faening.lello.core.model.AnvisaMedication
import jakarta.inject.Inject

class DataAnvisaMedicationRepository @Inject constructor(
    private val dao : AnvisaMedicationDao
): AnvisaMedicationRepository<AnvisaMedication> {

    override suspend fun getAll(): List<AnvisaMedication> {
        return dao.getAll().map { it.toModel() }
    }

    override suspend fun getById(id: Long): AnvisaMedication? {
        return dao.getById(id)?.toModel()
    }

    override suspend fun getByProductName(productName: String): List<AnvisaMedication>? {
        return dao.getByProductName(productName)?.map { it.toModel() } ?: emptyList()
    }

    override suspend fun getByTherapeuticClass(therapeuticClass: String): List<AnvisaMedication>? {
        return dao.getByTherapeuticClass(therapeuticClass)?.map { it.toModel() } ?: emptyList()
    }

    override suspend fun getByActiveIngredient(activeIngredient: String): List<AnvisaMedication>? {
        return dao.getByActiveIngredient(activeIngredient)?.map { it.toModel() } ?: emptyList()
    }
}