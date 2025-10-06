package io.github.faening.lello.feature.authentication

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import io.github.faening.lello.feature.authentication.screen.AuthenticationScreen
import io.github.faening.lello.feature.authentication.screen.BiometricAuthenticationScreen
import io.github.faening.lello.feature.authentication.screen.EmailSignInScreen
import io.github.faening.lello.feature.authentication.screen.EmailSignUpScreen
import io.github.faening.lello.feature.authentication.screen.ForgotPasswordScreen
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

object AuthenticationDestinations {
    const val GRAPH = "authentication_graph"
    const val HOME = "authentication_home"
    const val BIOMETRIC_AUTHENTICATION = "authentication_biometric"
    const val SIGN_IN_WITH_EMAIL = "authentication_sign_in_with_email"
    const val SIGN_UP_WITH_EMAIL = "authentication_sign_up_with_email"
    const val FORGOT_PASSWORD = "authentication_forgot_password"
}

fun NavGraphBuilder.authenticationGraph(
    navController: NavHostController,
    isReauthentication: Boolean = false,
    canUseBiometricAuth: Boolean = true
) {
    val startDestination = if (isReauthentication) {
        if (canUseBiometricAuth) {
            AuthenticationDestinations.BIOMETRIC_AUTHENTICATION
        } else {
            AuthenticationDestinations.SIGN_IN_WITH_EMAIL
        }
    } else {
        AuthenticationDestinations.HOME
    }

    navigation(
        startDestination = startDestination,
        route = AuthenticationDestinations.GRAPH
    ) {
        composable(AuthenticationDestinations.HOME) { backStackEntry ->
            val viewModel = sharedAuthenticationViewModel(navController, backStackEntry)

            AuthenticationScreen(
                viewModel = viewModel,
                onEmailSignInClick = { navController.navigate(AuthenticationDestinations.SIGN_IN_WITH_EMAIL) },
                onPrivacyPolicyClick = {},
                onEmailSignUpClick = { navController.navigate(AuthenticationDestinations.SIGN_UP_WITH_EMAIL) },
                onRecoverAccountClick = { navController.navigate(AuthenticationDestinations.FORGOT_PASSWORD) }
            )
        }

        composable(AuthenticationDestinations.BIOMETRIC_AUTHENTICATION) { backStackEntry ->
            val viewModel = sharedAuthenticationViewModel(navController, backStackEntry)
            val coroutineScope = rememberCoroutineScope()

            BiometricAuthenticationScreen(
                viewModel = viewModel,
                onNavigateToEmailSignIn = {
                    navController.navigate(AuthenticationDestinations.SIGN_IN_WITH_EMAIL) {
                        popUpTo(AuthenticationDestinations.BIOMETRIC_AUTHENTICATION) { inclusive = true }
                    }
                },
                onSignInSuccess = {
                    coroutineScope.launch {
                        viewModel.getNextDestination().collectLatest { destination ->
                            navController.navigate(destination) {
                                popUpTo(AuthenticationDestinations.GRAPH) { inclusive = true }
                            }
                        }
                    }
                }
            )
        }

        composable(AuthenticationDestinations.SIGN_IN_WITH_EMAIL) { backStackEntry ->
            val viewModel = sharedAuthenticationViewModel(navController, backStackEntry)
            val coroutineScope = rememberCoroutineScope()

            EmailSignInScreen(
                viewModel = viewModel,
                onBackClick = {
                    if (!navController.navigateUp()) {
                        navController.navigate(AuthenticationDestinations.HOME) {
                            popUpTo(AuthenticationDestinations.SIGN_IN_WITH_EMAIL) { inclusive = true }
                        }
                    }
                },
                onSignInSuccess = {
                    coroutineScope.launch {
                        viewModel.getNextDestination().collectLatest { destination ->
                            navController.navigate(destination) {
                                popUpTo(AuthenticationDestinations.GRAPH) { inclusive = true }
                            }
                        }
                    }
                },
            )
        }

        composable(AuthenticationDestinations.SIGN_UP_WITH_EMAIL) { backStackEntry ->
            val viewModel = sharedAuthenticationViewModel(navController, backStackEntry)
            val coroutineScope = rememberCoroutineScope()

            EmailSignUpScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onSignUpSuccess = {
                    coroutineScope.launch {
                        viewModel.getNextDestination().collectLatest { destination ->
                            navController.navigate(destination) {
                                popUpTo(AuthenticationDestinations.GRAPH) { inclusive = true }
                            }
                        }
                    }
                },
            )
        }

        composable(AuthenticationDestinations.FORGOT_PASSWORD) { backStackEntry ->
            val viewModel = sharedAuthenticationViewModel(navController, backStackEntry)

            ForgotPasswordScreen(
                viewModel = viewModel,
                onBackClick = { navController.popBackStack() },
                onPasswordResetSuccess = {
                    navController.navigate(AuthenticationDestinations.SIGN_IN_WITH_EMAIL) {
                        popUpTo(AuthenticationDestinations.FORGOT_PASSWORD) { inclusive = true }
                    }
                }
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