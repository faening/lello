package io.github.faening.lello.feature.authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.faening.lello.feature.authentication.screen.AuthenticationScreen
import io.github.faening.lello.feature.authentication.screen.EmailSignUpScreen

object AuthenticationDestinations {
    const val GRAPH = "authentication_graph"
    const val HOME = "authentication_home"
    const val SIGN_IN = "authentication_sign_in"
    const val SIGN_UP_WITH_MAIL = "authentication_sign_up_with_mail"
    const val SIGN_UP_WITH_GOOGLE = "authentication_sign_up_with_google"
    const val FORGOT_PASSWORD = "authentication_forgot_password"
}

fun NavGraphBuilder.authenticationGraph(navController: NavHostController) {
    navigation(
        startDestination = AuthenticationDestinations.HOME,
        route = AuthenticationDestinations.GRAPH
    ) {
        composable(AuthenticationDestinations.HOME) { backStackEntry ->
            val viewModel = sharedAuthenticationViewModel(navController, backStackEntry)
            AuthenticationScreen(
                viewModel = viewModel,
                onEmailSignUpClick = { navController.navigate(AuthenticationDestinations.SIGN_UP_WITH_MAIL) },
                onGoogleSignUpClick = {},
                onPrivacyPolicyClick = {},
                onLoginClick = {},
                onRecoverAccountClick = {}
            )
        }

        composable(AuthenticationDestinations.SIGN_UP_WITH_MAIL) { backStackEntry ->
            val viewModel = sharedAuthenticationViewModel(navController, backStackEntry)
            EmailSignUpScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onSignUpSuccess = { /* Navegar para a próxima tela após o sucesso do cadastro */ },
                onLoginClick = {}
            )
        }

        // ...

    }
}

@Composable
fun sharedAuthenticationViewModel(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
): AuthenticationViewModel {
    val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(AuthenticationDestinations.GRAPH)
    }
    return hiltViewModel(parentEntry)
}