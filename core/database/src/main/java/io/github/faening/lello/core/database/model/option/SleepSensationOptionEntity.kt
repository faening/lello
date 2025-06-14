package io.github.faening.lello.core.database.model.option

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.option.SleepSensationOption

@Entity(tableName = "sleep_sensation_options")
data class SleepSensationOptionEntity(
    @PrimaryKey(autoGenerate = true) val sleepSensationOptionId: Long,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun SleepSensationOptionEntity.toModel() = SleepSensationOption(
    id = sleepSensationOptionId,
    description = description,
    blocked = blocked,
    active = active
)

fun SleepSensationOption.toEntity() = SleepSensationOptionEntity(
    sleepSensationOptionId = id,
    description = description,
    blocked = blocked,
    active = active
)