package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.ClimateOptionEntity

internal object ClimateOptionSeed : Seed<ClimateOptionEntity> {
    override val data = listOf(
        ClimateOptionEntity(climateOptionId = 1, description = "Céu Limpo", blocked = true, active = true),
        ClimateOptionEntity(climateOptionId = 2, description = "Chuvoso", blocked = true, active = true),
        ClimateOptionEntity(climateOptionId = 3, description = "Empoeirado", blocked = true, active = true),
        ClimateOptionEntity(climateOptionId = 4, description = "Encoberto", blocked = true, active = true),
        ClimateOptionEntity(climateOptionId = 5, description = "Ensolarado", blocked = true, active = true),
        ClimateOptionEntity(climateOptionId = 6, description = "Frio", blocked = true, active = true),
        ClimateOptionEntity(climateOptionId = 7, description = "Fumaça", blocked = true, active = true),
        ClimateOptionEntity(climateOptionId = 8, description = "Garoa", blocked = true, active = true),
        ClimateOptionEntity(climateOptionId = 9, description = "Geada", blocked = true, active = true),
        ClimateOptionEntity(climateOptionId = 10, description = "Neblina", blocked = true, active = true),
        ClimateOptionEntity(climateOptionId = 11, description = "Nevado", blocked = true, active = true),
        ClimateOptionEntity(climateOptionId = 12, description = "Nublado", blocked = true, active = true),
        ClimateOptionEntity(climateOptionId = 13, description = "Parcialmente nublado", blocked = true, active = true),
        ClimateOptionEntity(climateOptionId = 14, description = "Quente", blocked = true, active = true),
        ClimateOptionEntity(climateOptionId = 15, description = "Tempestade", blocked = true, active = true),
        ClimateOptionEntity(climateOptionId = 16, description = "ÚmclimateOptionIdo", blocked = true, active = true),
        ClimateOptionEntity(climateOptionId = 17, description = "Ventando", blocked = true, active = true)
    )
}