package io.github.faening.lello.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.diary.Climate

@Entity(tableName = "climates")
data class ClimateEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "weather_type") val weatherType: String,
    val blocked: Boolean,
    val active: Boolean
)

fun ClimateEntity.toModel() : Climate {
    return Climate(
        id = id,
        weatherType = weatherType,
        blocked = blocked,
        active = active
    )
}

fun Climate.toEntity() : ClimateEntity {
    return ClimateEntity(
        id = id ?: 0,
        weatherType = weatherType,
        blocked = blocked,
        active = active
    )
}