package io.github.faening.lello.core.domain.di

import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.usecase.authentication.LogoutUseCase

@Module
@InstallIn(SingletonComponent::class)
object AuthenticationUseCaseModule {

    @Provides
    fun provideLogoutUseCase(
        firebase: FirebaseAuth
    ) = LogoutUseCase(firebase)
}