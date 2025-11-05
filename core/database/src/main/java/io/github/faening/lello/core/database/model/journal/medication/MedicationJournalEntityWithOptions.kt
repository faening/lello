package io.github.faening.lello.core.database.model.journal.medication

import androidx.room.Embedded
import androidx.room.Relation
import io.github.faening.lello.core.database.model.medication.MedicationDosageEntity
import io.github.faening.lello.core.database.model.medication.MedicationDosageEntityWithOption
import io.github.faening.lello.core.database.model.medication.MedicationEntity
import io.github.faening.lello.core.database.model.medication.MedicationEntityWithOptions
import io.github.faening.lello.core.database.model.medication.toModel
import io.github.faening.lello.core.database.model.option.MedicationSkipReasonOptionEntity
import io.github.faening.lello.core.database.model.option.toModel
import io.github.faening.lello.core.model.journal.MedicationJournal

data class MedicationJournalEntityWithOptions(
    @Embedded val journal: MedicationJournalEntity,
    @Relation(
        entity = MedicationEntity::class,
        parentColumn = "medication_id",
        entityColumn = "medicationId"
    )
    val medication: MedicationEntityWithOptions?,
    @Relation(
        entity = MedicationDosageEntity::class,
        parentColumn = "medication_dosage_id",
        entityColumn = "medicationDosageId"
    )
    val medicationDosage: MedicationDosageEntityWithOption?,
    @Relation(
        parentColumn = "skip_reason_option_id",
        entityColumn = "skipReasonOptionId"
    )
    val skipReasonOption: MedicationSkipReasonOptionEntity?
)

fun MedicationJournalEntityWithOptions.toModel() = MedicationJournal(
    id = journal.medicationJournalId,
    medication = medication?.toModel(),
    medicationDosage = medicationDosage?.toModel(),
    scheduledTime = journal.scheduledTime,
    taken = journal.taken,
    skipReasonOption = skipReasonOption?.toModel(),
    registeredAt = journal.registeredAt,
    createdAt = journal.createdAt
)
