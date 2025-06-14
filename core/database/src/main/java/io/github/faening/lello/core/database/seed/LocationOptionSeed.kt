package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.option.LocationOptionEntity

object LocationOptionSeed : Seed<LocationOptionEntity> {
    override val data = listOf(
        LocationOptionEntity(locationOptionId = 1, description = "Academia", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 2, description = "Aeroporto", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 3, description = "Bar", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 4, description = "Café", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 5, description = "Casa", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 6, description = "Centro Comercial", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 7, description = "Cinema", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 8, description = "Consultório", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 9, description = "Escola", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 10, description = "Estádio", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 11, description = "Evento Social", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 12, description = "Hospital", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 13, description = "Loja", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 14, description = "Parque", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 15, description = "Praia", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 16, description = "Restaurante", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 17, description = "Rodoviária", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 18, description = "Shopping", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 19, description = "Trabalho", blocked = true, active = true),
        LocationOptionEntity(locationOptionId = 20, description = "Viagem", blocked = true, active = true)
    )
}