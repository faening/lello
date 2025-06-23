package io.github.faening.lello.core.model.journal

import io.github.faening.lello.core.model.option.ClimateOption
import io.github.faening.lello.core.model.option.EmotionOption
import io.github.faening.lello.core.model.option.HealthOption
import io.github.faening.lello.core.model.option.LocationOption
import io.github.faening.lello.core.model.option.SocialOption

data class MoodJournal(
    val id: Long? = null,
    val mood: MoodType,
    val reflection: String? = null,
    val emotionOptions: List<EmotionOption>,
    val climateOptions: List<ClimateOption>,
    val locationOptions: List<LocationOption>,
    val socialOptions: List<SocialOption>,
    val healthOptions: List<HealthOption>,
    val createdAt: Long,
)

enum class MoodType(val label: String) {
    SERENE("Excelente"),
    JOYFUL("Bem"),
    BALANCED("Normal"),
    TROUBLED("Mal"),
    OVERWHELMED("Horrível");
}