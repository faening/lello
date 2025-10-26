package io.github.faening.lello.core.database.model.medication

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import io.github.faening.lello.core.model.AnvisaMedication

@Entity(tableName = "anvisa_medications")
data class AnvisaMedicationEntity(
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

fun AnvisaMedicationEntity.toModel() = AnvisaMedication(
    id = id,
    productName = productName,
    registrationNumber = registrationNumber,
    therapeuticClass = therapeuticClass,
    company = company,
    activeIngredient = activeIngredient
)

fun AnvisaMedication.toEntity() = AnvisaMedicationEntity(
    id = id,
    productName = productName,
    registrationNumber = registrationNumber,
    therapeuticClass = therapeuticClass,
    company = company,
    activeIngredient = activeIngredient
)