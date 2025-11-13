package io.github.faening.lello.core.database.model.journal.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import io.github.faening.lello.core.database.model.medication.MedicationDosageEntity
import io.github.faening.lello.core.database.model.medication.MedicationEntity
import io.github.faening.lello.core.database.model.option.MedicationSkipReasonOptionEntity
import io.github.faening.lello.core.model.journal.MedicationJournal

@Entity(
    tableName = "medication_journals",
    foreignKeys = [
        ForeignKey(
            entity = MedicationEntity::class,
            parentColumns = ["medicationId"],
            childColumns = ["medication_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MedicationDosageEntity::class,
            parentColumns = ["medicationDosageId"],
            childColumns = ["medication_dosage_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MedicationSkipReasonOptionEntity::class,
            parentColumns = ["skipReasonOptionId"],
            childColumns = ["skip_reason_option_id"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [
        Index(value = ["medication_id"]),
        Index(value = ["medication_dosage_id"]),
        Index(value = ["skip_reason_option_id"])
    ]
)
data class MedicationJournalEntity(
    @PrimaryKey(autoGenerate = true) val medicationJournalId: Long = 0L,
    @ColumnInfo(name = "medication_id") val medicationId: Long,
    @ColumnInfo(name = "medication_dosage_id") val medicationDosageId: Long,
    @ColumnInfo(name = "scheduled_time") val scheduledTime: Long,
    @ColumnInfo(name = "taken") val taken: Boolean,
    @ColumnInfo(name = "skip_reason_option_id") val skipReasonOptionId: Long?,
    @ColumnInfo(name = "registered_at") val registeredAt: Long,
    @ColumnInfo(name = "created_at") val createdAt: Long
)

fun MedicationJournalEntity.toModel() = MedicationJournal(
    id = medicationJournalId,
    medication = null,
    medicationDosage = null,
    scheduledTime = scheduledTime,
    taken = taken,
    skipReasonOption = null,
    registeredAt = registeredAt,
    createdAt = createdAt
)

fun MedicationJournal.toEntity() = MedicationJournalEntity(
    medicationJournalId = id ?: 0L,
    medicationId = medication?.id ?: 0L,
    medicationDosageId = medicationDosage?.id ?: 0L,
    scheduledTime = scheduledTime,
    taken = taken,
    skipReasonOptionId = skipReasonOption?.id,
    registeredAt = registeredAt,
    createdAt = createdAt
)