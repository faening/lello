package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.github.faening.lello.core.database.model.HealthOptionEntity
import io.github.faening.lello.core.domain.repository.OptionRepository
import kotlinx.coroutines.flow.Flow

@Suppress("unused")
@Dao
interface HealthOptionDao : OptionRepository<HealthOptionEntity> {

    /**
     * Busca recursos de saúde que correspondem aos parâmetros da consulta. Os parâmetros são opcionais e podem ser
     * combinados para refinar a busca.
     *
     * @param useBlockedFilter Ativa o filtro com base na propriedade `blocked`. O padrão é `false`.
     * @param isBlocked Define se os itens devem estar com o status `blocked` `true` ou `false`. O padrão é `false`.
     * @param useActiveFilter Ativa o filtro com base na propriedade `active`. O padrão é `false`.
     * @param isActive Define se os itens devem estar com o status `active` `true` ou `false`. O padrão é `false`.
     *
     * @return Uma lista de objetos [HealthOptionEntity] que correspondem aos critérios de filtro fornecidos.
     */
    @Transaction
    @Query(
        value = """
            SELECT * FROM health_options
            WHERE 
                CASE WHEN :useBlockedFilter
                    THEN blocked = :isBlocked
                    ELSE 1 END
            AND
                CASE WHEN :useActiveFilter
                    THEN active = :isActive
                    ELSE 1 END
            ORDER BY description ASC
        """
    )
    override fun getAll(
        useBlockedFilter: Boolean,
        isBlocked: Boolean,
        useActiveFilter: Boolean,
        isActive: Boolean
    ): Flow<List<HealthOptionEntity>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM health_options
            WHERE id = :id
            LIMIT 1
        """
    )
    override fun getById(id: Int): Flow<HealthOptionEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    override suspend fun insert(item: HealthOptionEntity)

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    override suspend fun update(item: HealthOptionEntity)

    @Delete
    override suspend fun delete(item: HealthOptionEntity)
}