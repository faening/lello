package io.github.faening.lello.core.model.journal

enum class JournalType {
    MOOD,
    SLEEP,
    MEDICATION,
    MEAL;

    companion object {
        fun fromName(name: String): JournalType? {
            return when (name) {
                "Humor" -> MOOD
                "Sono" -> SLEEP
                "Medicamentos" -> MEDICATION
                "Alimentação" -> MEAL
                else -> null
            }
        }

        fun getName(type: JournalType): String {
            return when (type) {
                MOOD -> "Humor"
                SLEEP -> "Sono"
                MEDICATION -> "Medicamentos"
                MEAL -> "Alimentação"
            }
        }
    }
}
