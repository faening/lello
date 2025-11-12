package io.github.faening.lello.core.testing.data

import io.github.faening.lello.core.model.option.MedicationDosageUnitOption
import io.github.faening.lello.core.testing.repository.TestDataResources

object MedicationDosageUnitOptionTestData : TestDataResources<MedicationDosageUnitOption> {
    override val list: List<MedicationDosageUnitOption> = listOf(
        MedicationDosageUnitOption(
            id = 1L,
            description = "Miligrama(s) (mg)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOption(
            id = 2L,
            description = "Grama(s) (g)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOption(
            id = 3L,
            description = "Mililitro(s) (ml)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOption(
            id = 4L,
            description = "Unidade(s) (un)",
            blocked = true,
            active = true
        ),
        MedicationDosageUnitOption(
            id = 5L,
            description = "Gota(s) (gts)",
            blocked = true,
            active = true
        )
    )
}