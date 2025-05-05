package io.github.faening.lello.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.EmotionOption

@Entity(tableName = "emotion_options")
data class EmotionOptionEntity(
    @PrimaryKey(autoGenerate = true) override val id: Int,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun EmotionOptionEntity.toModel(): EmotionOption {
    return EmotionOption(
        id = id,
        description = description,
        blocked = blocked,
        active = active
    )
}

fun EmotionOption.toEntity(): EmotionOptionEntity {
    return EmotionOptionEntity(
        id = id ?: 0,
        description = description,
        blocked = blocked,
        active = active
    )
}