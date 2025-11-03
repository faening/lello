package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.option.MedicationSkipReasonOptionEntity

object MedicationSkipReasonOptionSeed : Seed<MedicationSkipReasonOptionEntity> {
    override val data = listOf(
        MedicationSkipReasonOptionEntity(
            skipReasonOptionId = 1,
            description = "Esqueceu de tomar",
            blocked = true,
            active = true
        ),
        MedicationSkipReasonOptionEntity(
            skipReasonOptionId = 2,
            description = "Acabou o medicamento",
            blocked = true,
            active = true
        ),
        MedicationSkipReasonOptionEntity(
            skipReasonOptionId = 3,
            description = "Efeitos colaterais",
            blocked = true,
            active = true
        ),
        MedicationSkipReasonOptionEntity(
            skipReasonOptionId = 4,
            description = "Sentiu-se melhor sem",
            blocked = true,
            active = true
        ),
        MedicationSkipReasonOptionEntity(
            skipReasonOptionId = 5,
            description = "Mudança na rotina",
            blocked = true,
            active = true
        ),
        MedicationSkipReasonOptionEntity(
            skipReasonOptionId = 6,
            description = "Perdeu o horário",
            blocked = true,
            active = true
        ),
        MedicationSkipReasonOptionEntity(
            skipReasonOptionId = 7,
            description = "Dificuldade para ingerir",
            blocked = true,
            active = true
        ),
        MedicationSkipReasonOptionEntity(
            skipReasonOptionId = 8,
            description = "Decisão própria",
            blocked = true,
            active = true
        ),
        MedicationSkipReasonOptionEntity(
            skipReasonOptionId = 9,
            description = "Esqueceu de comprar",
            blocked = true,
            active = true
        ),
        MedicationSkipReasonOptionEntity(
            skipReasonOptionId = 10,
            description = "Crise de ansiedade",
            blocked = true,
            active = true
        ),
        MedicationSkipReasonOptionEntity(
            skipReasonOptionId = 11,
            description = "Dificuldade financeira para adquirir",
            blocked = true,
            active = true
        ),
    )
}