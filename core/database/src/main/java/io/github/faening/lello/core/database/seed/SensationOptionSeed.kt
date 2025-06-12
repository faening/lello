package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.option.SensationOptionEntity

object SensationOptionSeed : Seed<SensationOptionEntity> {
    override val data = listOf(
        SensationOptionEntity(sensationOptionId = 1, description = "Ágil", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 2, description = "Aliviado", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 3, description = "Animado", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 4, description = "Apático", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 5, description = "Cansado", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 6, description = "Confuso", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 7, description = "Consciente", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 8, description = "Desorientado", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 9, description = "Disposto", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 10, description = "Energético", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 11, description = "Equilibrado", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 12, description = "Estressado", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 13, description = "Irritado", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 14, description = "Letárgico", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 15, description = "Motivado", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 16, description = "Relaxado", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 17, description = "Ressoando", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 18, description = "Revigorado", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 19, description = "Sonolento", blocked = true, active = true),
        SensationOptionEntity(sensationOptionId = 20, description = "Tranquilo", blocked = true, active = true)
    )
}