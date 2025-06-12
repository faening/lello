package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.PortionOptionDao
import io.github.faening.lello.core.database.model.option.PortionOptionEntity
import io.github.faening.lello.core.model.journal.PortionOption
import jakarta.inject.Inject

class PortionOptionRepository @Inject constructor(
    dao: PortionOptionDao
) : OptionRepository<PortionOption, PortionOptionEntity>(dao) {

    override fun PortionOptionEntity.toModel(): PortionOption {
        return PortionOption(
            id = this.portionOptionId,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun PortionOption.toEntity(): PortionOptionEntity {
        return PortionOptionEntity(
            portionOptionId = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}
