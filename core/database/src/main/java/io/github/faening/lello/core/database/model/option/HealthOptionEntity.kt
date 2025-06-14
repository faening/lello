package io.github.faening.lello.core.database.model.option

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.option.HealthOption

@Entity(tableName = "health_options")
data class HealthOptionEntity(
    @PrimaryKey(autoGenerate = true) val healthOptionId: Long,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun HealthOptionEntity.toModel(): HealthOption {
    return HealthOption(
        id = healthOptionId,
        description = description,
        blocked = blocked,
        active = active
    )
}

fun HealthOption.toEntity(): HealthOptionEntity {
    return HealthOptionEntity(
        healthOptionId = id,
        description = description,
        blocked = blocked,
        active = active
    )
}