package io.github.faening.lello.core.database.seed

import android.util.Log
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Classe responsável por centralizar a população de dados iniciais no banco de dados.
 * Agrupa lógicas de seed e fornece métodos para registrar cada tipo de dado.
 */
internal object DatabaseSeeder {
    private const val TAG = "DatabaseSeeder"

    /**
     * Popula todas as tabelas do banco de dados com dados iniciais
     * @param db Instância do banco de dados para execução de queries SQL
     */
    fun seedAll(db: SupportSQLiteDatabase) {
        Log.d(TAG, "Iniciando processo de seed do banco de dados")
        seedJournals(db)
        seedEmotions(db)
        Log.d(TAG, "Processo de seed do banco de dados concluído com sucesso")
    }

    /**
     * Popula a tabela 'journals' com dados iniciais
     */
    fun seedJournals(db: SupportSQLiteDatabase) {
        Log.d(TAG, "Populando tabela 'journals' com ${JournalSeed.data.size} registros")
        
        for (journal in JournalSeed.data) {
            db.execSQL(
                """
                    INSERT INTO journals (name, short_description, long_description, blocked, active)
                    VALUES (?, ?, ?, ?, ?)
                """.trimIndent(),
                arrayOf(
                    journal.name,
                    journal.shortDescription,
                    journal.longDescription,
                    if (journal.blocked) 1 else 0,
                    if (journal.active) 1 else 0
                )
            )
        }
        
        Log.d(TAG, "Tabela 'journals' populada com sucesso")
    }

    /**
     * Popula a tabela 'emotions' com dados iniciais
     */
    fun seedEmotions(db: SupportSQLiteDatabase) {
        Log.d(TAG, "Populando tabela 'emotions' com ${EmotionSeed.data.size} registros")
        
        for (emotion in EmotionSeed.data) {
            db.execSQL(
                """
                    INSERT INTO emotions (word, blocked, active)
                    VALUES (?, ?, ?)
                """.trimIndent(),
                arrayOf(
                    emotion.word,
                    if (emotion.blocked) 1 else 0,
                    if (emotion.active) 1 else 0
                )
            )
        }
        
        Log.d(TAG, "Tabela 'emotions' populada com sucesso")
    }
}