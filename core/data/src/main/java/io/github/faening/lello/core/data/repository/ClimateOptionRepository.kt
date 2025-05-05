package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.ClimateOptionDao
import io.github.faening.lello.core.database.model.ClimateOptionEntity
import io.github.faening.lello.core.model.journal.ClimateOption
import jakarta.inject.Inject

class ClimateOptionRepository @Inject constructor(
    dao: ClimateOptionDao
) : OptionRepository<ClimateOption, ClimateOptionEntity>(dao) {

    override fun ClimateOptionEntity.toModel(): ClimateOption {
        return this.toModel()
    }

    override fun ClimateOption.toEntity(): ClimateOptionEntity {
        return this.toEntity()
    }
}