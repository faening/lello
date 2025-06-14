package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.model.option.AppetiteOption

object AppetiteOptionMock {
    val list = listOf(
        AppetiteOption(id = 1, description = "Ausente de fome", blocked = false, active = true),
        AppetiteOption(id = 2, description = "Elevado", blocked = false, active = true),
        AppetiteOption(id = 3, description = "Reduzido", blocked = false, active = true),
        AppetiteOption(id = 4, description = "Fome constante", blocked = false, active = true),
        AppetiteOption(id = 5, description = "Fome emocional", blocked = false, active = true),
        AppetiteOption(id = 6, description = "Fome tardia", blocked = false, active = true),
        AppetiteOption(id = 7, description = "Igual ao habitual", blocked = false, active = true)
    )
}