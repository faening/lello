package io.github.faening.lello.core.domain.usecase

import io.github.faening.lello.core.domain.repository.OptionResources
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.domain.util.validateDescription
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.domain.util.validateNotBlocked
import io.github.faening.lello.core.model.journal.EmotionOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class EmotionOptionUseCase @Inject constructor(
    private val repository: OptionResources<EmotionOption>
) {

    fun getAll(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<EmotionOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }

    fun getById(id: Int): Flow<EmotionOption>? {
        id.validateId()
        return repository.getById(id)
    }

    suspend fun save(vararg items: EmotionOption) {
        val formattedItems = items.map { item ->
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.insert(item) }
    }

    suspend fun update(vararg items: EmotionOption) {
        val formattedItems = items.map { item ->
            item.blocked.validateNotBlocked()
            item.id?.validateId()
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.update(item) }
    }

    suspend fun delete(vararg items: EmotionOption) {
        items.forEach { item ->
            item.blocked.validateNotBlocked()
            item.id?.validateId()
        }
        items.forEach { item -> repository.delete(item) }
    }
}