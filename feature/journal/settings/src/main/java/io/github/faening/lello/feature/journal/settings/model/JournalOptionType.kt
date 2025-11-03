package io.github.faening.lello.feature.journal.settings.model

import androidx.annotation.StringRes
import io.github.faening.lello.feature.journal.settings.R

enum class JournalOptionType(
    @StringRes val titleRes: Int,
    @StringRes val registerLabelRes: Int,
) {
    EMOTION(
        R.string.journal_settings_emotion_appbar_title,
        R.string.journal_settings_emotion_register_button_label
    ),
    CLIMATE(
        R.string.journal_settings_climate_appbar_title,
        R.string.journal_settings_climate_register_button_label
    ),
    LOCATION(
        R.string.journal_settings_location_appbar_title,
        R.string.journal_settings_location_register_button_label
    ),
    SOCIAL(
        R.string.journal_settings_social_appbar_title,
        R.string.journal_settings_social_register_button_label
    ),
    HEALTH(
        R.string.journal_settings_health_appbar_title,
        R.string.journal_settings_health_register_button_label
    ),
    APPETITE(
        R.string.journal_settings_appetite_appbar_title,
        R.string.journal_settings_appetite_register_button_label
    ),
    FOOD(
        R.string.journal_settings_food_appbar_title,
        R.string.journal_settings_food_register_button_label
    ),
    MEAL(
        R.string.journal_settings_meal_appbar_title,
        R.string.journal_settings_meal_register_button_label
    ),
    MEDICATION_DOSAGE_FORM(
        R.string.journal_settings_dosageform_appbar_title,
        R.string.journal_settings_dosageform_register_button_label
    ),
    MEDICATION_SKIP_REASON(
        R.string.journal_settings_medication_appbar_title,
        R.string.journal_settings_medication_register_button_label
    ),
    PORTION(
        R.string.journal_settings_portion_appbar_title,
        R.string.journal_settings_portion_register_button_label
    ),
    SLEEP_SENSATION(
        R.string.journal_settings_sensation_appbar_title,
        R.string.journal_settings_sensation_register_button_label
    ),
    SLEEP_ACTIVITY(
        R.string.journal_settings_sleepactivity_appbar_title,
        R.string.journal_settings_sleepactivity_register_button_label
    ),
    SLEEP_QUALITY(
        R.string.journal_settings_sleepquality_appbar_title,
        R.string.journal_settings_sleepquality_register_button_label
    );
}
