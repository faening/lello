package io.github.faening.lello.core.database.model.option

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.option.MedicationDosageUnitOption

@Entity(tableName = "medication_dosage_unit_options")
data class MedicationDosageUnitOptionEntity(
    @PrimaryKey(autoGenerate = true) val dosageUnitOptionId: Long,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun MedicationDosageUnitOptionEntity.toModel() = MedicationDosageUnitOption(
    id = dosageUnitOptionId,
    description = description,
    blocked = blocked,
    active = active
)

fun MedicationDosageUnitOption.toEntity() = MedicationDosageUnitOptionEntity(
    dosageUnitOptionId = id,
    description = description,
    blocked = blocked,
    active = active
)
