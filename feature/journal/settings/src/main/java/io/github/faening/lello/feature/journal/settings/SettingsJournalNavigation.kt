package io.github.faening.lello.feature.journal.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import io.github.faening.lello.core.designsystem.theme.MoodColor
import io.github.faening.lello.feature.journal.settings.model.JournalOptionType
import io.github.faening.lello.feature.journal.settings.screen.SettingsJournalRegisterScreen
import io.github.faening.lello.feature.journal.settings.screen.SettingsJournalScreen

object SettingsJournalDestinations {
    const val GRAPH = "journal_settings_graph"
    const val SETTINGS = "journal_settings/{option}/{colorScheme}"
    const val REGISTER = "journal_settings_register/{option}/{colorScheme}"

    fun listRoute(type: JournalOptionType, scheme: MoodColor) =
        "journal_settings/${type.name}/${scheme.name}"

    fun registerRoute(type: JournalOptionType, scheme: MoodColor) =
        "journal_settings_register/${type.name}/${scheme.name}"
}

fun NavGraphBuilder.settingsJournalGraph(navController: NavHostController) {
    navigation(
        startDestination = SettingsJournalDestinations.SETTINGS,
        route = SettingsJournalDestinations.GRAPH
    ) {
        composable(SettingsJournalDestinations.SETTINGS) { backStackEntry ->
            val optionName = backStackEntry.arguments?.getString("option")
            val colorSchemeName = backStackEntry.arguments?.getString("colorScheme")

            val optionType = optionName
                ?.let { runCatching { JournalOptionType.valueOf(it) }.getOrNull() }
                ?: JournalOptionType.EMOTION

            val colorScheme = colorSchemeName
                ?.let { runCatching { MoodColor.valueOf(it) }.getOrNull() }
                ?: MoodColor.DEFAULT

            val viewModel = sharedSettingsJournalViewModel(navController, backStackEntry)

            SettingsJournalScreen(
                viewModel = viewModel,
                optionType = optionType,
                colorScheme = colorScheme,
                onBack = { navController.popBackStack() },
                onRegister = {
                    navController.navigate(SettingsJournalDestinations.registerRoute(optionType, colorScheme))
                }
            )
        }

        composable(SettingsJournalDestinations.REGISTER) { backStackEntry ->
            val optionName = backStackEntry.arguments?.getString("option")
            val colorSchemeName = backStackEntry.arguments?.getString("colorScheme")

            val optionType = optionName
                ?.let { runCatching { JournalOptionType.valueOf(it) }.getOrNull() }
                ?: JournalOptionType.EMOTION

            val colorScheme = colorSchemeName
                ?.let { runCatching { MoodColor.valueOf(it) }.getOrNull() }
                ?: MoodColor.DEFAULT

            val viewModel = sharedSettingsJournalViewModel(navController, backStackEntry)

            SettingsJournalRegisterScreen(
                viewModel = viewModel,
                optionType = optionType,
                colorScheme = colorScheme,
                onBack = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun sharedSettingsJournalViewModel(
    navController: NavHostController,
    backStackEntry: NavBackStackEntry
): SettingsJournalViewModel {
    val parentEntry = remember(backStackEntry) {
        navController.getBackStackEntry(SettingsJournalDestinations.GRAPH)
    }
    return hiltViewModel(parentEntry)
}
