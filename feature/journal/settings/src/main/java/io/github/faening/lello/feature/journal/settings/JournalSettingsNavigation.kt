package io.github.faening.lello.feature.journal.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.core.designsystem.theme.LelloColorScheme
import io.github.faening.lello.feature.journal.settings.screen.emotion.JournalSettingsEmotionRegisterScreen
import io.github.faening.lello.feature.journal.settings.screen.emotion.JournalSettingsEmotionScreen
import io.github.faening.lello.feature.journal.settings.screen.climate.JournalSettingsClimateRegisterScreen
import io.github.faening.lello.feature.journal.settings.screen.climate.JournalSettingsClimateScreen
import io.github.faening.lello.feature.journal.settings.screen.social.JournalSettingsSocialRegisterScreen
import io.github.faening.lello.feature.journal.settings.screen.social.JournalSettingsSocialScreen

object JournalSettingsDestinations {
    const val GRAPH = "journal_settings_graph"
    const val EMOTION_SETTINGS = "journal_settings_emotion/{colorScheme}"
    const val EMOTION_REGISTER = "journal_settings_emotion_register"
    const val CLIMATE_SETTINGS = "journal_settings_climate/{colorScheme}"
    const val CLIMATE_REGISTER = "journal_settings_climate_register"
    const val LOCATION_SETTINGS = "journal_settings_location/{colorScheme}"
    const val LOCATION_REGISTER = "journal_settings_location_register"
    const val SOCIAL_SETTINGS = "journal_settings_social/{colorScheme}"
    const val SOCIAl_REGISTER = "journal_settings_social_register"
    const val HEALTH_SETTINGS = "journal_settings_health/{colorScheme}"
    const val HEALTH_REGISTER = "journal_settings_health_register"
}

fun NavGraphBuilder.journalSettingsGraph(navController: NavHostController) {
    navigation(
        startDestination = JournalSettingsDestinations.EMOTION_SETTINGS,
        route = JournalSettingsDestinations.GRAPH
    ) {
        composable(JournalSettingsDestinations.EMOTION_SETTINGS) { backStackEntry ->
            val colorSchemeName = backStackEntry.arguments?.getString("colorScheme")
            val colorScheme = colorSchemeName
                ?.let { runCatching { LelloColorScheme.valueOf(it) }.getOrNull() }
                ?: LelloColorScheme.DEFAULT

            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsEmotionScreen(
                viewModel = viewModel,
                colorScheme = colorScheme,
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

        composable(JournalSettingsDestinations.CLIMATE_SETTINGS) { backStackEntry ->
            val colorSchemeName = backStackEntry.arguments?.getString("colorScheme")
            val colorScheme = colorSchemeName
                ?.let { runCatching { LelloColorScheme.valueOf(it) }.getOrNull() }
                ?: LelloColorScheme.DEFAULT

            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsClimateScreen(
                viewModel = viewModel,
                colorScheme = colorScheme,
                onBack = { navController.popBackStack() },
                onRegister = { navController.navigate(JournalSettingsDestinations.CLIMATE_REGISTER) }
            )
        }

        composable(JournalSettingsDestinations.CLIMATE_REGISTER) { backStackEntry ->
            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsClimateRegisterScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
            )
        }

        composable(JournalSettingsDestinations.SOCIAL_SETTINGS) { backStackEntry ->
            val colorSchemeName = backStackEntry.arguments?.getString("colorScheme")
            val colorScheme = colorSchemeName
                ?.let { runCatching { LelloColorScheme.valueOf(it) }.getOrNull() }
                ?: LelloColorScheme.DEFAULT

            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsSocialScreen(
                viewModel = viewModel,
                colorScheme = colorScheme,
                onBack = { navController.popBackStack() },
                onRegister = { navController.navigate(JournalSettingsDestinations.SOCIAl_REGISTER) }
            )
        }

        composable(JournalSettingsDestinations.SOCIAl_REGISTER) { backStackEntry ->
            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsSocialRegisterScreen(
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