package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.model.journal.LocationOption
import io.github.faening.lello.core.model.journal.SocialOption

object SocialOptionMock {
    val list = listOf(
        SocialOption(id = 1, description = "Amigo(a)", blocked = false, active = true),
        SocialOption(id = 2, description = "Família", blocked = false, active = true),
        SocialOption(id = 3, description = "Namorado(a)", blocked = false, active = true),
        SocialOption(id = 4, description = "Esposo(a)", blocked = false, active = true),
        SocialOption(id = 5, description = "Desconhecido", blocked = false, active = true),
        SocialOption(id = 6, description = "Pet", blocked = false, active = true),
        SocialOption(id = 7, description = "Vizinho(a)", blocked = false, active = true)
    )
}
