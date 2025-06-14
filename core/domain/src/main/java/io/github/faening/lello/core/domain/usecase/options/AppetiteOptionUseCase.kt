package io.github.faening.lello.core.domain.usecase.options

import io.github.faening.lello.core.domain.repository.OptionResources
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.domain.util.validateDescription
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.domain.util.validateNotBlocked
import io.github.faening.lello.core.model.option.AppetiteOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppetiteOptionUseCase @Inject constructor(
    private val repository: OptionResources<AppetiteOption>
) {

    fun getAll(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<AppetiteOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }

    fun getById(id: Long): Flow<AppetiteOption>? {
        id.validateId()
        return repository.getById(id)
    }

    suspend fun save(vararg items: AppetiteOption) {
        val formattedItems = items.map { item ->
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.insert(item) }
    }

    suspend fun update(vararg items: AppetiteOption) {
        val formattedItems = items.map { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.update(item) }
    }

    /**
     * Update only the active status for the provided options.
     */
    suspend fun updateActiveStatus(vararg items: AppetiteOption) {
        items.forEach { item ->
            item.id.validateId()
        }
        items.forEach { item -> repository.update(item) }
    }

    suspend fun delete(vararg items: AppetiteOption) {
        items.forEach { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
        }
        items.forEach { item -> repository.delete(item) }
    }
}
