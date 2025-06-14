package io.github.faening.lello.core.domain.mock

import io.github.faening.lello.core.model.option.SleepQualityOption

object SleepQualityOptionMock {
    val list = listOf(
        SleepQualityOption(id = 1, description = "Acordar várias vezes", blocked = true, active = true),
        SleepQualityOption(id = 2, description = "Despertar abrupto", blocked = true, active = true),
        SleepQualityOption(id = 3, description = "Despertar suado", blocked = true, active = true),
        SleepQualityOption(id = 4, description = "Dormiu bem", blocked = true, active = true),
        SleepQualityOption(id = 5, description = "Insônia", blocked = true, active = true),
        SleepQualityOption(id = 6, description = "Pesadelos", blocked = true, active = true),
        SleepQualityOption(id = 7, description = "Sono leve", blocked = true, active = true)
    )
}