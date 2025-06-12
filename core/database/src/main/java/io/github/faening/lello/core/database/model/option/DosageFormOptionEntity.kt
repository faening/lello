package io.github.faening.lello.core.database.model.option

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.DosageFormOption

@Entity(tableName = "dosage_form_options")
data class DosageFormOptionEntity(
    @PrimaryKey(autoGenerate = true) val dosageFormOptionId: Long,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun DosageFormOptionEntity.toModel() = DosageFormOption(
    id = dosageFormOptionId,
    description = description,
    blocked = blocked,
    active = active
)

fun DosageFormOption.toEntity() = DosageFormOptionEntity(
    dosageFormOptionId = id,
    description = description,
    blocked = blocked,
    active = active
)
