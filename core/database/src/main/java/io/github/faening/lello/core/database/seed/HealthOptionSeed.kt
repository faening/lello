package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.HealthOptionEntity

object HealthOptionSeed : Seed<HealthOptionEntity> {
    override val data = listOf(
        HealthOptionEntity(healthOptionId = 1, description = "Dor", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 2, description = "Gripado(a)", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 3, description = "Cansado(a)", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 4, description = "Com febre", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 5, description = "Náusea", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 6, description = "Insônia", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 7, description = "Fadiga", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 8, description = "Coceira", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 9, description = "Azia", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 10, description = "Tontura", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 11, description = "Falta de ar", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 12, description = "Calafrios", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 13, description = "Tosse", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 14, description = "Tremor", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 15, description = "Congestão nasal", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 16, description = "Dor de cabeça", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 17, description = "Dor muscular", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 18, description = "Dor no estômago", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 19, description = "Inchaço", blocked = true, active = true),
        HealthOptionEntity(healthOptionId = 20, description = "Suor excessivo", blocked = true, active = true),
    )
}