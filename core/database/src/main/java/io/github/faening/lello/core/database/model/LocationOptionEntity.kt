package io.github.faening.lello.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.database.model.base.OptionBaseEntity
import io.github.faening.lello.core.model.diary.LocationOption

@Entity(tableName = "location_options")
data class LocationOptionEntity(
    @PrimaryKey(autoGenerate = true) override val id: Int,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionBaseEntity()

fun LocationOption.toModel() = LocationOption(
    id = id,
    description = description,
    blocked = blocked,
    active = active
)

fun LocationOption.toEntity() = LocationOption(
    id = id ?: 0,
    description = description,
    blocked = blocked,
    active = active
)