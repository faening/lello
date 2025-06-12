package io.github.faening.lello.core.model.journal

import java.util.Date

data class JournalMood(
    val date: Date,
    val mood: MoodType,
    val reflection: String? = null,
    val emotionOptions: List<EmotionOption>,
    val climateOptions: List<ClimateOption>,
    val locationOptions: List<LocationOption>,
    val socialOptions: List<SocialOption>,
    val healthOptions: List<HealthOption>
)

enum class MoodType(val label: String) {
    SERENE("Excelente"),
    JOYFUL("Bem"),
    BALANCED("Normal"),
    TROUBLED("Mal"),
    OVERWHELMED("Horrível");
}