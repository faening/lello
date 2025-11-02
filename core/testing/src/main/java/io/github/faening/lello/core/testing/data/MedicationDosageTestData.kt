package io.github.faening.lello.core.testing.data

import io.github.faening.lello.core.model.medication.MedicationDosage
import java.time.LocalTime

object MedicationDosageTestData {
    val list = listOf(
        MedicationDosage(
            dosageNumber = 1,
            quantity = 2.0,
            unit = "miligrama(s) (mg)",
            time = LocalTime.of(8, 0)
        ),
        MedicationDosage(
            dosageNumber = 2,
            quantity = 1.0,
            unit = "gramas(s) (g)",
            time = LocalTime.of(14, 0)
        ),
        MedicationDosage(
            dosageNumber = 3,
            quantity = 1.0,
            unit = "gota(s) (gts)",
            time = LocalTime.of(20, 0)
        )
    )
}