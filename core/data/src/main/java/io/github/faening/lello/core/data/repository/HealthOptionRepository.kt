package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.HealthOptionDao
import io.github.faening.lello.core.database.model.HealthOptionEntity
import io.github.faening.lello.core.model.journal.HealthOption
import jakarta.inject.Inject

class HealthOptionRepository @Inject constructor(
    dao: HealthOptionDao
) : OptionRepository<HealthOption, HealthOptionEntity>(dao) {

    override fun HealthOptionEntity.toModel(): HealthOption {
        return HealthOption(
            id = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun HealthOption.toEntity(): HealthOptionEntity {
        return HealthOptionEntity(
            id = this.id ?: 0,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}