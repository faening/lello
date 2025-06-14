package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.model.option.LocationOption

object LocationOptionMock {
    val list = listOf(
        LocationOption(id = 1, description = "Academia", blocked = false, active = true),
        LocationOption(id = 2, description = "Aeroporto", blocked = false, active = true),
        LocationOption(id = 3, description = "Bar", blocked = false, active = true),
        LocationOption(id = 4, description = "Café", blocked = false, active = true),
        LocationOption(id = 5, description = "Casa", blocked = false, active = true),
        LocationOption(id = 6, description = "Comércio", blocked = false, active = true),
        LocationOption(id = 7, description = "Cinema", blocked = false, active = true)
    )
}
