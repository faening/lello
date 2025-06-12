package io.github.faening.lello.core.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.data.repository.AppetiteOptionRepository
import io.github.faening.lello.core.data.repository.ClimateOptionRepository
import io.github.faening.lello.core.data.repository.DosageFormOptionRepository
import io.github.faening.lello.core.data.repository.EmotionOptionRepository
import io.github.faening.lello.core.data.repository.FoodOptionRepository
import io.github.faening.lello.core.data.repository.HealthOptionRepository
import io.github.faening.lello.core.data.repository.JournalCategoryRepository
import io.github.faening.lello.core.data.repository.LocationOptionRepository
import io.github.faening.lello.core.data.repository.MealOptionRepository
import io.github.faening.lello.core.data.repository.MoodJournalRepository
import io.github.faening.lello.core.data.repository.PortionOptionRepository
import io.github.faening.lello.core.data.repository.SensationOptionRepository
import io.github.faening.lello.core.data.repository.SleepActivityOptionRepository
import io.github.faening.lello.core.data.repository.SleepQualityOptionRepository
import io.github.faening.lello.core.data.repository.SocialOptionRepository
import io.github.faening.lello.core.database.dao.AppetiteOptionDao
import io.github.faening.lello.core.database.dao.ClimateOptionDao
import io.github.faening.lello.core.database.dao.DosageFormOptionDao
import io.github.faening.lello.core.database.dao.EmotionOptionDao
import io.github.faening.lello.core.database.dao.FoodOptionDao
import io.github.faening.lello.core.database.dao.HealthOptionDao
import io.github.faening.lello.core.database.dao.JournalCategoryDao
import io.github.faening.lello.core.database.dao.LocationOptionDao
import io.github.faening.lello.core.database.dao.MealOptionDao
import io.github.faening.lello.core.database.dao.MoodJournalDao
import io.github.faening.lello.core.database.dao.PortionOptionDao
import io.github.faening.lello.core.database.dao.SensationOptionDao
import io.github.faening.lello.core.database.dao.SleepActivityOptionDao
import io.github.faening.lello.core.database.dao.SleepQualityOptionDao
import io.github.faening.lello.core.database.dao.SocialOptionDao
import io.github.faening.lello.core.domain.repository.JournalCategoryResources
import io.github.faening.lello.core.domain.repository.MoodJournalResources
import io.github.faening.lello.core.domain.repository.OptionResources
import io.github.faening.lello.core.model.journal.AppetiteOption
import io.github.faening.lello.core.model.journal.ClimateOption
import io.github.faening.lello.core.model.journal.DosageFormOption
import io.github.faening.lello.core.model.journal.EmotionOption
import io.github.faening.lello.core.model.journal.FoodOption
import io.github.faening.lello.core.model.journal.HealthOption
import io.github.faening.lello.core.model.journal.JournalCategory
import io.github.faening.lello.core.model.journal.LocationOption
import io.github.faening.lello.core.model.journal.MealOption
import io.github.faening.lello.core.model.journal.MoodJournal
import io.github.faening.lello.core.model.journal.PortionOption
import io.github.faening.lello.core.model.journal.SensationOption
import io.github.faening.lello.core.model.journal.SleepActivityOption
import io.github.faening.lello.core.model.journal.SleepQualityOption
import io.github.faening.lello.core.model.journal.SocialOption

/**
 * Módulo responsável por fornecer as implementações de repositórios para o grafo de dependências do Dagger Hilt.
 *
 * Este módulo existe no módulo `app` pois é o único ponto da aplicação que possui visibilidade tanto para as abstrações definidas na
 * camada `domain`, quanto para as implementações localizadas na camada `data`.
 *
 * Como as boas práticas da Clean Architecture recomendam que a camada `domain` seja independente de não tenha conhecimento sobre `data`,
 * sabemos que `data` também não dependa de `app`, o binding das interfaces de repositório com suas implementações precisa ocorrer aqui.
 *
 * Essa abordagem garante que:
 * - O padrão MVVM e a separação de responsabilidades sejam respeitados.
 * - A manutenção e evolução do projeto sejam mais seguras e organizadas.
 * - Evitamos dependências circulares entre os módulos.
 *
 * Outros desenvolvedores devem utilizar este módulo para registrar novas implementações de repositórios sempre que necessário.
 */
@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideMoodJournalRepository(
        dao: MoodJournalDao
    ): MoodJournalResources<MoodJournal> {
        return MoodJournalRepository(dao)
    }

    @Provides
    fun provideJournalCategoryRepository(
        dao: JournalCategoryDao
    ): JournalCategoryResources<JournalCategory> {
        return JournalCategoryRepository(dao)
    }

    @Provides
    fun provideAppetiteOptionRepository(
        dao: AppetiteOptionDao
    ): OptionResources<AppetiteOption> {
        return AppetiteOptionRepository(dao)
    }

    @Provides
    fun provideClimateOptionRepository(
        dao: ClimateOptionDao
    ): OptionResources<ClimateOption> {
        return ClimateOptionRepository(dao)
    }

    @Provides
    fun provideDosageFormOptionRepository(
        dao: DosageFormOptionDao
    ): OptionResources<DosageFormOption> {
        return DosageFormOptionRepository(dao)
    }

    @Provides
    fun provideEmotionOptionRepository(
        dao: EmotionOptionDao
    ): OptionResources<EmotionOption> {
        return EmotionOptionRepository(dao)
    }

    @Provides
    fun provideFoodOptionRepository(
        dao: FoodOptionDao
    ): OptionResources<FoodOption> {
        return FoodOptionRepository(dao)
    }

    @Provides
    fun provideHealthOptionRepository(
        dao: HealthOptionDao
    ) : OptionResources<HealthOption> {
        return HealthOptionRepository(dao)
    }

    @Provides
    fun provideLocationOptionRepository(
        dao: LocationOptionDao
    ) : OptionResources<LocationOption> {
        return LocationOptionRepository(dao)
    }

    @Provides
    fun provideMealOptionRepository(
        dao: MealOptionDao
    ): OptionResources<MealOption> {
        return MealOptionRepository(dao)
    }

    @Provides
    fun providePortionOptionRepository(
        dao: PortionOptionDao
    ): OptionResources<PortionOption> {
        return PortionOptionRepository(dao)
    }

    @Provides
    fun provideSensationOptionRepository(
        dao: SensationOptionDao
    ): OptionResources<SensationOption> {
        return SensationOptionRepository(dao)
    }

    @Provides
    fun provideSleepActivityOptionRepository(
        dao: SleepActivityOptionDao
    ): OptionResources<SleepActivityOption> {
        return SleepActivityOptionRepository(dao)
    }

    @Provides
    fun provideSleepQualityOptionRepository(
        dao: SleepQualityOptionDao
    ): OptionResources<SleepQualityOption> {
        return SleepQualityOptionRepository(dao)
    }

    @Provides
    fun provideSocialOptionRepository(
        dao: SocialOptionDao
    ) : OptionResources<SocialOption> {
        return SocialOptionRepository(dao)
    }
}