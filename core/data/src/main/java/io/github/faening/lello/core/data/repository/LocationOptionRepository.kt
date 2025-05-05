package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.LocationOptionDao
import io.github.faening.lello.core.database.model.LocationOptionEntity
import io.github.faening.lello.core.model.journal.LocationOption
import jakarta.inject.Inject

class LocationOptionRepository @Inject constructor(
    dao: LocationOptionDao
) : BaseOptionRepository<LocationOption, LocationOptionEntity>(dao) {

    override fun LocationOptionEntity.toModel(): LocationOption {
        return this.toModel()
    }

    override fun LocationOption.toEntity(): LocationOptionEntity {
        return this.toEntity()
    }
}