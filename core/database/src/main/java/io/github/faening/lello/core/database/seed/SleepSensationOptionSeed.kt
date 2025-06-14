package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.option.SleepSensationOptionEntity

object SleepSensationOptionSeed : Seed<SleepSensationOptionEntity> {
    override val data = listOf(
        SleepSensationOptionEntity(sleepSensationOptionId = 1, description = "Ágil", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 2, description = "Aliviado", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 3, description = "Animado", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 4, description = "Apático", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 5, description = "Cansado", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 6, description = "Confuso", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 7, description = "Consciente", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 8, description = "Desorientado", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 9, description = "Disposto", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 10, description = "Energético", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 11, description = "Equilibrado", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 12, description = "Estressado", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 13, description = "Irritado", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 14, description = "Letárgico", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 15, description = "Motivado", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 16, description = "Relaxado", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 17, description = "Ressoando", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 18, description = "Revigorado", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 19, description = "Sonolento", blocked = true, active = true),
        SleepSensationOptionEntity(sleepSensationOptionId = 20, description = "Tranquilo", blocked = true, active = true)
    )
}