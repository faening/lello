package io.github.faening.lello.core.model.medication

import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import io.github.faening.lello.core.model.option.MedicationDosageFormOption
import kotlinx.serialization.Serializable

@Serializable
data class Medication(
    val id: Long? = null,
    val activeIngredientOption: MedicationActiveIngredientOption?,
    val dosageFormOption: MedicationDosageFormOption?,
    val dosages: List<MedicationDosage>,
    val active: Boolean?,
    val createdAt: Long,
    val updatedAt: Long,
)