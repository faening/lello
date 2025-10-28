package io.github.faening.lello.core.database.model.medication

import androidx.room.Embedded
import androidx.room.Relation
import io.github.faening.lello.core.database.model.option.MedicationActiveIngredientOptionEntity
import io.github.faening.lello.core.database.model.option.MedicationDosageFormOptionEntity
import io.github.faening.lello.core.database.model.option.MedicationDosageUnitOptionEntity
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
        parentColumn = "dosage_unit_option_id",
        entityColumn = "dosageUnitOptionId"
    )
    val dosageUnitOptionId: MedicationDosageUnitOptionEntity?
)

fun MedicationEntityWithOptions.toModel() = Medication(
    id = medication.medicationId,
    activeIngredientOption = activeIngredientOption?.toModel(),
    dosageFormOption = dosageFormOption?.toModel(),
    dosageUnitOption = dosageUnitOptionId?.toModel(),
    strengthValue = medication.strengthValue ?: 0.0,
    active = medication.active,
    createdAt = medication.createdAt,
    updatedAt = medication.updatedAt,
)