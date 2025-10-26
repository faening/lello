package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.option.MedicationDosageUnitOptionEntity

object MedicationDosageUnitOptionSeed : Seed<MedicationDosageUnitOptionEntity> {
    override val data = listOf(
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 1,
            description = "Miligrama (mg)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 2,
            description = "Grama (g)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 3,
            description = "Micrograma (mcg)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 4,
            description = "Mililitro (ml)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 5,
            description = "Centímetro Cúbico (cc)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 6,
            description = "Gota (gts)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 7,
            description = "Litro (L)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 8,
            description = "Unidade Internacional (UI)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 9,
            description = "Miliunidade Internacional (mUI)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 10,
            description = "Jato",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 11,
            description = "Borrifada",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 12,
            description = "Inalação",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 13,
            description = "Porcentagem (%)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 14,
            description = "mg/ml",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 15,
            description = "mg/g",
            blocked = true,
            active = true
        ),
    )
}