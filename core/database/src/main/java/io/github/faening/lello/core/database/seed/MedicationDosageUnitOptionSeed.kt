package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.option.MedicationDosageUnitOptionEntity

object MedicationDosageUnitOptionSeed : Seed<MedicationDosageUnitOptionEntity> {
    override val data = listOf(
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 1,
            description = "Miligrama(s) (mg)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 2,
            description = "Grama(s) (g)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 3,
            description = "Micrograma(s) (mcg)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 4,
            description = "Mililitro(s) (ml)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 5,
            description = "Centímetro(s) Cúbico(s) (cc)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 6,
            description = "Gota(s) (gts)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 7,
            description = "Litro(s) (L)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 8,
            description = "Unidade(s) Internacional(is) (UI)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 9,
            description = "Miliunidade(s) Internacional(is) (mUI)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 10,
            description = "Jato(s)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 11,
            description = "Borrifada(s)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = 12,
            description = "Inalação(ões)",
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