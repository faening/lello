package io.github.faening.lello.core.database.model.option

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.option.SleepDurationOption

@Entity(tableName = "sleep_duration_options")
data class SleepDurationOptionEntity(
    @PrimaryKey(autoGenerate = true) val sleepDurationOptionId: Long,
    override val description: String,
    override val blocked: Boolean = false,
    override val active: Boolean = true
) : OptionEntity()

fun SleepDurationOptionEntity.toModel() = SleepDurationOption(
    id = sleepDurationOptionId,
    description = description,
    blocked = blocked,
    active = active
)

fun SleepDurationOption.toEntity() = SleepDurationOptionEntity(
    sleepDurationOptionId = id,
    description = description,
    blocked = blocked,
    active = active
)
