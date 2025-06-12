package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.SleepActivityOptionDao
import io.github.faening.lello.core.database.model.option.SleepActivityOptionEntity
import io.github.faening.lello.core.model.journal.SleepActivityOption
import jakarta.inject.Inject

class SleepActivityOptionRepository @Inject constructor(
    dao: SleepActivityOptionDao
) : OptionRepository<SleepActivityOption, SleepActivityOptionEntity>(dao) {

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
