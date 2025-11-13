package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.MedicationSkipReasonOptionDao
import io.github.faening.lello.core.database.model.option.MedicationSkipReasonOptionEntity
import io.github.faening.lello.core.model.option.MedicationSkipReasonOption
import jakarta.inject.Inject

class DataMedicationSkipReasonOptionRepository @Inject constructor(
    dao: MedicationSkipReasonOptionDao
) : DataAbstractOptionRepository<MedicationSkipReasonOption, MedicationSkipReasonOptionEntity>(dao) {

    override fun MedicationSkipReasonOptionEntity.toModel(): MedicationSkipReasonOption {
        return MedicationSkipReasonOption(
            id = this.skipReasonOptionId,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun MedicationSkipReasonOption.toEntity(): MedicationSkipReasonOptionEntity {
        return MedicationSkipReasonOptionEntity(
            skipReasonOptionId = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}