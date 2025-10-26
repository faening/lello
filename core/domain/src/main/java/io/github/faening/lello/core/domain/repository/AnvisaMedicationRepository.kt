package io.github.faening.lello.core.domain.repository

interface AnvisaMedicationRepository<T> {
    suspend fun getMedicationById(id: Long): T?
    suspend fun getMedicationByProductName(productName: String): T?
    suspend fun getMedicationByTherapeuticClass(therapeuticClass: String): List<T>
}