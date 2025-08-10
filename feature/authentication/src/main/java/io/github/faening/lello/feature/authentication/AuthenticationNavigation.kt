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

object AuthenticationDestinations {
    const val GRAPH = "authentication_graph"
    const val HOME = "authentication_home"
    const val SIGN_IN = "authentication_sign_in"
    const val SIGN_UP = "authentication_sign_up"
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
                onEmailSignUpClick = {},
                onGoogleSignUpClick = {},
                onPrivacyPolicyClick = {},
                onLoginClick = {},
                onRecoverAccountClick = {}
            )
        }

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