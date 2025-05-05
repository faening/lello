package io.github.faening.lello.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.HealthOption

@Entity(tableName = "health_options")
data class HealthOptionEntity(
    @PrimaryKey(autoGenerate = true) override val id: Int,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun HealthOptionEntity.toModel(): HealthOption {
    return HealthOption(
        id = id,
        description = description,
        blocked = blocked,
        active = active
    )
}

fun HealthOption.toEntity(): HealthOptionEntity {
    return HealthOptionEntity(
        id = id ?: 0,
        description = description,
        blocked = blocked,
        active = active
    )
}