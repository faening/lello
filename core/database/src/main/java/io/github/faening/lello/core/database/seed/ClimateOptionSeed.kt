package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.ClimateOptionEntity

internal object ClimateOptionSeed : Seed<ClimateOptionEntity> {
    override val data = listOf(
        ClimateOptionEntity(id = 1, description = "Céu Limpo", blocked = true, active = true),
        ClimateOptionEntity(id = 2, description = "Chuvoso", blocked = true, active = true),
        ClimateOptionEntity(id = 3, description = "Empoeirado", blocked = true, active = true),
        ClimateOptionEntity(id = 4, description = "Encoberto", blocked = true, active = true),
        ClimateOptionEntity(id = 5, description = "Ensolarado", blocked = true, active = true),
        ClimateOptionEntity(id = 6, description = "Frio", blocked = true, active = true),
        ClimateOptionEntity(id = 7, description = "Fumaça", blocked = true, active = true),
        ClimateOptionEntity(id = 8, description = "Garoa", blocked = true, active = true),
        ClimateOptionEntity(id = 9, description = "Geada", blocked = true, active = true),
        ClimateOptionEntity(id = 10, description = "Neblina", blocked = true, active = true),
        ClimateOptionEntity(id = 11, description = "Nevado", blocked = true, active = true),
        ClimateOptionEntity(id = 12, description = "Nublado", blocked = true, active = true),
        ClimateOptionEntity(id = 13, description = "Parcialmente nublado", blocked = true, active = true),
        ClimateOptionEntity(id = 14, description = "Quente", blocked = true, active = true),
        ClimateOptionEntity(id = 15, description = "Tempestade", blocked = true, active = true),
        ClimateOptionEntity(id = 16, description = "Úmido", blocked = true, active = true),
        ClimateOptionEntity(id = 17, description = "Ventando", blocked = true, active = true)
    )
}