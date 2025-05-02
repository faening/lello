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

@Dao
interface JournalDao {

    @Transaction
    @Query(value = "SELECT * FROM journals")
    fun getJournals(): Flow<List<JournalEntity>>

    @Transaction
    @Query(
        value = """
            SELECT * FROM journals
            WHERE id = :id
        """
    )
    fun getJournal(id: Long): Flow<JournalEntity>?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertJournal(journal: JournalEntity): Long

    @Update
    fun updateJournal(journal: JournalEntity)

    @Delete
    fun deleteJournal(journal: JournalEntity)
}