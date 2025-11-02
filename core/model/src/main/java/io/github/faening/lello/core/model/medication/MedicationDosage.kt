package io.github.faening.lello.core.model.medication

import io.github.faening.lello.core.model.option.MedicationDosageUnitOption
import java.time.LocalTime

data class MedicationDosage(
    val dosageNumber: Int,
    val quantity: Double,
    val unitOption: MedicationDosageUnitOption,
    val time: LocalTime
) {
    companion object {
        fun fromViewModel(
            quantity: Double,
            unit: MedicationDosageUnitOption,
            timeString: String
        ): MedicationDosage {
            val time = LocalTime.parse(timeString)
            return MedicationDosage(
                dosageNumber = 0,
                quantity = quantity,
                unitOption = unit,
                time = time
            )
        }
    }
}