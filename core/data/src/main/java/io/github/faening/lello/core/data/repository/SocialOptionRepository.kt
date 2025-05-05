package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.SocialOptionDao
import io.github.faening.lello.core.database.model.SocialOptionEntity
import io.github.faening.lello.core.model.journal.SocialOption
import jakarta.inject.Inject

class SocialOptionRepository @Inject constructor(
    dao: SocialOptionDao
) : BaseOptionRepository<SocialOption, SocialOptionEntity>(dao) {

    override fun SocialOptionEntity.toModel(): SocialOption {
        return this.toModel()
    }

    override fun SocialOption.toEntity(): SocialOptionEntity {
        return this.toEntity()
    }
}