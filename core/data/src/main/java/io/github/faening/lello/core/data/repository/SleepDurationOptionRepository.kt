package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.SleepDurationOptionDao
import io.github.faening.lello.core.database.model.option.SleepDurationOptionEntity
import io.github.faening.lello.core.model.option.SleepDurationOption
import jakarta.inject.Inject

class SleepDurationOptionRepository @Inject constructor(
    dao: SleepDurationOptionDao
) : OptionRepository<SleepDurationOption, SleepDurationOptionEntity>(dao) {

    override fun SleepDurationOptionEntity.toModel(): SleepDurationOption {
        return SleepDurationOption(
            id = this.sleepDurationOptionId,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun SleepDurationOption.toEntity(): SleepDurationOptionEntity {
        return SleepDurationOptionEntity(
            sleepDurationOptionId = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}
