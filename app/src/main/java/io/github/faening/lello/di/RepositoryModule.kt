package io.github.faening.lello.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.data.repository.ClimateOptionRepository
import io.github.faening.lello.core.data.repository.EmotionOptionRepository
import io.github.faening.lello.core.data.repository.HealthOptionRepository
import io.github.faening.lello.core.data.repository.JournalCategoryRepository
import io.github.faening.lello.core.data.repository.LocationOptionRepository
import io.github.faening.lello.core.database.dao.ClimateOptionDao
import io.github.faening.lello.core.database.dao.EmotionOptionDao
import io.github.faening.lello.core.database.dao.HealthOptionDao
import io.github.faening.lello.core.database.dao.JournalCategoryDao
import io.github.faening.lello.core.database.dao.LocationOptionDao
import io.github.faening.lello.core.domain.repository.OptionRepository
import io.github.faening.lello.core.domain.repository.ReadOnlyRepository
import io.github.faening.lello.core.model.journal.ClimateOption
import io.github.faening.lello.core.model.journal.EmotionOption
import io.github.faening.lello.core.model.journal.HealthOption
import io.github.faening.lello.core.model.journal.JournalCategory
import io.github.faening.lello.core.model.journal.LocationOption

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
    fun provideJournalCategoryRepository(
        dao: JournalCategoryDao
    ): ReadOnlyRepository<JournalCategory> {
        return JournalCategoryRepository(dao)
    }

    @Provides
    fun provideClimateOptionRepository(
        dao: ClimateOptionDao
    ): OptionRepository<ClimateOption> {
        return ClimateOptionRepository(dao)
    }

    @Provides
    fun provideEmotionOptionRepository(
        dao: EmotionOptionDao
    ): OptionRepository<EmotionOption> {
        return EmotionOptionRepository(dao)
    }

    @Provides
    fun provideHealthOptionRepository(
        dao: HealthOptionDao
    ) : OptionRepository<HealthOption> {
        return HealthOptionRepository(dao)
    }

    @Provides
    fun provideLocationOptionRepository(
        dao: LocationOptionDao
    ) : OptionRepository<LocationOption> {
        return LocationOptionRepository(dao)
    }
}