package io.github.faening.lello.feature.profile.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.faening.lello.feature.profile.screen.ProfileScreen

object ProfileNavigation {
    const val PROFILE_GRAPH = "profile"
    const val PROFILE_ROUTE = "profile_main"
    const val PROFILE_SETTINGS_ROUTE = "profile_settings"
    const val PROFILE_EDIT_ROUTE = "profile_edit"
}

fun NavGraphBuilder.profileGraph(navController: NavController) {
    // Tela principal do Profile diretamente na rota "profile"
    composable(route = ProfileNavigation.PROFILE_ROUTE) {
        ProfileScreen(
            onSettingsClick = {
                navController.navigate(ProfileNavigation.PROFILE_SETTINGS_ROUTE)
            },
            onEditProfileClick = {
                navController.navigate(ProfileNavigation.PROFILE_EDIT_ROUTE)
            }
        )
    }

    // Tela de configurações
//    composable(route = PROFILE_SETTINGS_ROUTE) {
//        ProfileSettingsScreen(
//            onBackClick = {
//                navController.navigateUp()
//            }
//        )
//    }

    // Tela de edição de perfil
//    composable(route = PROFILE_EDIT_ROUTE) {
//        ProfileEditScreen(
//            onBackClick = {
//                navController.navigateUp()
//            }
//        )
//    }
}
