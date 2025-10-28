package io.github.faening.lello.core.model.medication

import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption
import io.github.faening.lello.core.model.option.MedicationDosageFormOption
import io.github.faening.lello.core.model.option.MedicationDosageUnitOption
import kotlinx.serialization.Serializable

@Serializable
data class Medication(
    val id: Long? = null,
    val activeIngredientOption: MedicationActiveIngredientOption?,
    val dosageFormOption: MedicationDosageFormOption?,
    val dosageUnitOption: MedicationDosageUnitOption?,
    val strengthValue: Double,
    val active: Boolean?,
    val createdAt: Long,
    val updatedAt: Long,
)