package io.github.faening.lello.core.domain.usecase.options.health

import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.domain.util.validateDescription
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.domain.util.validateNotBlocked
import io.github.faening.lello.core.model.option.HealthOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HealthOptionUseCase @Inject constructor(
    private val repository: OptionRepository<HealthOption>
) {

    fun getAll(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<HealthOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }

    fun getById(id: Long): Flow<HealthOption>? {
        id.validateId()
        return repository.getById(id)
    }

    suspend fun save(vararg items: HealthOption) {
        val formattedItems = items.map { item ->
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.insert(item) }
    }

    suspend fun update(vararg items: HealthOption) {
        val formattedItems = items.map { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.update(item) }
    }

    /**
     * Only updates the `active` status of the given options.
     */
    suspend fun updateActiveStatus(vararg items: HealthOption) {
        items.forEach { item ->
            item.id.validateId()
        }
        items.forEach { item -> repository.update(item) }
    }

    suspend fun delete(vararg items: HealthOption) {
        items.forEach { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
        }
        items.forEach { item -> repository.delete(item) }
    }
}