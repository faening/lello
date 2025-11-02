package io.github.faening.lello.core.testing.data

import io.github.faening.lello.core.model.medication.Medication

object MedicationTestData {
    private val activeIngredients = MedicationActiveIngredientOptionTestData.list
    private val forms = MedicationDosageFormOptionTestData.list
    private val dosages = MedicationDosageTestData.list

    val list = listOf(
        Medication(
            id = 1,
            activeIngredientOption = activeIngredients[0],
            dosageFormOption = forms[0],
            dosages = listOf(
                dosages[0]
            ),
            active = true,
            createdAt = 1625155200000,
            updatedAt = 1625155200000
        ),
        Medication(
            id = 2,
            activeIngredientOption = activeIngredients[2],
            dosageFormOption = forms[1],
            dosages = listOf(
                dosages[1],
                dosages[2]
            ),
            active = true,
            createdAt = 1625155200000,
            updatedAt = 1625155200000
        )
    )
}