package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import io.github.faening.lello.core.database.dao.base.WritableRoom
import io.github.faening.lello.core.database.model.ClimateOptionEntity
import kotlinx.coroutines.flow.Flow

@Suppress("unused")
@Dao
interface ClimateOptionDao : WritableRoom<ClimateOptionEntity> {

    /**
     * Busca recursos de clima que correspondem aos parâmetros da consulta. Os parâmetros são opcionais e podem ser
     * combinados para refinar a busca.
     *
     * @param useBlockedFilter Ativa o filtro com base na propriedade `blocked`. O padrão é `false`.
     * @param isBlocked Define se os itens devem estar com o status `blocked` `true` ou `false`. O padrão é `false`.
     * @param useActiveFilter Ativa o filtro com base na propriedade `active`. O padrão é `false`.
     * @param isActive Define se os itens devem estar com o status `active` `true` ou `false`. O padrão é `false`.
     *
     * @return Uma lista de objetos [ClimateOptionEntity] que correspondem aos critérios de filtro fornecidos.
     */
    @Transaction
    @Query(
        value = """
            SELECT * FROM climate_options
            WHERE 
                CASE WHEN :useBlockedFilter
                    THEN blocked = :isBlocked
                    ELSE 1 END
            AND
                CASE WHEN :useActiveFilter
                    THEN active = :isActive
                    ELSE 1 END
            ORDER BY id ASC
        """
    )
    fun getAll(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = true,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<ClimateOptionEntity>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM climate_options
            WHERE id = :id
            LIMIT 1
        """
    )
    fun get(id: Int): Flow<ClimateOptionEntity>
}