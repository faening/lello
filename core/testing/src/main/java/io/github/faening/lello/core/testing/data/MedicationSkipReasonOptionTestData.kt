package io.github.faening.lello.core.testing.data

import io.github.faening.lello.core.model.option.MedicationSkipReasonOption
import io.github.faening.lello.core.testing.repository.TestDataResources

object MedicationSkipReasonOptionTestData : TestDataResources<MedicationSkipReasonOption> {
    override val list: List<MedicationSkipReasonOption> = listOf(
        MedicationSkipReasonOption(
            id = 1L,
            description = "Esqueci de tomar",
            blocked = false,
            active = true,
            selected = false
        ),
        MedicationSkipReasonOption(
            id = 2L,
            description = "Não estava afim",
            blocked = false,
            active = true,
            selected = false
        ),
        MedicationSkipReasonOption(
            id = 3L,
            description = "Reações adversas",
            blocked = false,
            active = true,
            selected = false
        ),
        MedicationSkipReasonOption(
            id = 4L,
            description = "Outro compromisso",
            blocked = false,
            active = true,
            selected = false
        ),
        MedicationSkipReasonOption(
            id = 5L,
            description = "Sem condiição financeira para comprar",
            blocked = false,
            active = true,
            selected = false
        ),
    )
}