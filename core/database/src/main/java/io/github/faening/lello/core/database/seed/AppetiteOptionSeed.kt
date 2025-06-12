package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.AppetiteOptionEntity

object AppetiteOptionSeed : Seed<AppetiteOptionEntity> {
    override val data = listOf(
        AppetiteOptionEntity(appetiteOptionId = 1, description = "Ausente de fome", blocked = true, active = true),
        AppetiteOptionEntity(appetiteOptionId = 2, description = "Elevado", blocked = true, active = true),
        AppetiteOptionEntity(appetiteOptionId = 3, description = "Reduzido", blocked = true, active = true),
        AppetiteOptionEntity(appetiteOptionId = 4, description = "Desejo específico (gula)", blocked = true, active = true),
        AppetiteOptionEntity(appetiteOptionId = 5, description = "Fome constante", blocked = true, active = true),
        AppetiteOptionEntity(appetiteOptionId = 6, description = "Fome emocional", blocked = true, active = true),
        AppetiteOptionEntity(appetiteOptionId = 7, description = "Fome física", blocked = true, active = true),
        AppetiteOptionEntity(appetiteOptionId = 8, description = "Mais que o habitual", blocked = true, active = true),
        AppetiteOptionEntity(appetiteOptionId = 9, description = "Fome matinal", blocked = true, active = true),
        AppetiteOptionEntity(appetiteOptionId = 10, description = "Fome tardia", blocked = true, active = true),
        AppetiteOptionEntity(appetiteOptionId = 11, description = "Igual ao habitual", blocked = true, active = true),
        AppetiteOptionEntity(appetiteOptionId = 12, description = "Menos que o habitual", blocked = true, active = true),
        AppetiteOptionEntity(appetiteOptionId = 13, description = "Rebeldia alimentar", blocked = true, active = true),
        AppetiteOptionEntity(appetiteOptionId = 14, description = "Saciedade rápida", blocked = true, active = true),
        AppetiteOptionEntity(appetiteOptionId = 15, description = "Vontade de beliscar", blocked = true, active = true)
    )
}
