package io.github.faening.lello.core.database.model.option

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.option.SleepQualityOption

@Entity(tableName = "sleep_quality_options")
data class SleepQualityOptionEntity(
    @PrimaryKey(autoGenerate = true) val sleepQualityOptionId: Long,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun SleepQualityOptionEntity.toModel() = SleepQualityOption(
    id = sleepQualityOptionId,
    description = description,
    blocked = blocked,
    active = active
)

fun SleepQualityOption.toEntity() = SleepQualityOptionEntity(
    sleepQualityOptionId = id,
    description = description,
    blocked = blocked,
    active = active
)
