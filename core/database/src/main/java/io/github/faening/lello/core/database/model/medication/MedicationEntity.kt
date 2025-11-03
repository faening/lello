package io.github.faening.lello.core.database.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.medication.Medication

@Entity(tableName = "medications")
data class MedicationEntity(
    @PrimaryKey(autoGenerate = true) val medicationId: Long,
    @ColumnInfo(name = "active_ingredient_option_id") val activeIngredientOptionId: Long?,
    @ColumnInfo(name = "dosage_form_option_id") val dosageFormOptionId: Long?,
    @ColumnInfo(name = "active") val active: Boolean?,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "updated_at") val updatedAt: Long,
)

fun MedicationEntity.toModel() = Medication(
    id = medicationId,
    activeIngredientOption = null,
    dosageFormOption = null,
    dosages = emptyList(),
    active = active,
    createdAt = createdAt,
    updatedAt = updatedAt,
)

fun Medication.toEntity() = MedicationEntity(
    medicationId = id ?: 0L,
    activeIngredientOptionId = activeIngredientOption?.id,
    dosageFormOptionId = dosageFormOption?.id,
    active = active ?: true,
    createdAt = createdAt,
    updatedAt = updatedAt
)