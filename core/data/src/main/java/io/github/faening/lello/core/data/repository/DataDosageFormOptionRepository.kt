package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.DosageFormOptionDao
import io.github.faening.lello.core.database.model.option.DosageFormOptionEntity
import io.github.faening.lello.core.model.option.DosageFormOption
import jakarta.inject.Inject

class DataDosageFormOptionRepository @Inject constructor(
    dao: DosageFormOptionDao
) : DataAbstractOptionRepository<DosageFormOption, DosageFormOptionEntity>(dao) {

    override fun DosageFormOptionEntity.toModel(): DosageFormOption {
        return DosageFormOption(
            id = this.dosageFormOptionId,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun DosageFormOption.toEntity(): DosageFormOptionEntity {
        return DosageFormOptionEntity(
            dosageFormOptionId = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}
