package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.usecase.options.social.DeleteSocialOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.social.GetAllSocialOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.social.GetSocialOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.social.SaveSocialOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.social.UpdateSocialOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.social.UpdateSocialOptionUseCase
import io.github.faening.lello.core.model.option.SocialOption

@Module
@InstallIn(SingletonComponent::class)
object SocialOptionUseCaseModule {

    @Provides
    fun provideGetAllSocialOptionUseCase(
        repository: OptionRepository<SocialOption>
    ) = GetAllSocialOptionUseCase(repository)

    @Provides
    fun provideGetSocialOptionByIdUseCase(
        repository: OptionRepository<SocialOption>
    ) = GetSocialOptionByIdUseCase(repository)

    @Provides
    fun provideSaveSocialOptionUseCase(
        repository: OptionRepository<SocialOption>
    ) = SaveSocialOptionUseCase(repository)

    @Provides
    fun provideUpdateSocialOptionUseCase(
        repository: OptionRepository<SocialOption>
    ) = UpdateSocialOptionUseCase(repository)

    @Provides
    fun provideUpdateSocialOptionActiveStatusUseCase(
        repository: OptionRepository<SocialOption>
    ) = UpdateSocialOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteSocialOptionUseCase(
        repository: OptionRepository<SocialOption>
    ) = DeleteSocialOptionUseCase(repository)
}
