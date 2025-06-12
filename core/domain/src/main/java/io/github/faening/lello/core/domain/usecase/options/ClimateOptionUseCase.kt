package io.github.faening.lello.core.domain.usecase.options

import io.github.faening.lello.core.domain.repository.OptionResources
import io.github.faening.lello.core.domain.util.capitalizeFirst
import io.github.faening.lello.core.domain.util.validateDescription
import io.github.faening.lello.core.domain.util.validateId
import io.github.faening.lello.core.domain.util.validateNotBlocked
import io.github.faening.lello.core.model.journal.ClimateOption
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ClimateOptionUseCase @Inject constructor(
    private val repository: OptionResources<ClimateOption>
) {

    fun getAll(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<ClimateOption>> {
        return repository.getAll(useBlockedFilter, isBlocked, useActiveFilter, isActive)
    }

    fun getById(id: Long): Flow<ClimateOption>? {
        id.validateId()
        return repository.getById(id)
    }

    suspend fun save(vararg items: ClimateOption) {
        val formattedItems = items.map { item ->
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.insert(item) }
    }

    suspend fun update(vararg items: ClimateOption) {
        val formattedItems = items.map { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
            item.description.validateDescription()
            item.copy(description = item.description.capitalizeFirst())
        }
        formattedItems.forEach { item -> repository.update(item) }
    }

    suspend fun delete(vararg items: ClimateOption) {
        items.forEach { item ->
            item.blocked.validateNotBlocked()
            item.id.validateId()
        }
        items.forEach { item -> repository.delete(item) }
    }
}