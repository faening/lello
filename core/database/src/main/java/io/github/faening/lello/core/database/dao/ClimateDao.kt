package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.github.faening.lello.core.database.model.ClimateEntity
import kotlinx.coroutines.flow.Flow

@Suppress("unused")
@Dao
interface ClimateDao {

    /**
     * Busca recursos de clima que correspondem aos parâmetros da consulta. Os parâmetros são opcionais e podem ser
     * combinados para refinar a busca.
     *
     * @param useBlockedFilter Ativa o filtro com base na propriedade `blocked`. O padrão é `false`.
     * @param isBlocked Define se os itens devem estar com o status `blocked` `true` ou `false`. O padrão é `false`.
     * @param useActiveFilter Ativa o filtro com base na propriedade `active`. O padrão é `false`.
     * @param isActive Define se os itens devem estar com o status `active` `true` ou `false`. O padrão é `false`.
     *
     * @return Uma lista de objetos [ClimateEntity] que correspondem aos critérios de filtro fornecidos.
     */
    @Transaction
    @Query(
        value = """
            SELECT * FROM climates
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
    ): Flow<List<ClimateEntity>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM climates
            WHERE id = :id
            LIMIT 1
        """
    )
    fun get(id: Long): Flow<ClimateEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg climates: ClimateEntity)

    @Update(onConflict = OnConflictStrategy.IGNORE)
    fun update(vararg climates: ClimateEntity)

    @Delete
    fun delete(vararg climates: ClimateEntity)
}