package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.MedicationDosageUnitOptionDao
import io.github.faening.lello.core.database.model.option.MedicationDosageUnitOptionEntity
import io.github.faening.lello.core.model.option.MedicationDosageUnitOption
import jakarta.inject.Inject

class DataMedicationDosageUnitOptionRepository @Inject constructor(
    dao: MedicationDosageUnitOptionDao
) : DataAbstractOptionRepository<MedicationDosageUnitOption, MedicationDosageUnitOptionEntity>(dao) {

    override fun MedicationDosageUnitOptionEntity.toModel(): MedicationDosageUnitOption {
        return MedicationDosageUnitOption(
            id = this.dosageUnitOptionId,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun MedicationDosageUnitOption.toEntity(): MedicationDosageUnitOptionEntity {
        return MedicationDosageUnitOptionEntity(
            dosageUnitOptionId = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}
