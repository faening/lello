package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.SleepQualityOptionDao
import io.github.faening.lello.core.database.model.option.SleepQualityOptionEntity
import io.github.faening.lello.core.model.option.SleepQualityOption
import jakarta.inject.Inject

class SleepQualityOptionRepository @Inject constructor(
    dao: SleepQualityOptionDao
) : OptionRepository<SleepQualityOption, SleepQualityOptionEntity>(dao) {

    override fun SleepQualityOptionEntity.toModel(): SleepQualityOption {
        return SleepQualityOption(
            id = this.sleepQualityOptionId,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun SleepQualityOption.toEntity(): SleepQualityOptionEntity {
        return SleepQualityOptionEntity(
            sleepQualityOptionId = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}
