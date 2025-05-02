package io.github.faening.lello.core.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

internal object DatabaseMigrations {

    val MIGRATION_1_2 = object : Migration(1, 2) {
        override fun migrate(db: SupportSQLiteDatabase) {
            db.execSQL("""
                INSERT INTO Diary (name, description, locked, active, imageUrl) 
                VALUES ('Humor', 'Registre como você está se sentindo neste momento', 1, 1, null)
                VALUES ('Medicamentos', 'Acompanhe seus medicamentos e não esqueça de nenhuma dose', 1, 1, null)
            """)
        }
    }
}