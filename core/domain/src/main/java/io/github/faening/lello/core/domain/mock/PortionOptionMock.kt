package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.model.journal.PortionOption

object PortionOptionMock {
    val list = listOf(
        PortionOption(id = 1, description = "Copo", blocked = false, active = true),
        PortionOption(id = 2, description = "Fatia", blocked = false, active = true),
        PortionOption(id = 3, description = "Porção pequena", blocked = false, active = true),
        PortionOption(id = 4, description = "Porção média", blocked = false, active = true),
        PortionOption(id = 5, description = "CeiaPorção grande", blocked = false, active = true),
        PortionOption(id = 6, description = "Prato", blocked = false, active = true),
        PortionOption(id = 7, description = "Tigela", blocked = false, active = true)
    )
}