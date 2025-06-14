package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.option.MealOptionEntity

object MealOptionSeed : Seed<MealOptionEntity> {
    override val data = listOf(
        MealOptionEntity(mealOptionId = 1, description = "Almoço", blocked = true, active = true),
        MealOptionEntity(mealOptionId = 2, description = "Brunch", blocked = true, active = true),
        MealOptionEntity(mealOptionId = 3, description = "Café (somente)", blocked = true, active = true),
        MealOptionEntity(mealOptionId = 4, description = "Café da manhã", blocked = true, active = true),
        MealOptionEntity(mealOptionId = 5, description = "Ceia", blocked = true, active = true),
        MealOptionEntity(mealOptionId = 6, description = "Chá da tarde", blocked = true, active = true),
        MealOptionEntity(mealOptionId = 7, description = "Jantar", blocked = true, active = true),
        MealOptionEntity(mealOptionId = 8, description = "Lanche da manhã", blocked = true, active = true),
        MealOptionEntity(mealOptionId = 9, description = "Lanche da tarde", blocked = true, active = true),
        MealOptionEntity(mealOptionId = 10, description = "Lanche noturno", blocked = true, active = true),
        MealOptionEntity(mealOptionId = 11, description = "Petisco", blocked = true, active = true),
        MealOptionEntity(mealOptionId = 12, description = "Pós-treino", blocked = true, active = true),
        MealOptionEntity(mealOptionId = 13, description = "Pré-treino", blocked = true, active = true),
        MealOptionEntity(mealOptionId = 14, description = "Refeição livre", blocked = true, active = true),
        MealOptionEntity(mealOptionId = 15, description = "Sobremesa", blocked = true, active = true),
    )
}
