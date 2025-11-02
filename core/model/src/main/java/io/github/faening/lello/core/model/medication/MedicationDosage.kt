package io.github.faening.lello.core.model.medication

import java.time.LocalTime

data class MedicationDosage(
    val dosageNumber: Int,
    val quantity: Double,
    val unit: String,
    val time: LocalTime
) {
    companion object {
        fun fromViewModel(
            quantity: Double,
            unit: String,
            timeString: String
        ): MedicationDosage {
            val time = LocalTime.parse(timeString)
            return MedicationDosage(
                dosageNumber = 0,
                quantity = quantity,
                unit = unit,
                time = time
            )
        }
    }
}