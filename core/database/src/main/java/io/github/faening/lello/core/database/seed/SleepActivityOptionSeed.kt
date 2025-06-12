package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.option.SleepActivityOptionEntity

object SleepActivityOptionSeed : Seed<SleepActivityOptionEntity> {
    override val data = listOf(
        SleepActivityOptionEntity(sleepActivityOptionId = 1, description = "Alongamento", blocked = true, active = true),
        SleepActivityOptionEntity(sleepActivityOptionId = 2, description = "Tomar café", blocked = true, active = true),
        SleepActivityOptionEntity(sleepActivityOptionId = 3, description = "Tomar chá", blocked = true, active = true),
        SleepActivityOptionEntity(sleepActivityOptionId = 4, description = "Celular", blocked = true, active = true),
        SleepActivityOptionEntity(sleepActivityOptionId = 5, description = "Computador", blocked = true, active = true),
        SleepActivityOptionEntity(sleepActivityOptionId = 6, description = "Conversar", blocked = true, active = true),
        SleepActivityOptionEntity(sleepActivityOptionId = 7, description = "Leitura", blocked = true, active = true),
        SleepActivityOptionEntity(sleepActivityOptionId = 8, description = "Meditação", blocked = true, active = true),
        SleepActivityOptionEntity(sleepActivityOptionId = 9, description = "Música", blocked = true, active = true),
        SleepActivityOptionEntity(sleepActivityOptionId = 10, description = "TV", blocked = true, active = true),
        SleepActivityOptionEntity(sleepActivityOptionId = 11, description = "Videogame", blocked = true, active = true),
    )
}
