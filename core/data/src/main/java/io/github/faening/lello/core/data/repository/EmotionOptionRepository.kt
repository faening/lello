package io.github.faening.lello.core.data.repository

import io.github.faening.lello.core.database.dao.EmotionOptionDao
import io.github.faening.lello.core.database.model.EmotionOptionEntity
import io.github.faening.lello.core.model.journal.EmotionOption
import jakarta.inject.Inject

class EmotionOptionRepository @Inject constructor(
    dao: EmotionOptionDao
) : OptionRepository<EmotionOption, EmotionOptionEntity>(dao) {

    override fun EmotionOptionEntity.toModel(): EmotionOption {
        return this.toModel()
    }

    override fun EmotionOption.toEntity(): EmotionOptionEntity {
        return this.toEntity()
    }
}