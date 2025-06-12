package io.github.faening.lello.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.SensationOption

@Entity(tableName = "sensation_options")
data class SensationOptionEntity(
    @PrimaryKey(autoGenerate = true) val sensationOptionId: Long,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun SensationOptionEntity.toModel() = SensationOption(
    id = sensationOptionId,
    description = description,
    blocked = blocked,
    active = active
)

fun SensationOption.toEntity() = SensationOptionEntity(
    sensationOptionId = id,
    description = description,
    blocked = blocked,
    active = active
)