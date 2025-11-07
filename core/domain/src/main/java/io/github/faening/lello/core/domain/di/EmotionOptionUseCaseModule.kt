package io.github.faening.lello.core.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.usecase.options.emotion.DeleteEmotionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.emotion.GetAllEmotionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.emotion.GetEmotionOptionByIdUseCase
import io.github.faening.lello.core.domain.usecase.options.emotion.SaveEmotionOptionUseCase
import io.github.faening.lello.core.domain.usecase.options.emotion.UpdateEmotionOptionActiveStatusUseCase
import io.github.faening.lello.core.domain.usecase.options.emotion.UpdateEmotionOptionUseCase
import io.github.faening.lello.core.model.option.EmotionOption

@Module
@InstallIn(SingletonComponent::class)
object EmotionOptionUseCaseModule {

    @Provides
    fun provideGetAllEmotionOptionUseCase(
        repository: OptionRepository<EmotionOption>
    ) = GetAllEmotionOptionUseCase(repository)

    @Provides
    fun provideGetEmotionOptionByIdUseCase(
        repository: OptionRepository<EmotionOption>
    ) = GetEmotionOptionByIdUseCase(repository)

    @Provides
    fun provideSaveEmotionOptionUseCase(
        repository: OptionRepository<EmotionOption>
    ) = SaveEmotionOptionUseCase(repository)

    @Provides
    fun provideUpdateEmotionOptionUseCase(
        repository: OptionRepository<EmotionOption>
    ) = UpdateEmotionOptionUseCase(repository)

    @Provides
    fun provideUpdateEmotionOptionActiveStatusUseCase(
        repository: OptionRepository<EmotionOption>
    ) = UpdateEmotionOptionActiveStatusUseCase(repository)

    @Provides
    fun provideDeleteEmotionOptionUseCase(
        repository: OptionRepository<EmotionOption>
    ) = DeleteEmotionOptionUseCase(repository)
}
