package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.AppetiteOptionDao
import io.github.faening.lello.core.database.model.option.AppetiteOptionEntity
import io.github.faening.lello.core.model.option.AppetiteOption
import jakarta.inject.Inject

class DataAppetiteOptionRepository @Inject constructor(
    dao: AppetiteOptionDao
) : DataAbstractOptionRepository<AppetiteOption, AppetiteOptionEntity>(dao) {

    override fun AppetiteOptionEntity.toModel(): AppetiteOption {
        return AppetiteOption(
            id = this.appetiteOptionId,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun AppetiteOption.toEntity(): AppetiteOptionEntity {
        return AppetiteOptionEntity(
            appetiteOptionId = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}
