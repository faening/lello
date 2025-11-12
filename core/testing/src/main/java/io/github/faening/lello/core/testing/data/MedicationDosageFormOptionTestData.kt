package io.github.faening.lello.core.testing.data

import io.github.faening.lello.core.model.option.MedicationDosageFormOption
import io.github.faening.lello.core.testing.repository.TestDataResources

object MedicationDosageFormOptionTestData : TestDataResources<MedicationDosageFormOption> {
    override val list = listOf(
        MedicationDosageFormOption(
            id = 1L,
            description = "AMPOLA",
            blocked = true,
            active = true,
        ),
        MedicationDosageFormOption(
            id = 2L,
            description = "ANEL",
            blocked = true,
            active = true,
        ),
        MedicationDosageFormOption(
            id = 3L,
            description = "CÁPSULA",
            blocked = true,
            active = true,
        ),
        MedicationDosageFormOption(
            id = 4L,
            description = "CÁPSULA GELATINOSA",
            blocked = true,
            active = true,
        ),
        MedicationDosageFormOption(
            id = 5L,
            description = "COMPRIMIDO",
            blocked = true,
            active = true,
        )
    )
}