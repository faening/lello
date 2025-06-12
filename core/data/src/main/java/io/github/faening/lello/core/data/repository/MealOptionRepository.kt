package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.MealOptionDao
import io.github.faening.lello.core.database.model.option.MealOptionEntity
import io.github.faening.lello.core.model.journal.MealOption
import jakarta.inject.Inject

class MealOptionRepository @Inject constructor(
    dao: MealOptionDao
) : OptionRepository<MealOption, MealOptionEntity>(dao) {

    override fun MealOptionEntity.toModel(): MealOption {
        return MealOption(
            id = this.mealOptionId,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun MealOption.toEntity(): MealOptionEntity {
        return MealOptionEntity(
            mealOptionId = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}
