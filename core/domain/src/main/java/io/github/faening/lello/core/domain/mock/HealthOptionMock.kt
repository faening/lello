package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.model.journal.HealthOption

object HealthOptionMock {
    val list = listOf(
        HealthOption(id = 1, description = "Azia", blocked = false, active = true),
        HealthOption(id = 2, description = "Calafrios", blocked = false, active = true),
        HealthOption(id = 3, description = "Cansado(a)", blocked = false, active = true),
        HealthOption(id = 4, description = "Coceira", blocked = false, active = true),
        HealthOption(id = 5, description = "Congestão nasal", blocked = false, active = true),
        HealthOption(id = 6, description = "Dor", blocked = false, active = true),
        HealthOption(id = 7, description = "Fadiga", blocked = false, active = true)
    )
}