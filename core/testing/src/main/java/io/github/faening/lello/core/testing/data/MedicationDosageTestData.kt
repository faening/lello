package io.github.faening.lello.core.testing.data

import io.github.faening.lello.core.model.medication.MedicationDosage
import io.github.faening.lello.core.model.option.MedicationDosageUnitOption
import java.time.LocalTime

object MedicationDosageTestData {
    val list = listOf(
        MedicationDosage(
            dosageNumber = 1,
            quantity = 2.0,
            unitOption = MedicationDosageUnitOption(
                id = 1,
                description = "Miligrama(s) (mg)",
                blocked = true,
                active = true
            ),
            time = LocalTime.of(8, 0)
        ),
        MedicationDosage(
            dosageNumber = 2,
            quantity = 1.0,
            unitOption = MedicationDosageUnitOption(
                id = 1,
                description = "Grama(s) (g)",
                blocked = true,
                active = true
            ),
            time = LocalTime.of(14, 0)
        ),
        MedicationDosage(
            dosageNumber = 3,
            quantity = 1.0,
            unitOption = MedicationDosageUnitOption(
                id = 1,
                description = "Mililitro(s) (ml)",
                blocked = true,
                active = true
            ),
            time = LocalTime.of(20, 0)
        )
    )
}