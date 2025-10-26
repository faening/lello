package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.MedicationDosageFormOptionDao
import io.github.faening.lello.core.database.model.option.MedicationDosageFormOptionEntity
import io.github.faening.lello.core.model.option.MedicationDosageFormOption
import jakarta.inject.Inject

class DataMedicationDosageFormOptionRepository @Inject constructor(
    dao: MedicationDosageFormOptionDao
) : DataAbstractOptionRepository<MedicationDosageFormOption, MedicationDosageFormOptionEntity>(dao) {

    override fun MedicationDosageFormOptionEntity.toModel(): MedicationDosageFormOption {
        return MedicationDosageFormOption(
            id = this.dosageFormOptionId,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun MedicationDosageFormOption.toEntity(): MedicationDosageFormOptionEntity {
        return MedicationDosageFormOptionEntity(
            dosageFormOptionId = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}
