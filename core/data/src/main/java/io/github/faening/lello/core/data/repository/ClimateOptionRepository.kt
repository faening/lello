package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.ClimateOptionDao
import io.github.faening.lello.core.database.model.ClimateOptionEntity
import io.github.faening.lello.core.model.journal.ClimateOption
import jakarta.inject.Inject

class ClimateOptionRepository @Inject constructor(
    dao: ClimateOptionDao
) : OptionRepository<ClimateOption, ClimateOptionEntity>(dao) {

    override fun ClimateOptionEntity.toModel(): ClimateOption {
        return ClimateOption(
            id = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun ClimateOption.toEntity(): ClimateOptionEntity {
        return ClimateOptionEntity(
            id = this.id ?: 0,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}