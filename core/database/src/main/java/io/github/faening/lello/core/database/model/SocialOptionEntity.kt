package io.github.faening.lello.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.journal.SocialOption

@Entity(tableName = "social_options")
data class SocialOptionEntity(
    @PrimaryKey(autoGenerate = true) override val id: Int,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun SocialOptionEntity.toModel(): SocialOption {
    return SocialOption(
        id = id,
        description = description,
        blocked = blocked,
        active = active
    )
}

fun SocialOption.toEntity(): SocialOptionEntity {
    return SocialOptionEntity(
        id = id ?: 0,
        description = description,
        blocked = blocked,
        active = active
    )
}