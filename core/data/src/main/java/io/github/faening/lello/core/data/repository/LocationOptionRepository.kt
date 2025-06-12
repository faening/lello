package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.LocationOptionDao
import io.github.faening.lello.core.database.model.LocationOptionEntity
import io.github.faening.lello.core.model.journal.LocationOption
import jakarta.inject.Inject

class LocationOptionRepository @Inject constructor(
    dao: LocationOptionDao
) : OptionRepository<LocationOption, LocationOptionEntity>(dao) {

    override fun LocationOptionEntity.toModel(): LocationOption {
        return LocationOption(
            id = this.locationOptionId,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun LocationOption.toEntity(): LocationOptionEntity {
        return LocationOptionEntity(
            locationOptionId = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}