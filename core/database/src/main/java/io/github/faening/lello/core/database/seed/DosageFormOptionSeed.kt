package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.option.DosageFormOptionEntity

object DosageFormOptionSeed : Seed<DosageFormOptionEntity> {
    override val data = listOf(
        DosageFormOptionEntity(dosageFormOptionId = 1, description = "Ampola", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 2, description = "Anel", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 3, description = "Cápsula", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 4, description = "Cápsula gelatinosa", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 5, description = "Chá", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 6, description = "Comprimido", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 7, description = "Comprimido mastigável", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 8, description = "Comprimido sublingual", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 9, description = "Creme", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 10, description = "Curativo", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 11, description = "Drágea", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 12, description = "Espuma", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 13, description = "Gel", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 14, description = "Goma", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 15, description = "Inalador", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 16, description = "Injeção", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 17, description = "Loção", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 18, description = "Pasta de dente", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 19, description = "Pomada", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 20, description = "Pó", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 21, description = "Shampoo", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 22, description = "Spray", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 23, description = "Supositório", blocked = true, active = true),
        DosageFormOptionEntity(dosageFormOptionId = 24, description = "Xarope", blocked = true, active = true)
    )
}
