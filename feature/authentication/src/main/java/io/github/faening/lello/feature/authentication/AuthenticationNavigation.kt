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
import io.github.faening.lello.feature.authentication.screen.EmailSignInScreen
import io.github.faening.lello.feature.authentication.screen.EmailSignUpScreen
import io.github.faening.lello.feature.onboarding.OnboardingDestinations

object AuthenticationDestinations {
    const val GRAPH = "authentication_graph"
    const val HOME = "authentication_home"
    const val SIGN_IN_WITH_EMAIL = "authentication_sign_in_with_email"
    const val SIGN_IN_WITH_GOOGLE = "authentication_sign_in_with_google"
    const val SIGN_UP = "authentication_login_with_mail"
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
                onEmailSignInClick = { navController.navigate(AuthenticationDestinations.SIGN_IN_WITH_EMAIL) },
                onGoogleSignInClick = {},
                onPrivacyPolicyClick = {},
                onEmailSignUpClick = { navController.navigate(AuthenticationDestinations.SIGN_UP) },
                onRecoverAccountClick = {}
            )
        }

        composable(AuthenticationDestinations.SIGN_IN_WITH_EMAIL) { backStackEntry ->
            val viewModel = sharedAuthenticationViewModel(navController, backStackEntry)
            EmailSignInScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onSignInSuccess = { /* Navegar para a próxima tela após o sucesso do login */ },
            )
        }

        composable(AuthenticationDestinations.SIGN_IN_WITH_GOOGLE) { backStackEntry ->
            val viewModel = sharedAuthenticationViewModel(navController, backStackEntry)

        }

        composable(AuthenticationDestinations.SIGN_UP) { backStackEntry ->
            val viewModel = sharedAuthenticationViewModel(navController, backStackEntry)
            EmailSignUpScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onSignUpSuccess = {
                    navController.navigate(OnboardingDestinations.GRAPH) {
                        popUpTo(AuthenticationDestinations.GRAPH) { inclusive = true }
                    }
                },
            )
        }

        composable(AuthenticationDestinations.FORGOT_PASSWORD) { backStackEntry ->
            val viewModel = sharedAuthenticationViewModel(navController, backStackEntry)
            // ForgotPasswordScreen(
            //     viewModel = viewModel,
            //     onBackClick = { navController.popBackStack() },
            //     onRecoverAccountSuccess = { /* Navegar para a próxima tela após o sucesso da recuperação */ }
            // )
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