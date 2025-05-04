package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.HealthOptionEntity

object HealthOptionSeed : Seed<HealthOptionEntity> {
    override val data = listOf(
        HealthOptionEntity(id = 1, description = "Dor", blocked = true, active = true),
        HealthOptionEntity(id = 2, description = "Gripado(a)", blocked = true, active = true),
        HealthOptionEntity(id = 3, description = "Cansado(a)", blocked = true, active = true),
        HealthOptionEntity(id = 4, description = "Com febre", blocked = true, active = true),
        HealthOptionEntity(id = 5, description = "Náusea", blocked = true, active = true),
        HealthOptionEntity(id = 6, description = "Insônia", blocked = true, active = true),
        HealthOptionEntity(id = 7, description = "Fadiga", blocked = true, active = true),
        HealthOptionEntity(id = 8, description = "Coceira", blocked = true, active = true),
        HealthOptionEntity(id = 9, description = "Azia", blocked = true, active = true),
        HealthOptionEntity(id = 10, description = "Tontura", blocked = true, active = true),
        HealthOptionEntity(id = 11, description = "Falta de ar", blocked = true, active = true),
        HealthOptionEntity(id = 12, description = "Calafrios", blocked = true, active = true),
        HealthOptionEntity(id = 13, description = "Tosse", blocked = true, active = true),
        HealthOptionEntity(id = 14, description = "Tremor", blocked = true, active = true),
        HealthOptionEntity(id = 15, description = "Congestão nasal", blocked = true, active = true),
        HealthOptionEntity(id = 16, description = "Dor de cabeça", blocked = true, active = true),
        HealthOptionEntity(id = 17, description = "Dor muscular", blocked = true, active = true),
        HealthOptionEntity(id = 18, description = "Dor no estômago", blocked = true, active = true),
        HealthOptionEntity(id = 19, description = "Inchaço", blocked = true, active = true),
        HealthOptionEntity(id = 20, description = "Suor excessivo", blocked = true, active = true),
    )
}