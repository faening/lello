package io.github.faening.lello.core.database.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.medication.MedicationDosage
import java.time.LocalTime

@Entity(
    tableName = "medication_dosages",
    foreignKeys = [
        ForeignKey(
            entity = MedicationEntity::class,
            parentColumns = ["medicationId"],
            childColumns = ["medication_id"],
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["medication_id"])]
)
data class MedicationDosageEntity(
    @PrimaryKey(autoGenerate = true) val medicationDosageId: Long = 0L,
    @ColumnInfo(name = "medication_id") val medicationId: Long,
    @ColumnInfo(name = "dosage_number") val dosageNumber: Int,
    @ColumnInfo(name = "quantity") val quantity: Double,
    @ColumnInfo(name = "unit_option_id") val unitOptionId: Long,
    @ColumnInfo(name = "time") val time: String,
    @ColumnInfo(name = "active") val active: Boolean?,
)

fun MedicationDosageEntity.toModel() = MedicationDosage(
    id = medicationDosageId,
    dosageNumber = dosageNumber,
    quantity = quantity,
    unitOption = null,
    time = LocalTime.parse(time),
    active = active ?: true
)

fun MedicationDosage.toEntity(medicationId: Long) = MedicationDosageEntity(
    medicationDosageId = id ?: 0L,
    medicationId = medicationId,
    dosageNumber = dosageNumber,
    quantity = quantity,
    unitOptionId = unitOption?.id ?: 0L,
    time = time.toString(),
    active = active
)