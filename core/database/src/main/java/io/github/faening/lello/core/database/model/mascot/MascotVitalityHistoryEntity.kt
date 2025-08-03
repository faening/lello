package io.github.faening.lello.core.database.model.mascot

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.mascot.MascotVitalityHistory

@Entity(tableName = "mascot_vitality_history")
data class MascotVitalityHistoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0L,
    @ColumnInfo(name = "change_type")
    val changeType: String,
    val value: Int,
    val delta: Int,
    val source: String,
    @ColumnInfo(name = "created_at")
    val createdAt: Long
)

fun MascotVitalityHistoryEntity.toModel() = MascotVitalityHistory(
    id = id,
    changeType = changeType,
    value = value,
    delta = delta,
    source = source,
    createdAt = createdAt
)

fun MascotVitalityHistory.toEntity() = MascotVitalityHistoryEntity(
    id = id,
    changeType = changeType,
    value = value,
    delta = delta,
    source = source,
    createdAt = createdAt
)
