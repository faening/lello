package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.github.faening.lello.core.database.model.option.ClimateOptionEntity
import io.github.faening.lello.core.domain.repository.OptionResources
import kotlinx.coroutines.flow.Flow

@Suppress("unused")
@Dao
interface ClimateOptionDao : OptionResources<ClimateOptionEntity> {

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
            ORDER BY description ASC
        """
    )
    override fun getAll(
        useBlockedFilter: Boolean,
        isBlocked: Boolean,
        useActiveFilter: Boolean,
        isActive: Boolean
    ): Flow<List<ClimateOptionEntity>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM climate_options
            WHERE climateOptionId = :id
            LIMIT 1
        """
    )
    override fun getById(id: Long): Flow<ClimateOptionEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    override suspend fun insert(item: ClimateOptionEntity): Long

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    override suspend fun update(item: ClimateOptionEntity)

    @Delete
    override suspend fun delete(item: ClimateOptionEntity)
}