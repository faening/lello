package io.github.faening.lello.core.testing.data

import io.github.faening.lello.core.model.option.MedicationActiveIngredientOption

object MedicationActiveIngredientOptionTestData {
    val list = listOf(
        MedicationActiveIngredientOption(
            id = 1L,
            description = "ACIDO ACETILSALICILICO",
            blocked = true,
            active = true,
        ),
        MedicationActiveIngredientOption(
            id = 2L,
            description = "DIPIRONA",
            blocked = true,
            active = true,
        ),
        MedicationActiveIngredientOption(
            id = 3L,
            description = "ACETATO DE RACEALFATOCOFEROL +  BETACAROTENO +  OXIDO CUPRICO +  RIBOFLAVINA +  SELENATO DE SÓDIO +  ÁCIDO ASCÓRBICO +  ÓXIDO CÚPRICO +  ÓXIDO DE ZINCO",
            blocked = true,
            active = true,
        ),
        MedicationActiveIngredientOption(
            id = 4L,
            description = "CLORIDRATO DE TIAMINA",
            blocked = true,
            active = true,
        ),
        MedicationActiveIngredientOption(
            id = 5L,
            description = "CETOCONAZOL",
            blocked = true,
            active = true,
        )
    )
}