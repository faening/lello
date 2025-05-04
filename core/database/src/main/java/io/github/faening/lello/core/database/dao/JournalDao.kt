package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.github.faening.lello.core.database.model.JournalEntity
import kotlinx.coroutines.flow.Flow

@Suppress("unused")
@Dao
interface JournalDao {

    /**
     * Obtém todas os diários armazenadas no banco de dados.
     *
     * @param useBlockedFilter Se verdadeiro, filtra os diários com base no status de bloqueio.
     * @param isBlocked Define se os diários devem estar bloqueadas (`true`) ou não bloqueadas (`false`). Padrão é `false`.
     * @param useActiveFilter Se verdadeiro, filtra os diários com base no status ativo.
     * @param isActive Define se os diários devem estar ativos (`true`) ou inativas (`false`). Padrão é `true`.
     * @return Uma lista de objetos [JournalEntity] que correspondem aos critérios de filtro fornecidos.
     */
    @Transaction
    @Query(
        value = """
            SELECT * FROM journals
            WHERE 
                CASE WHEN :useBlockedFilter
                    THEN blocked = :isBlocked
                    ELSE 1 END
            AND
                CASE WHEN :useActiveFilter
                    THEN active = :isActive
                    ELSE 1 END
            ORDER BY name ASC
        """
    )
    fun getJournals(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = false,
        useActiveFilter: Boolean = true,
        isActive: Boolean = true
    ): Flow<List<JournalEntity>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM journals
            WHERE id = :id
        """
    )
    fun getJournal(id: Long): Flow<JournalEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJournal(journal: JournalEntity) : Long

    @Update
    fun updateJournal(journal: JournalEntity)

    @Delete
    fun deleteJournal(journal: JournalEntity)
}