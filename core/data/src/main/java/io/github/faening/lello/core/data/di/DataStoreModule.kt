package io.github.faening.lello.core.data.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.data.repository.datastore.DataStoreOnboardingRepository
import io.github.faening.lello.core.data.repository.datastore.DataStoreUserRepository
import io.github.faening.lello.core.domain.repository.OnboardingRepository
import io.github.faening.lello.core.domain.repository.UserRepository
import javax.inject.Singleton

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "lello_preferences")

@Module
@InstallIn(SingletonComponent::class)
object PreferencesModule {

    @Provides
    @Singleton
    fun provideDataStore(@ApplicationContext context: Context): DataStore<Preferences> {
        return context.dataStore
    }

    @Provides
    @Singleton
    fun provideDataStoreOnboardingRepository(dataStore: DataStore<Preferences>): OnboardingRepository {
        return DataStoreOnboardingRepository(dataStore)
    }

    @Provides
    @Singleton
    fun provideDataStoreUserRepository(dataStore: DataStore<Preferences>) : UserRepository {
        return DataStoreUserRepository(dataStore)
    }
}
