package io.github.faening.lello.core.model.journal

import io.github.faening.lello.core.model.medication.Medication
import io.github.faening.lello.core.model.medication.MedicationDosage
import io.github.faening.lello.core.model.option.MedicationSkipReasonOption
import kotlinx.serialization.Serializable

@Serializable
data class MedicationJournal(
    val id: Long?,
    val medication: Medication?,
    val medicationDosage: MedicationDosage?,
    val scheduledTime: Long,
    val taken: Boolean,
    val skipReasonOption: MedicationSkipReasonOption?,
    val registeredAt: Long,
    val createdAt: Long
)