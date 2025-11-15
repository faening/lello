package io.github.faening.lello.core.database

import android.util.Log
import androidx.sqlite.db.SupportSQLiteDatabase
import io.github.faening.lello.core.database.seed.AppetiteOptionSeed
import io.github.faening.lello.core.database.seed.ClimateOptionSeed
import io.github.faening.lello.core.database.seed.EmotionOptionSeed
import io.github.faening.lello.core.database.seed.FoodOptionSeed
import io.github.faening.lello.core.database.seed.HealthOptionSeed
import io.github.faening.lello.core.database.seed.JournalCategorySeed
import io.github.faening.lello.core.database.seed.LocationOptionSeed
import io.github.faening.lello.core.database.seed.MealOptionSeed
import io.github.faening.lello.core.database.seed.MedicationActiveIngredientSeed
import io.github.faening.lello.core.database.seed.MedicationDosageFormOptionSeed
import io.github.faening.lello.core.database.seed.MedicationDosageUnitOptionSeed
import io.github.faening.lello.core.database.seed.MedicationSkipReasonOptionSeed
import io.github.faening.lello.core.database.seed.PortionOptionSeed
import io.github.faening.lello.core.database.seed.SleepActivityOptionSeed
import io.github.faening.lello.core.database.seed.SleepQualityOptionSeed
import io.github.faening.lello.core.database.seed.SleepSensationOptionSeed
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
        Log.d(TAG, "Iniciando processo de seed do banco de dados...")

        seedAppetiteOptions(db)
        seedClimateOptions(db)
        seedEmotionOptions(db)
        seedFoodOptions(db)
        seedHealthOptions(db)
        seedJournalCategory(db)
        seedLocationOptions(db)
        seedMascotStatus(db)
        seedMealOptions(db)
        seedMedicationActiveIngredientOption(db)
        seedMedicationDosageFormOptions(db)
        seedMedicationDosageUnitOptions(db)
        seedMedicationSkipReasonOption(db)
        seedPortionOptions(db)
        seedRewardBalance(db)
        seedSleepActivityOptions(db)
        seedSleepQualityOptions(db)
        seedSleepSensationOptions(db)
        seedSocialOptions(db)

        Log.d(TAG, "Processo de seed do banco de dados concluído com sucesso!")
    }

    private fun seedAppetiteOptions(db: SupportSQLiteDatabase) {
        for (item in AppetiteOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT OR IGNORE INTO appetite_options (description, blocked, active)
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

    private fun seedClimateOptions(db: SupportSQLiteDatabase) {
        for (item in ClimateOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT OR IGNORE INTO climate_options (description, blocked, active)
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

    private fun seedEmotionOptions(db: SupportSQLiteDatabase) {
        for (item in EmotionOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT OR IGNORE INTO emotion_options (description, blocked, active)
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

    private fun seedFoodOptions(db: SupportSQLiteDatabase) {
        for (item in FoodOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT OR IGNORE INTO food_options (description, blocked, active)
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

    private fun seedHealthOptions(db: SupportSQLiteDatabase) {
        for (item in HealthOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT OR IGNORE INTO health_options (description, blocked, active)
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

    private fun seedJournalCategory(db: SupportSQLiteDatabase) {
        for (item in JournalCategorySeed.data) {
            db.execSQL(
                sql = """
                        INSERT OR IGNORE INTO journal_categories (name, short_description, long_description, blocked, active)
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

    private fun seedLocationOptions(db: SupportSQLiteDatabase) {
        for (item in LocationOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT OR IGNORE INTO location_options (description, blocked, active)
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

    private fun seedMascotStatus(db: SupportSQLiteDatabase) {
        val now = System.currentTimeMillis()
        db.execSQL(
            sql = """
                INSERT OR IGNORE INTO mascot_status (
                    id,
                    vitality,
                    last_updated_at
                ) VALUES (?, ?, ?)
            """.trimIndent(),
            bindArgs = arrayOf(
                1,
                100,
                now
            )
        )
    }

    private fun seedMealOptions(db: SupportSQLiteDatabase) {
        for (item in MealOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT OR IGNORE INTO meal_options (description, blocked, active)
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

    private fun seedMedicationActiveIngredientOption(db: SupportSQLiteDatabase) {
        for (item in MedicationActiveIngredientSeed.data) {
            db.execSQL(
                sql = """
                        INSERT OR IGNORE INTO medication_active_ingredient_options (description, blocked, active)
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

    private fun seedMedicationDosageFormOptions(db: SupportSQLiteDatabase) {
        for (item in MedicationDosageFormOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT OR IGNORE INTO medication_dosage_form_options (description, blocked, active)
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

    private fun seedMedicationDosageUnitOptions(db: SupportSQLiteDatabase) {
        for (item in MedicationDosageUnitOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT OR IGNORE INTO medication_dosage_unit_options (description, blocked, active)
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

    private fun seedMedicationSkipReasonOption(db: SupportSQLiteDatabase) {
        for (item in MedicationSkipReasonOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT OR IGNORE INTO medication_skip_reason_options (description, blocked, active)
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

    private fun seedPortionOptions(db: SupportSQLiteDatabase) {
        for (item in PortionOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT OR IGNORE INTO portion_options (description, blocked, active)
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

    private fun seedRewardBalance(db: SupportSQLiteDatabase) {
        val now = System.currentTimeMillis()
        val initialTime = now - 48L * 60L * 60L * 1000L
        db.execSQL(
            sql = """
                INSERT OR IGNORE INTO reward_balance (
                    id,
                    total_coins,
                    last_mood_reward,
                    last_meal_reward,
                    last_sleep_reward,
                    last_medication_reward,
                    last_daily_achievement_reward
                ) VALUES (?, ?, ?, ?, ?, ?, ?)
            """.trimIndent(),
            bindArgs = arrayOf(
                1,
                0,
                initialTime,
                initialTime,
                initialTime,
                initialTime,
                initialTime
            )
        )
    }

    private fun seedSleepActivityOptions(db: SupportSQLiteDatabase) {
        for (item in SleepActivityOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT OR IGNORE INTO sleep_activity_options (description, blocked, active)
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

    private fun seedSleepQualityOptions(db: SupportSQLiteDatabase) {
        for (item in SleepQualityOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT OR IGNORE INTO sleep_quality_options (description, blocked, active)
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

    private fun seedSleepSensationOptions(db: SupportSQLiteDatabase) {
        for (item in SleepSensationOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT OR IGNORE INTO sleep_sensation_options (description, blocked, active)
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

    private fun seedSocialOptions(db: SupportSQLiteDatabase) {
        for (item in SocialOptionSeed.data) {
            db.execSQL(
                sql = """
                        INSERT OR IGNORE INTO social_options (description, blocked, active)
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