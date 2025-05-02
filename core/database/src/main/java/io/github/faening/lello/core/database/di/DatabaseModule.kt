package io.github.faening.lello.core.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.database.DatabaseMigrations
import io.github.faening.lello.core.database.LelloDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun providesLelloDatabase(
        @ApplicationContext context: Context,
    ): LelloDatabase = Room.databaseBuilder(
        context,
        LelloDatabase::class.java,
        "lello-database",
    )
    .addMigrations(
        DatabaseMigrations.MIGRATION_1_2
    )
    .build()
}