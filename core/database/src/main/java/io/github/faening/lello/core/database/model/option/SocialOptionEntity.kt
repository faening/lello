package io.github.faening.lello.core.database.model.option

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.option.SocialOption

@Entity(tableName = "social_options")
data class SocialOptionEntity(
    @PrimaryKey(autoGenerate = true) val socialOptionId: Long,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun SocialOptionEntity.toModel(): SocialOption {
    return SocialOption(
        id = socialOptionId,
        description = description,
        blocked = blocked,
        active = active
    )
}

fun SocialOption.toEntity(): SocialOptionEntity {
    return SocialOptionEntity(
        socialOptionId = id ?: 0,
        description = description,
        blocked = blocked,
        active = active
    )
}