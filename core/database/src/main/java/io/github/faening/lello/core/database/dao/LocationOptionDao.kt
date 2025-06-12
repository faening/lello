package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.github.faening.lello.core.database.model.option.LocationOptionEntity
import io.github.faening.lello.core.domain.repository.OptionResources
import kotlinx.coroutines.flow.Flow

@Suppress("unused")
@Dao
interface LocationOptionDao : OptionResources<LocationOptionEntity> {

    /**
     * Busca recursos de localização que correspondem aos parâmetros da consulta. Os parâmetros são opcionais e podem ser
     * combinados para refinar a busca.
     *
     * @param useBlockedFilter Ativa o filtro com base na propriedade `blocked`. O padrão é `false`.
     * @param isBlocked Define se os itens devem estar com o status `blocked` `true` ou `false`. O padrão é `false`.
     * @param useActiveFilter Ativa o filtro com base na propriedade `active`. O padrão é `false`.
     * @param isActive Define se os itens devem estar com o status `active` `true` ou `false`. O padrão é `false`.
     *
     * @return Uma lista de objetos [LocationOptionEntity] que correspondem aos critérios de filtro fornecidos.
     */
    @Transaction
    @Query(
        value = """
            SELECT * FROM location_options
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
    ): Flow<List<LocationOptionEntity>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM location_options
            WHERE locationOptionId = :id
            LIMIT 1
        """
    )
    override fun getById(id: Long): Flow<LocationOptionEntity>

    @Insert(onConflict = OnConflictStrategy.Companion.IGNORE)
    override suspend fun insert(item: LocationOptionEntity): Long

    @Update(onConflict = OnConflictStrategy.Companion.REPLACE)
    override suspend fun update(item: LocationOptionEntity)

    @Delete
    override suspend fun delete(item: LocationOptionEntity)
}