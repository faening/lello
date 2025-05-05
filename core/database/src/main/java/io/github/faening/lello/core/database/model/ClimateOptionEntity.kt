package io.github.faening.lello.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.ClimateOption

@Entity(tableName = "climate_options")
data class ClimateOptionEntity(
    @PrimaryKey(autoGenerate = true) override val id: Int,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun ClimateOptionEntity.toModel() : ClimateOption {
    return ClimateOption(
        id = id,
        description = description,
        blocked = blocked,
        active = active
    )
}

fun ClimateOption.toEntity() : ClimateOptionEntity {
    return ClimateOptionEntity(
        id = id ?: 0,
        description = description,
        blocked = blocked,
        active = active
    )
}