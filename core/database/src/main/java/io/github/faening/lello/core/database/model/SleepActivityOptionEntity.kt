package io.github.faening.lello.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.SleepActivityOption

@Entity(tableName = "sleep_activity_options")
data class SleepActivityOptionEntity(
    @PrimaryKey(autoGenerate = true) val sleepActivityOptionId: Long,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun SleepActivityOptionEntity.toModel() = SleepActivityOption(
    id = sleepActivityOptionId,
    description = description,
    blocked = blocked,
    active = active
)

fun SleepActivityOption.toEntity() = SleepActivityOptionEntity(
    sleepActivityOptionId = id,
    description = description,
    blocked = blocked,
    active = active
)
