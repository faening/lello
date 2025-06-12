package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.FoodOptionEntity

object FoodOptionSeed : Seed<FoodOptionEntity> {
    override val data = listOf(
        FoodOptionEntity(foodOptionId = 1, description = "Caseira", blocked = true, active = true),
        FoodOptionEntity(foodOptionId = 2, description = "Doces", blocked = true, active = true),
        FoodOptionEntity(foodOptionId = 3, description = "Fast food", blocked = true, active = true),
        FoodOptionEntity(foodOptionId = 4, description = "Japonesa", blocked = true, active = true),
        FoodOptionEntity(foodOptionId = 5, description = "Mexicana", blocked = true, active = true),
        FoodOptionEntity(foodOptionId = 6, description = "Saudável", blocked = true, active = true),
        FoodOptionEntity(foodOptionId = 7, description = "Sobremesa", blocked = true, active = true),
        FoodOptionEntity(foodOptionId = 8, description = "Salgados", blocked = true, active = true),
        FoodOptionEntity(foodOptionId = 9, description = "Vegana", blocked = true, active = true),
        FoodOptionEntity(foodOptionId = 10, description = "Vegetariana", blocked = true, active = true),
    )
}
