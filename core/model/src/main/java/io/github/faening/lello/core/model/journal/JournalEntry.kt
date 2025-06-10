package io.github.faening.lello.core.model.journal

data class JournalEntry(
    val dateTime: String,
    val mood: MoodType,
    val emotionOptionIds: List<Int>,
    val climateOptionIds: List<Int>,
    val locationOptionIds: List<Int>,
    val socialOptionIds: List<Int>,
    val healthOptionIds: List<Int>,
    val reflection: String? = null
)

enum class MoodType(val label: String) {
    SERENE("Excelente"),
    JOYFUL("Bem"),
    BALANCED("Normal"),
    TROUBLED("Mal"),
    OVERWHELMED("Horrível");
}
