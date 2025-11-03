package io.github.faening.lello.core.database.model.option

import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption

@Entity(tableName = "medication_active_ingredient_options")
data class MedicationActiveIngredientOptionEntity(
    @PrimaryKey(autoGenerate = true) val activeIngredientOptionId: Long,
    override val description: String,
    override val blocked: Boolean,
    override val active: Boolean
) : OptionEntity()

fun MedicationActiveIngredientOptionEntity.toModel() = MedicationActiveIngredientOption(
    id = activeIngredientOptionId,
    description = description,
    blocked = blocked,
    active = active
)

fun MedicationActiveIngredientOption.toEntity() = MedicationActiveIngredientOptionEntity(
    activeIngredientOptionId = id,
    description = description,
    blocked = blocked,
    active = active
)