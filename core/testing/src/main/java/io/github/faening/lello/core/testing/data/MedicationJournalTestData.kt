package io.github.faening.lello.core.testing.data

import io.github.faening.lello.core.model.journal.MedicationJournal
import io.github.faening.lello.core.testing.repository.TestDataResources

object MedicationJournalTestData : TestDataResources<MedicationJournal> {
    private val medications = MedicationTestData.list
    private val dosages = MedicationDosageTestData.list
    private val skipReasons = MedicationSkipReasonOptionTestData.list

    override val list: List<MedicationJournal> = listOf(
        MedicationJournal(
            id = 1L,
            medication = medications[0],
            medicationDosage = dosages[0],
            scheduledTime = 1625198400000,
            taken = true,
            skipReasonOption = null,
            registeredAt = 1625198460000,
            createdAt = 1625198400000
        ),
        MedicationJournal(
            id = 2L,
            medication = medications[1],
            medicationDosage = dosages[1],
            scheduledTime = 1625198400000,
            taken = true,
            skipReasonOption = skipReasons[0],
            registeredAt = 1625198460000,
            createdAt = 1625198400000
        ),
        MedicationJournal(
            id = 3L,
            medication = medications[2],
            medicationDosage = dosages[2],
            scheduledTime = 1625198400000,
            taken = false,
            skipReasonOption = skipReasons[2],
            registeredAt = 1625198460000,
            createdAt = 1625198400000
        ),
        MedicationJournal(
            id = 4L,
            medication = medications[3],
            medicationDosage = dosages[3],
            scheduledTime = 1625198400000,
            taken = true,
            skipReasonOption = null,
            registeredAt = 1625198460000,
            createdAt = 1625198400000
        ),
        MedicationJournal(
            id = 5L,
            medication = medications[4],
            medicationDosage = dosages[4],
            scheduledTime = 1625198400000,
            taken = false,
            skipReasonOption = skipReasons[4],
            registeredAt = 1625198460000,
            createdAt = 1625198400000
        )
    )
}