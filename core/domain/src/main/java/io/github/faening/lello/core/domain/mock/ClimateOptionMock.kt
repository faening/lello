package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.model.option.ClimateOption

object ClimateOptionMock {
    val list = listOf(
        ClimateOption(id = 1, description = "Céu limpo", blocked = false, active = true),
        ClimateOption(id = 2, description = "Chuvoso", blocked = false, active = true),
        ClimateOption(id = 3, description = "Empoeirado", blocked = false, active = true),
        ClimateOption(id = 4, description = "Encoberto", blocked = false, active = true),
        ClimateOption(id = 5, description = "Ensolarado", blocked = false, active = true),
        ClimateOption(id = 6, description = "Frio", blocked = false, active = true),
        ClimateOption(id = 7, description = "Fumaça", blocked = false, active = true)
    )
}