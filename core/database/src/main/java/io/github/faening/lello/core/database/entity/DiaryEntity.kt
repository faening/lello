package io.github.faening.lello.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.diary.Diary
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

@Entity(tableName = "diaries")
data class DiaryEntity(
    @PrimaryKey(autoGenerate = true)
    override val id: Long,

    override val createdAt: Instant,
    override val updatedAt: Instant,
    val name: String,
    val description: String,
    val locked: Boolean,
    val active: Boolean,
    val imageUrl: String
) : BaseEntity()

fun DiaryEntity.toModel() = Diary(
    id = id,
    createdAt = createdAt,
    updatedAt = updatedAt,
    name = name,
    description = description,
    locked = locked,
    active = active,
    imageUrl = imageUrl
)

fun Diary.toEntity() = DiaryEntity(
    id = this.id ?: 0L,
    createdAt = this.createdAt ?: Clock.System.now(),
    updatedAt = this.updatedAt ?: Clock.System.now(),
    name = this.name,
    description = this.description,
    locked = this.locked,
    active = this.active,
    imageUrl = this.imageUrl
)