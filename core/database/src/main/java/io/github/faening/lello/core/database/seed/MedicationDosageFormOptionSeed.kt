package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.option.MedicationDosageFormOptionEntity

object MedicationDosageFormOptionSeed : Seed<MedicationDosageFormOptionEntity> {
    override val data = listOf(
        MedicationDosageFormOptionEntity(dosageFormOptionId = 1, description = "Ampola", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 2, description = "Anel", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 3, description = "Cápsula", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 4, description = "Cápsula gelatinosa", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 5, description = "Chá", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 6, description = "Comprimido", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 7, description = "Comprimido mastigável", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 8, description = "Comprimido sublingual", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 9, description = "Creme", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 10, description = "Curativo", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 11, description = "Drágea", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 12, description = "Espuma", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 13, description = "Gel", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 14, description = "Goma", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 15, description = "Inalador", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 16, description = "Injeção", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 17, description = "Loção", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 18, description = "Pasta de dente", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 19, description = "Pomada", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 20, description = "Pó", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 21, description = "Shampoo", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 22, description = "Spray", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 23, description = "Supositório", blocked = true, active = true),
        MedicationDosageFormOptionEntity(dosageFormOptionId = 24, description = "Xarope", blocked = true, active = true)
    )
}
