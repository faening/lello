package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.HealthOptionDao
import io.github.faening.lello.core.database.model.option.HealthOptionEntity
import io.github.faening.lello.core.model.option.HealthOption
import jakarta.inject.Inject

class DataHealthOptionRepository @Inject constructor(
    dao: HealthOptionDao
) : DataAbstractOptionRepository<HealthOption, HealthOptionEntity>(dao) {

    override fun HealthOptionEntity.toModel(): HealthOption {
        return HealthOption(
            id = this.healthOptionId,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun HealthOption.toEntity(): HealthOptionEntity {
        return HealthOptionEntity(
            healthOptionId = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}