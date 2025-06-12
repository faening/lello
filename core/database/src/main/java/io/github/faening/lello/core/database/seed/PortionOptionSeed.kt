package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.option.PortionOptionEntity

object PortionOptionSeed : Seed<PortionOptionEntity> {
    override val data = listOf(
        PortionOptionEntity(portionOptionId = 1, description = "Copo", blocked = true, active = true),
        PortionOptionEntity(portionOptionId = 2, description = "Fatia", blocked = true, active = true),
        PortionOptionEntity(portionOptionId = 3, description = "Porção pequena", blocked = true, active = true),
        PortionOptionEntity(portionOptionId = 4, description = "Porção média", blocked = true, active = true),
        PortionOptionEntity(portionOptionId = 5, description = "Porção grande", blocked = true, active = true),
        PortionOptionEntity(portionOptionId = 6, description = "Prato", blocked = true, active = true),
        PortionOptionEntity(portionOptionId = 7, description = "Tigela", blocked = true, active = true),
        PortionOptionEntity(portionOptionId = 8, description = "Livre (à vontade)", blocked = true, active = true),
    )
}

