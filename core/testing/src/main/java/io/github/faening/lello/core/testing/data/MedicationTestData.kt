package io.github.faening.lello.core.testing.data

import io.github.faening.lello.core.model.medication.Medication
import io.github.faening.lello.core.testing.repository.TestDataResources

object MedicationTestData : TestDataResources<Medication> {
    private val activeIngredients = MedicationActiveIngredientOptionTestData.list
    private val forms = MedicationDosageFormOptionTestData.list
    private val dosages = MedicationDosageTestData.list

    override val list: List<Medication> = listOf(
        Medication(
            id = 1L,
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
            id = 2L,
            activeIngredientOption = activeIngredients[1],
            dosageFormOption = forms[1],
            dosages = listOf(
                dosages[1],
            ),
            active = true,
            createdAt = 1625155200000,
            updatedAt = 1625155200000
        ),
        Medication(
            id = 3L,
            activeIngredientOption = activeIngredients[2],
            dosageFormOption = forms[2],
            dosages = listOf(
                dosages[1],
                dosages[3],
            ),
            active = false,
            createdAt = 1625155200000,
            updatedAt = 1625155200000
        ),
        Medication(
            id = 4L,
            activeIngredientOption = activeIngredients[3],
            dosageFormOption = forms[3],
            dosages = listOf(
                dosages[2],
                dosages[4],
            ),
            active = true,
            createdAt = 1625155200000,
            updatedAt = 1625155200000
        ),
        Medication(
            id = 5L,
            activeIngredientOption = activeIngredients[4],
            dosageFormOption = forms[4],
            dosages = listOf(
                dosages[0],
                dosages[3],
                dosages[5],
            ),
            active = false,
            createdAt = 1625155200000,
            updatedAt = 1625155200000
        ),
    )
}