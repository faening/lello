package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.FoodOptionDao
import io.github.faening.lello.core.database.model.option.FoodOptionEntity
import io.github.faening.lello.core.model.option.FoodOption
import jakarta.inject.Inject

class FoodOptionRepository @Inject constructor(
    dao: FoodOptionDao
) : OptionRepository<FoodOption, FoodOptionEntity>(dao) {

    override fun FoodOptionEntity.toModel(): FoodOption {
        return FoodOption(
            id = this.foodOptionId,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun FoodOption.toEntity(): FoodOptionEntity {
        return FoodOptionEntity(
            foodOptionId = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}
