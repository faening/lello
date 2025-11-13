package io.github.faening.lello.core.model.medication

import io.github.faening.lello.core.model.option.MedicationDosageUnitOption
import kotlinx.serialization.Serializable
import java.time.LocalTime

@Serializable
data class MedicationDosage(
    val id: Long? = 0L,
    val dosageNumber: Int,
    val quantity: Double,
    val unitOption: MedicationDosageUnitOption?,
    val time: LocalTime,
    val active: Boolean
) {
    companion object {
        fun fromViewModel(
            quantity: Double,
            unit: MedicationDosageUnitOption,
            timeString: String
        ): MedicationDosage {
            val time = LocalTime.parse(timeString)
            return MedicationDosage(
                id = 0L,
                dosageNumber = 1,
                quantity = quantity,
                unitOption = unit,
                time = time,
                active = true
            )
        }
    }
}