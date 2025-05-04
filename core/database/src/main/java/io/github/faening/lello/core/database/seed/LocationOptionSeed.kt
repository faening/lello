package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.LocationOptionEntity

object LocationOptionSeed : Seed<LocationOptionEntity> {
    override val data = listOf(
        LocationOptionEntity(id = 1, description = "Academia", blocked = true, active = true),
        LocationOptionEntity(id = 2, description = "Aeroporto", blocked = true, active = true),
        LocationOptionEntity(id = 3, description = "Bar", blocked = true, active = true),
        LocationOptionEntity(id = 4, description = "Café", blocked = true, active = true),
        LocationOptionEntity(id = 5, description = "Casa", blocked = true, active = true),
        LocationOptionEntity(id = 6, description = "Centro Comercial", blocked = true, active = true),
        LocationOptionEntity(id = 7, description = "Cinema", blocked = true, active = true),
        LocationOptionEntity(id = 8, description = "Consultório", blocked = true, active = true),
        LocationOptionEntity(id = 9, description = "Escola", blocked = true, active = true),
        LocationOptionEntity(id = 10, description = "Estádio", blocked = true, active = true),
        LocationOptionEntity(id = 11, description = "Evento Social", blocked = true, active = true),
        LocationOptionEntity(id = 12, description = "Hospital", blocked = true, active = true),
        LocationOptionEntity(id = 13, description = "Loja", blocked = true, active = true),
        LocationOptionEntity(id = 14, description = "Parque", blocked = true, active = true),
        LocationOptionEntity(id = 15, description = "Praia", blocked = true, active = true),
        LocationOptionEntity(id = 16, description = "Restaurante", blocked = true, active = true),
        LocationOptionEntity(id = 17, description = "Rodoviária", blocked = true, active = true),
        LocationOptionEntity(id = 18, description = "Shopping", blocked = true, active = true),
        LocationOptionEntity(id = 19, description = "Trabalho", blocked = true, active = true),
        LocationOptionEntity(id = 20, description = "Viagem", blocked = true, active = true)
    )
}