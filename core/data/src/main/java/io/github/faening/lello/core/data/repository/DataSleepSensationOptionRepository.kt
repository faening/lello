package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.SleepSensationOptionDao
import io.github.faening.lello.core.database.model.option.SleepSensationOptionEntity
import io.github.faening.lello.core.model.option.SleepSensationOption
import jakarta.inject.Inject

class DataSleepSensationOptionRepository @Inject constructor(
    dao: SleepSensationOptionDao
) : DataAbstractOptionRepository<SleepSensationOption, SleepSensationOptionEntity>(dao) {

    override fun SleepSensationOptionEntity.toModel(): SleepSensationOption {
        return SleepSensationOption(
            id = this.sleepSensationOptionId,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun SleepSensationOption.toEntity(): SleepSensationOptionEntity {
        return SleepSensationOptionEntity(
            sleepSensationOptionId = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}
