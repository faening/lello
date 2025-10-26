package io.github.faening.lello.core.model

import kotlinx.serialization.Serializable

@Serializable
data class AnvisaMedication(
    val id: Long,
    val productName: String,
    val registrationNumber: String?,
    val therapeuticClass: String?,
    val company: String?,
    val activeIngredient: String?
)