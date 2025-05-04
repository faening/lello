package io.github.faening.lello.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.diary.Emotion

@Entity(tableName = "emotions")
data class EmotionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    val word: String,
    val blocked: Boolean,
    val active: Boolean
)

fun EmotionEntity.toModel(): Emotion {
    return Emotion(
        id = id,
        word = word,
        blocked = blocked,
        active = active
    )
}

fun Emotion.toEntity(): EmotionEntity {
    return EmotionEntity(
        id = id ?: 0,
        word = word,
        blocked = blocked,
        active = active
    )
}