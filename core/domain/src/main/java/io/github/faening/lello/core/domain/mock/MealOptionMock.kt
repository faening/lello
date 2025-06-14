package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.model.journal.MealOption

object MealOptionMock {
    val list = listOf(
        MealOption(id = 1, description = "Almoço", blocked = false, active = true),
        MealOption(id = 2, description = "Brunch", blocked = false, active = true),
        MealOption(id = 3, description = "Café (somente)", blocked = false, active = true),
        MealOption(id = 4, description = "Café da manhã", blocked = false, active = true),
        MealOption(id = 5, description = "Ceia", blocked = false, active = true),
        MealOption(id = 6, description = "Lanche da tarde", blocked = false, active = true),
        MealOption(id = 7, description = "Petisco", blocked = false, active = true)
    )
}