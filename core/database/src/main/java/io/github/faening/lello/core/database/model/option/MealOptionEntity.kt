package io.github.faening.lello.core.database.model.option

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.option.MealOption

@Entity(tableName = "meal_options")
data class MealOptionEntity(
    @PrimaryKey(autoGenerate = true) val mealOptionId: Long,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun MealOptionEntity.toModel() = MealOption(
    id = mealOptionId,
    description = description,
    blocked = blocked,
    active = active,
)

fun MealOption.toEntity() = MealOptionEntity(
    mealOptionId = id,
    description = description,
    blocked = blocked,
    active = active,
)
