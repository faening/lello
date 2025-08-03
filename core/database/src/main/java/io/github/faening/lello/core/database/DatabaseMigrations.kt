package io.github.faening.lello.core.database

internal object DatabaseMigrations {

    val MIGRATION_1_2 = object : androidx.room.migration.Migration(1, 2) {
        override fun migrate(db: androidx.sqlite.db.SupportSQLiteDatabase) {
            db.execSQL(
                """CREATE TABLE IF NOT EXISTS item_catalog (
                    id TEXT NOT NULL PRIMARY KEY,
                    type TEXT NOT NULL,
                    name TEXT NOT NULL,
                    description TEXT NOT NULL,
                    price INTEGER NOT NULL,
                    iconRes TEXT NOT NULL
                )"""
            )
            db.execSQL(
                """CREATE TABLE IF NOT EXISTS inventory (
                    itemId TEXT NOT NULL PRIMARY KEY,
                    quantity INTEGER NOT NULL,
                    acquiredAt INTEGER NOT NULL
                )"""
            )
            db.execSQL(
                """CREATE TABLE IF NOT EXISTS purchase_history (
                    id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
                    item_id TEXT NOT NULL,
                    amount INTEGER NOT NULL,
                    purchased_at INTEGER NOT NULL,
                    price INTEGER NOT NULL
                )"""
            )
        }
    }

}