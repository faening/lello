package io.github.faening.lello.core.database.model.mascot

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.mascot.MascotStatus

@Entity(tableName = "mascot_status")
data class MascotStatusEntity(
    @PrimaryKey
    val id: Int = 1,
    val vitality: Int,
    @ColumnInfo(name = "last_updated_at")
    val lastUpdatedAt: Long
)

fun MascotStatusEntity.toModel() = MascotStatus(
    id = id,
    vitality = vitality,
    lastUpdatedAt = lastUpdatedAt
)

fun MascotStatus.toEntity() = MascotStatusEntity(
    id = id,
    vitality = vitality,
    lastUpdatedAt = lastUpdatedAt
)
