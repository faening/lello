package io.github.faening.lello.core.database.model.option

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.option.PortionOption

@Entity(tableName = "portion_options")
data class PortionOptionEntity(
    @PrimaryKey(autoGenerate = true) val portionOptionId: Long,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun PortionOptionEntity.toModel() = PortionOption(
    id = portionOptionId,
    description = description,
    blocked = blocked,
    active = active,
)

fun PortionOption.toEntity() = PortionOptionEntity(
    portionOptionId = id,
    description = description,
    blocked = blocked,
    active = active,
)

