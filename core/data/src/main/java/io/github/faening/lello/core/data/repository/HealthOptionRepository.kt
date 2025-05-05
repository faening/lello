package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.HealthOptionDao
import io.github.faening.lello.core.database.model.HealthOptionEntity
import io.github.faening.lello.core.model.journal.HealthOption
import jakarta.inject.Inject

class HealthOptionRepository @Inject constructor(
    dao: HealthOptionDao
) : OptionRepository<HealthOption, HealthOptionEntity>(dao) {

    override fun HealthOptionEntity.toModel(): HealthOption {
        return this.toModel()
    }

    override fun HealthOption.toEntity(): HealthOptionEntity {
        return this.toEntity()
    }
}