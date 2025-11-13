package io.github.faening.lello.core.testing.data

import io.github.faening.lello.core.model.medication.MedicationDosage
import io.github.faening.lello.core.testing.repository.TestDataResources
import java.time.LocalTime

object MedicationDosageTestData : TestDataResources<MedicationDosage> {
    private val unitOptions = MedicationDosageUnitOptionTestData.list

    override val list: List<MedicationDosage> = listOf(
        MedicationDosage(
            id = 1L,
            dosageNumber = 1,
            quantity = 2.0,
            unitOption = unitOptions[0],
            time = LocalTime.of(8, 0),
            active = true
        ),
        MedicationDosage(
            id = 2L,
            dosageNumber = 2,
            quantity = 1.0,
            unitOption = unitOptions[1],
            time = LocalTime.of(14, 0),
            active = true
        ),
        MedicationDosage(
            id = 3L,
            dosageNumber = 3,
            quantity = 1.0,
            unitOption = unitOptions[2],
            time = LocalTime.of(20, 0),
            active = true
        ),
        MedicationDosage(
            id = 4L,
            dosageNumber = 1,
            quantity = 5.0,
            unitOption = unitOptions[3],
            time = LocalTime.of(9, 0),
            active = true
        ),
        MedicationDosage(
            id = 5L,
            dosageNumber = 2,
            quantity = 10.0,
            unitOption = unitOptions[4],
            time = LocalTime.of(21, 0),
            active = true
        )
    )
}