package io.github.faening.lello.core.domain.repository

interface AnvisaMedicationRepository<T> {
    suspend fun getAll(): List<T>
    suspend fun getById(id: Long): T?
    suspend fun getByProductName(productName: String): List<T>?
    suspend fun getByTherapeuticClass(therapeuticClass: String): List<T>?
    suspend fun getByActiveIngredient(activeIngredient: String): List<T>?
}