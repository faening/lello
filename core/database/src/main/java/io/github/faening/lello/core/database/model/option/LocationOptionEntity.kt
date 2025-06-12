package io.github.faening.lello.core.database.model.option

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.LocationOption

@Entity(tableName = "location_options")
data class LocationOptionEntity(
    @PrimaryKey(autoGenerate = true) val locationOptionId: Long,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun LocationOptionEntity.toModel() = LocationOption(
    id = locationOptionId,
    description = description,
    blocked = blocked,
    active = active
)

fun LocationOption.toEntity() = LocationOptionEntity(
    locationOptionId = id,
    description = description,
    blocked = blocked,
    active = active
)