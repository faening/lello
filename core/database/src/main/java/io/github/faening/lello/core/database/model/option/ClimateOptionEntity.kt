package io.github.faening.lello.core.database.model.option

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.ClimateOption

@Entity(tableName = "climate_options")
data class ClimateOptionEntity(
    @PrimaryKey(autoGenerate = true) val climateOptionId: Long,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun ClimateOptionEntity.toModel() : ClimateOption {
    return ClimateOption(
        id = climateOptionId,
        description = description,
        blocked = blocked,
        active = active
    )
}

fun ClimateOption.toEntity() : ClimateOptionEntity {
    return ClimateOptionEntity(
        climateOptionId = id,
        description = description,
        blocked = blocked,
        active = active
    )
}