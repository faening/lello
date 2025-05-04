package io.github.faening.lello.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.faening.lello.core.data.repository.JournalCategoryRepository
import io.github.faening.lello.core.database.dao.JournalCategoryDao
import io.github.faening.lello.core.domain.repository.ReadOnlyRepository
import io.github.faening.lello.core.model.journal.JournalCategory

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
}