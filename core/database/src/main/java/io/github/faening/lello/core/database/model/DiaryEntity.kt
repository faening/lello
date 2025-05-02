package io.github.faening.lello.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.diary.Diary
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

@Entity(tableName = "diaries")
data class DiaryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val name: String,
    val description: String,
    val locked: Boolean,
    val active: Boolean,
    val imageUrl: String
)

fun DiaryEntity.toModel() = Diary(
    id = id,
    name = name,
    description = description,
    locked = locked,
    active = active,
    imageUrl = imageUrl
)

fun Diary.toEntity() = DiaryEntity(
    id = this.id ?: 0,
    name = this.name,
    description = this.description,
    locked = this.locked,
    active = this.active,
    imageUrl = this.imageUrl
)