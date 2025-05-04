package io.github.faening.lello.core.database.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.database.LelloDatabase
import io.github.faening.lello.core.database.DatabaseSeeder
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesLelloDatabase(
        @ApplicationContext context: Context,
    ): LelloDatabase = Room
        .databaseBuilder(
            context,
            LelloDatabase::class.java,
            "lello.db",
        )
        .addMigrations()
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                DatabaseSeeder.seedAll(db)
            }
        })
        .build()
}