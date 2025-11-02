package io.github.faening.lello.core.database.model.medication

import androidx.room.Embedded
import androidx.room.Relation
import io.github.faening.lello.core.database.model.option.MedicationActiveIngredientOptionEntity
import io.github.faening.lello.core.database.model.option.MedicationDosageFormOptionEntity
import io.github.faening.lello.core.database.model.option.toModel
import io.github.faening.lello.core.model.medication.Medication

data class MedicationEntityWithOptions(
    @Embedded val medication: MedicationEntity,
    @Relation(
        parentColumn = "active_ingredient_option_id",
        entityColumn = "activeIngredientOptionId"
    )
    val activeIngredientOption: MedicationActiveIngredientOptionEntity?,
    @Relation(
        parentColumn = "dosage_form_option_id",
        entityColumn = "dosageFormOptionId"
    )
    val dosageFormOption: MedicationDosageFormOptionEntity?,
    @Relation(
        parentColumn = "medicationId",
        entityColumn = "medication_id"
    )
    val dosages: List<MedicationDosageEntity>
)

fun MedicationEntityWithOptions.toModel() = Medication(
    id = medication.medicationId,
    activeIngredientOption = activeIngredientOption?.toModel(),
    dosageFormOption = dosageFormOption?.toModel(),
    dosages = dosages.map { it.toModel() },
    active = medication.active,
    createdAt = medication.createdAt,
    updatedAt = medication.updatedAt,
)