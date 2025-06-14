package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.model.option.FoodOption

object FoodOptionMock {
    val list = listOf(
        FoodOption(id = 1, description = "Comida caseira", blocked = false, active = true),
        FoodOption(id = 2, description = "Comida mexicana", blocked = false, active = true),
        FoodOption(id = 3, description = "Comida japonesa", blocked = false, active = true),
        FoodOption(id = 4, description = "Doces", blocked = false, active = true),
        FoodOption(id = 5, description = "Fast food", blocked = false, active = true),
        FoodOption(id = 6, description = "Saudável", blocked = false, active = true),
        FoodOption(id = 7, description = "Vegano", blocked = false, active = true)
    )
}