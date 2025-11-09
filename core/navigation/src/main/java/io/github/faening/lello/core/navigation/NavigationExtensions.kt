package io.github.faening.lello.core.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

object NavigationTransitions {
    /**
     * Cria uma animação de transição de entrada com efeito fade in.
     *
     * @param animationSpec Duração da animação em milissegundos (default: 600).
     * @return Lambda que aplica a transição de entrada usando `fadeIn`.
     */
    fun fadeIn(
        animationSpec: Int = 600
    ): AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
        {
            fadeIn(animationSpec = tween(animationSpec))
        }

    /**
     * Cria uma animação de transição de saída com efeito fade out.
     *
     * @param animationSpec Duração da animação em milissegundos (default: 600).
     * @return Lambda que aplica a transição de saída usando `fadeOut`.
     */
    fun fadeOut(
        animationSpec: Int = 600
    ): AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
        {
            fadeOut(animationSpec = tween(animationSpec))
        }

    /**
     * Cria uma animação de transição de entrada deslizando o conteúdo da borda final para o início.
     *
     * @param animationSpec Duração da animação em milissegundos \(default: 600\).
     * @return Lambda que aplica a transição de entrada usando `slideIntoContainer` na direção `Start`.
     *
     * Utiliza o `AnimatedContentTransitionScope` para animar a entrada de um novo conteúdo na navegação,
     * deslizando da borda final \(direita em layouts LTR\) para o início \(esquerda\).
     */
    fun slideInFromEnd(
        animationSpec: Int = 600
    ): AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
        {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(animationSpec)
            )
        }

    /**
     * Cria uma animação de transição de entrada deslizando o conteúdo da borda inicial para o final.
     *
     * @param animationSpec Duração da animação em milissegundos \(default: 600\).
     * @return Lambda que aplica a transição de entrada usando `slideIntoContainer` na direção `End`.
     *
     * Utiliza o `AnimatedContentTransitionScope` para animar a entrada de um novo conteúdo na navegação,
     * deslizando da borda inicial \(esquerda em layouts LTR\) para o final \(direita\).
     */
    fun slideInFromStart(
        animationSpec: Int = 600
    ): AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition =
        {
            slideIntoContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(animationSpec)
            )
        }

    /**
     * Cria uma animação de transição de saída deslizando o conteúdo para a borda final.
     *
     * @param animationSpec Duração da animação em milissegundos \(default: 600\).
     * @return Lambda que aplica a transição de saída usando `slideOutOfContainer`
     *
     * Utiliza o `AnimatedContentTransitionScope` para animar a saída do conteúdo atual na navegação,
     * deslizando para a borda final \(direita em layouts LTR\).
     */
    fun slideOutToEnd(
        animationSpec: Int = 600
    ): AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
        {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
                animationSpec = tween(animationSpec)
            )
        }

    /**
     * Cria uma animação de transição de saída deslizando o conteúdo para a borda inicial.
     *
     * @param animationSpec Duração da animação em milissegundos \(default: 600\).
     * @return Lambda que aplica a transição de saída usando `slideOutOfContainer`
     *
     * Utiliza o `AnimatedContentTransitionScope` para animar a saída do conteúdo atual na navegação,
     * deslizando para a borda inicial \(esquerda em layouts LTR\).
     */
    fun slideOutToStart(
        animationSpec: Int = 600
    ): AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition =
        {
            slideOutOfContainer(
                towards = AnimatedContentTransitionScope.SlideDirection.End,
                animationSpec = tween(animationSpec)
            )
        }
}

fun NavGraphBuilder.customComposable(
    route: String,
    animationSpec: Int = 600,
    enterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition)?
        = NavigationTransitions.slideInFromEnd(animationSpec),
    exitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition)?
        = NavigationTransitions.slideOutToEnd(animationSpec),
    popEnterTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition)?
        = NavigationTransitions.slideInFromStart(animationSpec),
    popExitTransition: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition)?
        = NavigationTransitions.slideOutToStart(animationSpec),
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        route = route,
        enterTransition = enterTransition,
        exitTransition = exitTransition,
        popEnterTransition = popEnterTransition,
        popExitTransition = popExitTransition,
        content = content
    )
}