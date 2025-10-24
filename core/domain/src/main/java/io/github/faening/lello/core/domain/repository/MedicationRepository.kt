package io.github.faening.lello.core.domain.repository

interface MedicationRepository<T> {
    suspend fun getMedicationById(id: Long): T?
    suspend fun getMedicationByProductName(productName: String): T?
    suspend fun getMedicationByTherapeuticClass(therapeuticClass: String): List<T>
}