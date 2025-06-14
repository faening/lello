package io.github.faening.lello.core.database.model.option

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.option.FoodOption

@Entity(tableName = "food_options")
data class FoodOptionEntity(
    @PrimaryKey(autoGenerate = true) val foodOptionId: Long,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean,
) : OptionEntity()

fun FoodOptionEntity.toModel() = FoodOption(
    id = foodOptionId,
    description = description,
    blocked = blocked,
    active = active,
)

fun FoodOption.toEntity() = FoodOptionEntity(
    foodOptionId = id,
    description = description,
    blocked = blocked,
    active = active,
)
