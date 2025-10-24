package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.SleepActivityOptionDao
import io.github.faening.lello.core.database.model.option.SleepActivityOptionEntity
import io.github.faening.lello.core.model.option.SleepActivityOption
import jakarta.inject.Inject

class DataSleepActivityOptionRepository @Inject constructor(
    dao: SleepActivityOptionDao
) : DataAbstractOptionRepository<SleepActivityOption, SleepActivityOptionEntity>(dao) {

    override fun SleepActivityOptionEntity.toModel(): SleepActivityOption {
        return SleepActivityOption(
            id = this.sleepActivityOptionId,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun SleepActivityOption.toEntity(): SleepActivityOptionEntity {
        return SleepActivityOptionEntity(
            sleepActivityOptionId = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}
