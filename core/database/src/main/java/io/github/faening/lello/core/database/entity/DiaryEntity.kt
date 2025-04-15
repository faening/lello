package io.github.faening.lello.core.database.entity

import androidx.room.Entity
import io.github.faening.lello.core.database.util.UUIDCOnverters
import io.github.faening.lello.core.model.diary.Diary

@Entity(tableName = "diaries")
data class DiaryEntity(
    val name: String,
    val description: String,
    val locked: Boolean,
    val active: Boolean,
    val imageUrl: String,
) : BaseEntity()

fun DiaryEntity.asModel() = Diary(
    id = UUIDCOnverters().fromUUID(id).toString(),
    createdAt = createdAt,
    updatedAt = updatedAt,
    name = name,
    description = description,
    locked = locked,
    active = active,
    imageUrl = imageUrl
)