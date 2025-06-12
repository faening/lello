package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.option.SleepQualityOptionEntity

object SleepQualityOptionSeed : Seed<SleepQualityOptionEntity> {
    override val data = listOf(
        SleepQualityOptionEntity(sleepQualityOptionId = 1, description = "Acordar várias vezes", blocked = true, active = true),
        SleepQualityOptionEntity(sleepQualityOptionId = 2, description = "Despertar abrupto", blocked = true, active = true),
        SleepQualityOptionEntity(sleepQualityOptionId = 3, description = "Despertar suado", blocked = true, active = true),
        SleepQualityOptionEntity(sleepQualityOptionId = 4, description = "Dormiu bem", blocked = true, active = true),
        SleepQualityOptionEntity(sleepQualityOptionId = 5, description = "Insônia", blocked = true, active = true),
        SleepQualityOptionEntity(sleepQualityOptionId = 6, description = "Pesadelos", blocked = true, active = true),
        SleepQualityOptionEntity(sleepQualityOptionId = 7, description = "Sono profundo", blocked = true, active = true),
        SleepQualityOptionEntity(sleepQualityOptionId = 8, description = "Sono leve", blocked = true, active = true),
        SleepQualityOptionEntity(sleepQualityOptionId = 9, description = "Sono interrompido", blocked = true, active = true)
    )
}
