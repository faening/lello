package io.github.faening.lello.core.database.model.medication

import androidx.room.Embedded
import androidx.room.Relation
import io.github.faening.lello.core.database.model.option.MedicationDosageUnitOptionEntity
import io.github.faening.lello.core.database.model.option.toModel
import io.github.faening.lello.core.model.medication.MedicationDosage
import java.time.LocalTime

data class MedicationDosageEntityWithOption(
    @Embedded val dosage: MedicationDosageEntity,
    @Relation(
        parentColumn = "unit_option_id",
        entityColumn = "dosageUnitOptionId"
    )
    val unitOption: MedicationDosageUnitOptionEntity?
)

fun MedicationDosageEntityWithOption.toModel() = MedicationDosage(
    id = dosage.medicationDosageId,
    dosageNumber = dosage.dosageNumber,
    quantity = dosage.quantity,
    unitOption = unitOption?.toModel(),
    time = LocalTime.parse(dosage.time),
    active = dosage.active ?: true
)