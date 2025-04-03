package io.github.faening.lello.feature.diarymood.screen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryMoodScreen(
    mainNavController: NavController,
    onBackClick: () -> Unit
) {
    val navController = rememberNavController()

    DiaryMoodNavHost(
        navController = navController,
        mainNavController = mainNavController,
        onBackClick = onBackClick
    )
}

@Composable
private fun DiaryMoodNavHost(
    navController: NavHostController,
    mainNavController: NavController,
    onBackClick: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "diary_mood_home"
    ) {
        composable("diary_mood_home") {
            DiaryMoodHomeScreen(
                navController = navController,
                onBackClick = onBackClick
            )
        }

//        composable("diary_mood_create") {
//            DiaryMoodCreateScreen(
//                navController = navController
//            )
//        }
//
//        composable("diary_mood_details/{entryId}") { backStackEntry ->
//            val entryId = backStackEntry.arguments?.getString("entryId") ?: ""
//            DiaryMoodDetailsScreen(
//                entryId = entryId,
//                navController = navController
//            )
//        }
    }
}

