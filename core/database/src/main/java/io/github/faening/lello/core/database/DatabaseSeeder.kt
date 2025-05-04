package io.github.faening.lello.core.database

import android.util.Log
import androidx.sqlite.db.SupportSQLiteDatabase
import io.github.faening.lello.core.database.seed.ClimateSeed
import io.github.faening.lello.core.database.seed.EmotionSeed
import io.github.faening.lello.core.database.seed.JournalSeed
import io.github.faening.lello.core.database.seed.LocationOptionSeed

/**
 * Classe responsável por centralizar a população de dados iniciais no banco de dados.
 * Agrupa lógicas de seed e fornece métodos para registrar cada tipo de dado.
 */
@Suppress("TYPE_INTERSECTION_AS_REIFIED_WARNING")
internal object DatabaseSeeder {
    private const val TAG = "DatabaseSeeder"

    /**
     * Popula todas as tabelas do banco de dados com dados iniciais
     * @param db Instância do banco de dados para execução de queries SQL
     */
    fun seedAll(db: SupportSQLiteDatabase) {
        Log.d(TAG, "Iniciando processo de seed do banco de dados")

        seedClimates(db)
        seedEmotions(db)
        seedJournals(db)
        seedLocationOption(db)

        Log.d(TAG, "Processo de seed do banco de dados concluído com sucesso")
    }

    fun seedClimates(db: SupportSQLiteDatabase) {
        for (climate in ClimateSeed.data) {
            db.execSQL(
                sql = """
                        INSERT INTO climates (weather_type, blocked, active)
                        VALUES (?, ?, ?)
                    """.trimIndent(),
                bindArgs = arrayOf(
                    climate.weatherType,
                    if (climate.blocked) 1 else 0,
                    if (climate.active) 1 else 0
                )
            )
        }
    }

    fun seedEmotions(db: SupportSQLiteDatabase) {
        for (emotion in EmotionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT INTO emotions (word, blocked, active)
                        VALUES (?, ?, ?)
                    """.trimIndent(),
                bindArgs = arrayOf(
                    emotion.word,
                    if (emotion.blocked) 1 else 0,
                    if (emotion.active) 1 else 0
                )
            )
        }
    }

    fun seedJournals(db: SupportSQLiteDatabase) {
        for (journal in JournalSeed.data) {
            db.execSQL(
                sql = """
                        INSERT INTO journals (name, short_description, long_description, blocked, active)
                        VALUES (?, ?, ?, ?, ?)
                    """.trimIndent(),
                bindArgs = arrayOf(
                    journal.name,
                    journal.shortDescription,
                    journal.longDescription,
                    if (journal.blocked) 1 else 0,
                    if (journal.active) 1 else 0
                )
            )
        }
    }

    fun seedLocationOption(db: SupportSQLiteDatabase) {
        for (item in LocationOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT INTO location_options (description, blocked, active)
                        VALUES (?, ?, ?)
                    """.trimIndent(),
                bindArgs = arrayOf(
                    item.description,
                    if (item.blocked) 1 else 0,
                    if (item.active) 1 else 0
                )
            )
        }
    }
}