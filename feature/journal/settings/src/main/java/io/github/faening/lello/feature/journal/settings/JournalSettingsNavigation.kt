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
import io.github.faening.lello.feature.journal.settings.screen.location.JournalSettingsLocationRegisterScreen
import io.github.faening.lello.feature.journal.settings.screen.location.JournalSettingsLocationScreen
import io.github.faening.lello.feature.journal.settings.screen.social.JournalSettingsSocialRegisterScreen
import io.github.faening.lello.feature.journal.settings.screen.social.JournalSettingsSocialScreen
import io.github.faening.lello.feature.journal.settings.screen.health.JournalSettingsHealthRegisterScreen
import io.github.faening.lello.feature.journal.settings.screen.health.JournalSettingsHealthScreen
import io.github.faening.lello.feature.journal.settings.screen.appetite.JournalSettingsAppetiteOptionRegisterScreen
import io.github.faening.lello.feature.journal.settings.screen.appetite.JournalSettingsAppetiteOptionScreen
import io.github.faening.lello.feature.journal.settings.screen.dosageform.JournalSettingsDosageFormOptionRegisterScreen
import io.github.faening.lello.feature.journal.settings.screen.dosageform.JournalSettingsDosageFormOptionScreen
import io.github.faening.lello.feature.journal.settings.screen.food.JournalSettingsFoodOptionRegisterScreen
import io.github.faening.lello.feature.journal.settings.screen.food.JournalSettingsFoodOptionScreen
import io.github.faening.lello.feature.journal.settings.screen.meal.JournalSettingsMealOptionRegisterScreen
import io.github.faening.lello.feature.journal.settings.screen.meal.JournalSettingsMealOptionScreen
import io.github.faening.lello.feature.journal.settings.screen.portion.JournalSettingsPortionOptionRegisterScreen
import io.github.faening.lello.feature.journal.settings.screen.portion.JournalSettingsPortionOptionScreen
import io.github.faening.lello.feature.journal.settings.screen.sensation.JournalSettingsSensationOptionRegisterScreen
import io.github.faening.lello.feature.journal.settings.screen.sensation.JournalSettingsSensationOptionScreen
import io.github.faening.lello.feature.journal.settings.screen.sleepactivity.JournalSettingsSleepActivityOptionRegisterScreen
import io.github.faening.lello.feature.journal.settings.screen.sleepactivity.JournalSettingsSleepActivityOptionScreen
import io.github.faening.lello.feature.journal.settings.screen.sleepquality.JournalSettingsSleepQualityOptionRegisterScreen
import io.github.faening.lello.feature.journal.settings.screen.sleepquality.JournalSettingsSleepQualityOptionScreen

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

    const val APPETITE_SETTINGS = "journal_settings_appetite/{colorScheme}"
    const val APPETITE_REGISTER = "journal_settings_appetite_register"

    const val DOSAGE_FORM_SETTINGS = "journal_settings_dosageform/{colorScheme}"
    const val DOSAGE_FORM_REGISTER = "journal_settings_dosageform_register"

    const val FOOD_SETTINGS = "journal_settings_food/{colorScheme}"
    const val FOOD_REGISTER = "journal_settings_food_register"

    const val MEAL_SETTINGS = "journal_settings_meal/{colorScheme}"
    const val MEAL_REGISTER = "journal_settings_meal_register"

    const val PORTION_SETTINGS = "journal_settings_portion/{colorScheme}"
    const val PORTION_REGISTER = "journal_settings_portion_register"

    const val SENSATION_SETTINGS = "journal_settings_sensation/{colorScheme}"
    const val SENSATION_REGISTER = "journal_settings_sensation_register"

    const val SLEEP_ACTIVITY_SETTINGS = "journal_settings_sleepactivity/{colorScheme}"
    const val SLEEP_ACTIVITY_REGISTER = "journal_settings_sleepactivity_register"

    const val SLEEP_QUALITY_SETTINGS = "journal_settings_sleepquality/{colorScheme}"
    const val SLEEP_QUALITY_REGISTER = "journal_settings_sleepquality_register"
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

        composable(JournalSettingsDestinations.LOCATION_SETTINGS) { backStackEntry ->
            val colorSchemeName = backStackEntry.arguments?.getString("colorScheme")
            val colorScheme = colorSchemeName
                ?.let { runCatching { LelloColorScheme.valueOf(it) }.getOrNull() }
                ?: LelloColorScheme.DEFAULT

            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsLocationScreen(
                viewModel = viewModel,
                colorScheme = colorScheme,
                onBack = { navController.popBackStack() },
                onRegister = { navController.navigate(JournalSettingsDestinations.LOCATION_REGISTER) }
            )
        }

        composable(JournalSettingsDestinations.LOCATION_REGISTER) { backStackEntry ->
            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsLocationRegisterScreen(
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

        composable(JournalSettingsDestinations.HEALTH_SETTINGS) { backStackEntry ->
            val colorSchemeName = backStackEntry.arguments?.getString("colorScheme")
            val colorScheme = colorSchemeName
                ?.let { runCatching { LelloColorScheme.valueOf(it) }.getOrNull() }
                ?: LelloColorScheme.DEFAULT

            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsHealthScreen(
                viewModel = viewModel,
                colorScheme = colorScheme,
                onBack = { navController.popBackStack() },
                onRegister = { navController.navigate(JournalSettingsDestinations.HEALTH_REGISTER) }
            )
        }

        composable(JournalSettingsDestinations.HEALTH_REGISTER) { backStackEntry ->
            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsHealthRegisterScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
            )
        }

        composable(JournalSettingsDestinations.APPETITE_SETTINGS) { backStackEntry ->
            val colorSchemeName = backStackEntry.arguments?.getString("colorScheme")
            val colorScheme = colorSchemeName
                ?.let { runCatching { LelloColorScheme.valueOf(it) }.getOrNull() }
                ?: LelloColorScheme.DEFAULT

            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsAppetiteOptionScreen(
                viewModel = viewModel,
                colorScheme = colorScheme,
                onBack = { navController.popBackStack() },
                onRegister = { navController.navigate(JournalSettingsDestinations.APPETITE_REGISTER) }
            )
        }

        composable(JournalSettingsDestinations.APPETITE_REGISTER) { backStackEntry ->
            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsAppetiteOptionRegisterScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
            )
        }

        composable(JournalSettingsDestinations.DOSAGE_FORM_SETTINGS) { backStackEntry ->
            val colorSchemeName = backStackEntry.arguments?.getString("colorScheme")
            val colorScheme = colorSchemeName
                ?.let { runCatching { LelloColorScheme.valueOf(it) }.getOrNull() }
                ?: LelloColorScheme.DEFAULT

            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsDosageFormOptionScreen(
                viewModel = viewModel,
                colorScheme = colorScheme,
                onBack = { navController.popBackStack() },
                onRegister = { navController.navigate(JournalSettingsDestinations.DOSAGE_FORM_REGISTER) }
            )
        }

        composable(JournalSettingsDestinations.DOSAGE_FORM_REGISTER) { backStackEntry ->
            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsDosageFormOptionRegisterScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
            )
        }

        composable(JournalSettingsDestinations.FOOD_SETTINGS) { backStackEntry ->
            val colorSchemeName = backStackEntry.arguments?.getString("colorScheme")
            val colorScheme = colorSchemeName
                ?.let { runCatching { LelloColorScheme.valueOf(it) }.getOrNull() }
                ?: LelloColorScheme.DEFAULT

            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsFoodOptionScreen(
                viewModel = viewModel,
                colorScheme = colorScheme,
                onBack = { navController.popBackStack() },
                onRegister = { navController.navigate(JournalSettingsDestinations.FOOD_REGISTER) }
            )
        }

        composable(JournalSettingsDestinations.FOOD_REGISTER) { backStackEntry ->
            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsFoodOptionRegisterScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
            )
        }

        composable(JournalSettingsDestinations.MEAL_SETTINGS) { backStackEntry ->
            val colorSchemeName = backStackEntry.arguments?.getString("colorScheme")
            val colorScheme = colorSchemeName
                ?.let { runCatching { LelloColorScheme.valueOf(it) }.getOrNull() }
                ?: LelloColorScheme.DEFAULT

            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsMealOptionScreen(
                viewModel = viewModel,
                colorScheme = colorScheme,
                onBack = { navController.popBackStack() },
                onRegister = { navController.navigate(JournalSettingsDestinations.MEAL_REGISTER) }
            )
        }

        composable(JournalSettingsDestinations.MEAL_REGISTER) { backStackEntry ->
            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsMealOptionRegisterScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
            )
        }

        composable(JournalSettingsDestinations.PORTION_SETTINGS) { backStackEntry ->
            val colorSchemeName = backStackEntry.arguments?.getString("colorScheme")
            val colorScheme = colorSchemeName
                ?.let { runCatching { LelloColorScheme.valueOf(it) }.getOrNull() }
                ?: LelloColorScheme.DEFAULT

            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsPortionOptionScreen(
                viewModel = viewModel,
                colorScheme = colorScheme,
                onBack = { navController.popBackStack() },
                onRegister = { navController.navigate(JournalSettingsDestinations.PORTION_REGISTER) }
            )
        }

        composable(JournalSettingsDestinations.PORTION_REGISTER) { backStackEntry ->
            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsPortionOptionRegisterScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
            )
        }

        composable(JournalSettingsDestinations.SENSATION_SETTINGS) { backStackEntry ->
            val colorSchemeName = backStackEntry.arguments?.getString("colorScheme")
            val colorScheme = colorSchemeName
                ?.let { runCatching { LelloColorScheme.valueOf(it) }.getOrNull() }
                ?: LelloColorScheme.DEFAULT

            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsSensationOptionScreen(
                viewModel = viewModel,
                colorScheme = colorScheme,
                onBack = { navController.popBackStack() },
                onRegister = { navController.navigate(JournalSettingsDestinations.SENSATION_REGISTER) }
            )
        }

        composable(JournalSettingsDestinations.SENSATION_REGISTER) { backStackEntry ->
            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsSensationOptionRegisterScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
            )
        }

        composable(JournalSettingsDestinations.SLEEP_ACTIVITY_SETTINGS) { backStackEntry ->
            val colorSchemeName = backStackEntry.arguments?.getString("colorScheme")
            val colorScheme = colorSchemeName
                ?.let { runCatching { LelloColorScheme.valueOf(it) }.getOrNull() }
                ?: LelloColorScheme.DEFAULT

            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsSleepActivityOptionScreen(
                viewModel = viewModel,
                colorScheme = colorScheme,
                onBack = { navController.popBackStack() },
                onRegister = { navController.navigate(JournalSettingsDestinations.SLEEP_ACTIVITY_REGISTER) }
            )
        }

        composable(JournalSettingsDestinations.SLEEP_ACTIVITY_REGISTER) { backStackEntry ->
            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsSleepActivityOptionRegisterScreen(
                viewModel = viewModel,
                onBack = { navController.popBackStack() },
            )
        }

        composable(JournalSettingsDestinations.SLEEP_QUALITY_SETTINGS) { backStackEntry ->
            val colorSchemeName = backStackEntry.arguments?.getString("colorScheme")
            val colorScheme = colorSchemeName
                ?.let { runCatching { LelloColorScheme.valueOf(it) }.getOrNull() }
                ?: LelloColorScheme.DEFAULT

            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsSleepQualityOptionScreen(
                viewModel = viewModel,
                colorScheme = colorScheme,
                onBack = { navController.popBackStack() },
                onRegister = { navController.navigate(JournalSettingsDestinations.SLEEP_QUALITY_REGISTER) }
            )
        }

        composable(JournalSettingsDestinations.SLEEP_QUALITY_REGISTER) { backStackEntry ->
            val viewModel = sharedJournalSettingsViewModel(navController, backStackEntry)
            JournalSettingsSleepQualityOptionRegisterScreen(
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