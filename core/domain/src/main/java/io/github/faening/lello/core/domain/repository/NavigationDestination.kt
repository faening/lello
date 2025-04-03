package io.github.faening.lello.core.domain.repository

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

/**
 * Interface que cada módulo de feature deve implementar para definir sua navegação
 */
interface NavigationDestination {
    /**
     * Rota única para este destino
     */
    val route: String

    /**
     * Rota base para ser usada em navegação "deep link"
     */
    val destination: String
        get() = route

    /**
     * Adiciona esta destino ao NavGraphBuilder
     */
    fun NavGraphBuilder.addDestination(
        navController: NavHostController,
        onBackClick: () -> Unit
    )
}