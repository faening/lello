package io.github.faening.lello.feature.journal.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.feature.journal.settings.screen.emotion.JournalSettingsEmotionRegisterScreen
import io.github.faening.lello.feature.journal.settings.screen.emotion.JournalSettingsEmotionScreen

object JournalSettingsDestinations {
    const val GRAPH = "journal_settings_graph"
    const val EMOTION_SETTINGS = "journal_settings_emotion"
    const val EMOTION_REGISTER = "journal_settings_emotion_register"
}

fun NavGraphBuilder.journalSettingsGraph(navController: NavHostController) {
    navigation(
        startDestination = JournalSettingsDestinations.EMOTION_SETTINGS,
        route = JournalSettingsDestinations.GRAPH
    ) {
        composable(JournalSettingsDestinations.EMOTION_SETTINGS) { backStackEntry ->
            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsEmotionScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
                onRegister = { navController.navigate(JournalSettingsDestinations.EMOTION_REGISTER) }
            )
        }

        composable(JournalSettingsDestinations.EMOTION_REGISTER) { backStackEntry ->
            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsEmotionRegisterScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
            )
        }


    }
}

@Composable
fun sharedJournalSettingsViewModel(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
): JournalSettingsViewModel {
    val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(JournalSettingsDestinations.GRAPH)
    }
    return hiltViewModel(parentEntry)
}