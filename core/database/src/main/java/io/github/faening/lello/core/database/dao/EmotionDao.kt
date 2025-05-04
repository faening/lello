package io.github.faening.lello.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import io.github.faening.lello.core.database.model.EmotionEntity
import kotlinx.coroutines.flow.Flow

@Suppress("unused")
@Dao
interface EmotionDao {

    /**
     * Obtém todas as emoções armazenadas no banco de dados.
     *
     * @param useBlockedFilter Se verdadeiro, filtra as palavras com base no status de bloqueio.
     * @param isBlocked Define se as palavras devem estar bloqueadas (`true`) ou não bloqueadas (`false`). Padrão é `false`.
     * @param useActiveFilter Se verdadeiro, filtra as palavras com base no status ativo.
     * @param isActive Define se as palavras devem estar ativas (`true`) ou inativas (`false`). Padrão é `true`.
     * @return Uma lista de objetos [EmotionEntity] que correspondem aos critérios de filtro fornecidos.
     */
    @Transaction
    @Query(
        value = """
            SELECT * FROM emotions
            WHERE 
                CASE WHEN :useBlockedFilter
                    THEN blocked = :isBlocked
                    ELSE 1 END
            AND
                CASE WHEN :useActiveFilter
                    THEN active = :isActive
                    ELSE 1 END
            ORDER BY word ASC
        """
    )
    fun getEmotions(
        useBlockedFilter: Boolean = false,
        isBlocked: Boolean = false,
        useActiveFilter: Boolean = false,
        isActive: Boolean = true
    ): Flow<List<EmotionEntity>>

    @Transaction
    @Query(value = "SELECT * FROM emotions WHERE id = :id")
    fun getEmotion(id: Long): Flow<EmotionEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmotion(entity: EmotionEntity): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertEmotions(entities: List<EmotionEntity>): List<Long>

    @Update
    suspend fun updateEmotion(entity: EmotionEntity)

    @Delete
    suspend fun deleteEmotion(entity: EmotionEntity)
}