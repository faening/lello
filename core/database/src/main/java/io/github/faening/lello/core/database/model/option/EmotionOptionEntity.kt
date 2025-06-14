package io.github.faening.lello.core.database.model.option

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.option.EmotionOption

@Entity(tableName = "emotion_options")
data class EmotionOptionEntity(
    @PrimaryKey(autoGenerate = true) val emotionOptionId: Long,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun EmotionOptionEntity.toModel(): EmotionOption {
    return EmotionOption(
        id = emotionOptionId,
        description = description,
        blocked = blocked,
        active = active
    )
}

fun EmotionOption.toEntity(): EmotionOptionEntity {
    return EmotionOptionEntity(
        emotionOptionId = id,
        description = description,
        blocked = blocked,
        active = active
    )
}