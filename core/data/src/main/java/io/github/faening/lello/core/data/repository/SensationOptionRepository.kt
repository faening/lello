package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.SensationOptionDao
import io.github.faening.lello.core.database.model.option.SensationOptionEntity
import io.github.faening.lello.core.model.journal.SensationOption
import jakarta.inject.Inject

class SensationOptionRepository @Inject constructor(
    dao: SensationOptionDao
) : OptionRepository<SensationOption, SensationOptionEntity>(dao) {

    override fun SensationOptionEntity.toModel(): SensationOption {
        return SensationOption(
            id = this.sensationOptionId,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun SensationOption.toEntity(): SensationOptionEntity {
        return SensationOptionEntity(
            sensationOptionId = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}
