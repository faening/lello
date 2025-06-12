package io.github.faening.lello.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.AppetiteOption

@Entity(tableName = "appetite_options")
data class AppetiteOptionEntity(
    @PrimaryKey(autoGenerate = true) val appetiteOptionId: Long,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun AppetiteOptionEntity.toModel() = AppetiteOption(
    id = appetiteOptionId,
    description = description,
    blocked = blocked,
    active = active
)

fun AppetiteOption.toEntity() = AppetiteOptionEntity(
    appetiteOptionId = id,
    description = description,
    blocked = blocked,
    active = active
)
