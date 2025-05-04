package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.ClimateEntity

internal object ClimateSeed : Seed<ClimateEntity> {
    override val data = listOf(
        ClimateEntity(id = 1, weatherType = "Céu Limpo", blocked = true, active = true),
        ClimateEntity(id = 2, weatherType = "Chuvoso", blocked = true, active = true),
        ClimateEntity(id = 3, weatherType = "Empoeirado", blocked = true, active = true),
        ClimateEntity(id = 4, weatherType = "Encoberto", blocked = true, active = true),
        ClimateEntity(id = 5, weatherType = "Ensolarado", blocked = true, active = true),
        ClimateEntity(id = 6, weatherType = "Frio", blocked = true, active = true),
        ClimateEntity(id = 7, weatherType = "Fumaça", blocked = true, active = true),
        ClimateEntity(id = 8, weatherType = "Garoa", blocked = true, active = true),
        ClimateEntity(id = 9, weatherType = "Geada", blocked = true, active = true),
        ClimateEntity(id = 10, weatherType = "Neblina", blocked = true, active = true),
        ClimateEntity(id = 11, weatherType = "Nevado", blocked = true, active = true),
        ClimateEntity(id = 12, weatherType = "Nublado", blocked = true, active = true),
        ClimateEntity(id = 13, weatherType = "Parcialmente nublado", blocked = true, active = true),
        ClimateEntity(id = 14, weatherType = "Quente", blocked = true, active = true),
        ClimateEntity(id = 15, weatherType = "Tempestade", blocked = true, active = true),
        ClimateEntity(id = 16, weatherType = "Úmido", blocked = true, active = true),
        ClimateEntity(id = 17, weatherType = "Ventando", blocked = true, active = true)
    )
}