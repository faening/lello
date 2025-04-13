package io.github.faening.lello.feature.menu.diary.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import io.github.faening.lello.feature.menu.diary.screen.DiaryHomeScreen
import io.github.faening.lello.feature.menu.diary.screen.DiarySettingsScreen

const val DIARY_GRAPH = "diary"
const val DIARY_MAIN_ROUTE = "diary_main"
const val DIARY_SETTINGS_ROUTE = "diary_settings"
const val DIARY_EDIT_ROUTE = "diary_edit"

fun NavGraphBuilder.diaryGraph(navController: NavController) {

    composable(route = DIARY_MAIN_ROUTE) {
        DiaryHomeScreen(
            onSettingsClick = {
                navController.navigate(DIARY_SETTINGS_ROUTE)
            },
            onEditDiaryClick = {
                navController.navigate(DIARY_EDIT_ROUTE)
            }
        )
    }

    composable(route = DIARY_SETTINGS_ROUTE) {
        DiarySettingsScreen(
            onBackClick = {
                navController.popBackStack()
            }
        )
    }

    // Tela de configurações
//    composable(route = DIARY_SETTINGS_ROUTE) {
//        DiarySettingsScreen(
//            onBackClick = {
//                navController.navigateUp()
//            }
//        )
//    }

    // Tela de edição de perfil
//    composable(route = DIARY_EDIT_ROUTE) {
//        DiaryEditScreen(
//            onBackClick = {
//                navController.navigateUp()
//            }
//        )
//    }

}
