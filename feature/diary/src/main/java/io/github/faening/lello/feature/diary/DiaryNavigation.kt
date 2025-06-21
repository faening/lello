package io.github.faening.lello.feature.diary

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.diary.screen.DiaryScreen

object DiaryDestinations {
    const val GRAPH = "diary_graph"
    const val HOME = "diary_home"
}

fun NavGraphBuilder.diaryGraph(navController: NavHostController) {
    navigation(
        startDestination = DiaryDestinations.HOME,
        route = DiaryDestinations.GRAPH
    ) {
        composable(DiaryDestinations.HOME) { backStackEntry ->
            val viewModel = sharedDiaryViewModel(navController, backStackEntry)
            DiaryScreen(
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun sharedDiaryViewModel(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
): DiaryViewModel {
    val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(DiaryDestinations.GRAPH)
    }
    return hiltViewModel(parentEntry)
}