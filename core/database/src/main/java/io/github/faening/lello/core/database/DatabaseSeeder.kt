package io.github.faening.lello.core.database

import android.util.Log
import androidx.sqlite.db.SupportSQLiteDatabase
import io.github.faening.lello.core.database.seed.AppetiteOptionSeed
import io.github.faening.lello.core.database.seed.ClimateOptionSeed
import io.github.faening.lello.core.database.seed.DosageFormOptionSeed
import io.github.faening.lello.core.database.seed.EmotionOptionSeed
import io.github.faening.lello.core.database.seed.FoodOptionSeed
import io.github.faening.lello.core.database.seed.HealthOptionSeed
import io.github.faening.lello.core.database.seed.JournalCategorySeed
import io.github.faening.lello.core.database.seed.LocationOptionSeed
import io.github.faening.lello.core.database.seed.MealOptionSeed
import io.github.faening.lello.core.database.seed.PortionOptionSeed
import io.github.faening.lello.core.database.seed.SleepSensationOptionSeed
import io.github.faening.lello.core.database.seed.SleepActivityOptionSeed
import io.github.faening.lello.core.database.seed.SleepQualityOptionSeed
import io.github.faening.lello.core.database.seed.SocialOptionSeed

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

        seedJournalCategory(db)
        seedAppetiteOptions(db)
        seedClimateOptions(db)
        seedDosageFormOptions(db)
        seedEmotionOptions(db)
        seedFoodOptions(db)
        seedHealthOptions(db)
        seedLocationOptions(db)
        seedMealOptions(db)
        seedPortionOptions(db)
        seedSleepActivityOptions(db)
        seedSleepQualityOptions(db)
        seedSleepSensationOptions(db)
        seedSocialOptions(db)

        Log.d(TAG, "Processo de seed do banco de dados concluído com sucesso")
    }

fun seedJournalCategory(db: SupportSQLiteDatabase) {
    for (item in JournalCategorySeed.data) {
        db.execSQL(
            sql = """
                        INSERT INTO journal_categories (name, short_description, long_description, blocked, active)
                        VALUES (?, ?, ?, ?, ?)
                    """.trimIndent(),
                bindArgs = arrayOf(
                    item.name,
                    item.shortDescription,
                    item.longDescription,
                    if (item.blocked) 1 else 0,
                    if (item.active) 1 else 0
                )
        )
    }
}

    fun seedAppetiteOptions(db: SupportSQLiteDatabase) {
        for (item in AppetiteOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT INTO appetite_options (description, blocked, active)
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

fun seedClimateOptions(db: SupportSQLiteDatabase) {
        for (item in ClimateOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT INTO climate_options (description, blocked, active)
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

    fun seedDosageFormOptions(db: SupportSQLiteDatabase) {
        for (item in DosageFormOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT INTO dosage_form_options (description, blocked, active)
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

    fun seedEmotionOptions(db: SupportSQLiteDatabase) {
        for (item in EmotionOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT INTO emotion_options (description, blocked, active)
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

    fun seedFoodOptions(db: SupportSQLiteDatabase) {
        for (item in FoodOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT INTO food_options (description, blocked, active)
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

    fun seedHealthOptions(db: SupportSQLiteDatabase) {
        for (item in HealthOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT INTO health_options (description, blocked, active)
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

    fun seedLocationOptions(db: SupportSQLiteDatabase) {
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

    fun seedMealOptions(db: SupportSQLiteDatabase) {
        for (item in MealOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT INTO meal_options (description, blocked, active)
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

    fun seedPortionOptions(db: SupportSQLiteDatabase) {
        for (item in PortionOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT INTO portion_options (description, blocked, active)
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

    fun seedSleepActivityOptions(db: SupportSQLiteDatabase) {
        for (item in SleepActivityOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT INTO sleep_activity_options (description, blocked, active)
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

    fun seedSleepQualityOptions(db: SupportSQLiteDatabase) {
        for (item in SleepQualityOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT INTO sleep_quality_options (description, blocked, active)
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

    fun seedSleepSensationOptions(db: SupportSQLiteDatabase) {
        for (item in SleepSensationOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT INTO sleep_sensation_options (description, blocked, active)
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

    fun seedSocialOptions(db: SupportSQLiteDatabase) {
        for (item in SocialOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT INTO social_options (description, blocked, active)
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