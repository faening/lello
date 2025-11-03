package io.github.faening.lello.core.database.model.option

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.option.MedicationDosageFormOption

@Entity(tableName = "medication_dosage_form_options")
data class MedicationDosageFormOptionEntity(
    @PrimaryKey(autoGenerate = true) val dosageFormOptionId: Long,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun MedicationDosageFormOptionEntity.toModel() = MedicationDosageFormOption(
    id = dosageFormOptionId,
    description = description,
    blocked = blocked,
    active = active
)

fun MedicationDosageFormOption.toEntity() = MedicationDosageFormOptionEntity(
    dosageFormOptionId = id,
    description = description,
    blocked = blocked,
    active = active
)
