package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.SocialOptionDao
import io.github.faening.lello.core.database.model.option.SocialOptionEntity
import io.github.faening.lello.core.model.option.SocialOption
import jakarta.inject.Inject

class SocialOptionRepository @Inject constructor(
    dao: SocialOptionDao
) : OptionRepository<SocialOption, SocialOptionEntity>(dao) {

    override fun SocialOptionEntity.toModel(): SocialOption {
        return SocialOption(
            id = this.socialOptionId,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }

    override fun SocialOption.toEntity(): SocialOptionEntity {
        return SocialOptionEntity(
            socialOptionId = this.id,
            description = this.description,
            blocked = this.blocked,
            active = this.active
        )
    }
}