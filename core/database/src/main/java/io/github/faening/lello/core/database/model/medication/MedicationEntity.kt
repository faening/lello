package io.github.faening.lello.core.database.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.Medication

@Entity(tableName = "medication")
data class MedicationEntity(
    @PrimaryKey val id: Long,

    @ColumnInfo(name = "product_name")
    val productName: String,

    @ColumnInfo(name = "registration_number")
    val registrationNumber: String?,

    @ColumnInfo(name = "therapeutic_class")
    val therapeuticClass: String?,

    @ColumnInfo(name = "company")
    val company: String?,

    @ColumnInfo(name = "active_ingredient")
    val activeIngredient: String?
)

fun MedicationEntity.toModel() = Medication(
    id = id,
    productName = productName,
    registrationNumber = registrationNumber,
    therapeuticClass = therapeuticClass,
    company = company,
    activeIngredient = activeIngredient
)

fun Medication.toEntity() = MedicationEntity(
    id = id,
    productName = productName,
    registrationNumber = registrationNumber,
    therapeuticClass = therapeuticClass,
    company = company,
    activeIngredient = activeIngredient
)