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
import io.github.faening.lello.feature.journal.settings.model.JournalOptionType
import io.github.faening.lello.feature.journal.settings.screen.JournalSettingsRegisterScreen
import io.github.faening.lello.feature.journal.settings.screen.JournalSettingsScreen

object JournalSettingsDestinations {
    const val GRAPH = "journal_settings_graph"
    const val SETTINGS = "journal_settings/{option}/{colorScheme}"
    const val REGISTER = "journal_settings_register/{option}/{colorScheme}"

    fun listRoute(type: JournalOptionType, scheme: LelloColorScheme) =
        "journal_settings/${type.name}/${scheme.name}"

    fun registerRoute(type: JournalOptionType, scheme: LelloColorScheme) =
        "journal_settings_register/${type.name}/${scheme.name}"
}

fun NavGraphBuilder.journalSettingsGraph(navController: NavHostController) {
    navigation(
        startDestination = JournalSettingsDestinations.SETTINGS,
        route = JournalSettingsDestinations.GRAPH
    ) {
        composable(JournalSettingsDestinations.SETTINGS) { backStackEntry ->
            val optionName = backStackEntry.arguments?.getString("option")
            val optionType = optionName?.let { runCatching { JournalOptionType.valueOf(it) }.getOrNull() }
                ?: JournalOptionType.EMOTION
            val colorSchemeName = backStackEntry.arguments?.getString("colorScheme")
            val colorScheme = colorSchemeName
                ?.let { runCatching { LelloColorScheme.valueOf(it) }.getOrNull() }
                ?: LelloColorScheme.DEFAULT

            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsScreen(
                viewModel = viewModel,
                optionType = optionType,
                colorScheme = colorScheme,
                onBack = { navController.popBackStack() },
                onRegister = {
                    navController.navigate(JournalSettingsDestinations.registerRoute(optionType, colorScheme))
                }
            )
        }

        composable(JournalSettingsDestinations.REGISTER) { backStackEntry ->
            val optionName = backStackEntry.arguments?.getString("option")
            val optionType = optionName?.let { runCatching { JournalOptionType.valueOf(it) }.getOrNull() }
                ?: JournalOptionType.EMOTION
            val colorSchemeName = backStackEntry.arguments?.getString("colorScheme")
            val colorScheme = colorSchemeName
                ?.let { runCatching { LelloColorScheme.valueOf(it) }.getOrNull() }
                ?: LelloColorScheme.DEFAULT

            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsRegisterScreen(
                viewModel = viewModel,
                optionType = optionType,
                colorScheme = colorScheme,
                onBack = { navController.popBackStack() }
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
