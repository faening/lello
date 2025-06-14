package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.model.option.SleepSensationOption

object SleepSensationOptionMock {
    val list = listOf(
        SleepSensationOption(id = 1, description = "Acordei muito bem!", blocked = true, active = true),
        SleepSensationOption(id = 2, description = "Bem descansado(a)", blocked = true, active = true),
        SleepSensationOption(id = 3, description = "Satisfeito(a)", blocked = true, active = true),
        SleepSensationOption(id = 4, description = "Nada de diferente", blocked = true, active = true),
        SleepSensationOption(id = 5, description = "Poderia ter sido melhor", blocked = true, active = true),
        SleepSensationOption(id = 6, description = "Cansado(a) ao acordar", blocked = true, active = true),
        SleepSensationOption(id = 7, description = "Muito cansado(a)", blocked = true, active = true)
    )
}