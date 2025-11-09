package io.github.faening.lello.core.testing.data

import io.github.faening.lello.core.model.option.EmotionOption

object EmotionOptionTestData {
    val list = listOf(
        EmotionOption(id = 1, description = "Feliz", blocked = false, active = true),
        EmotionOption(id = 2, description = "Triste", blocked = false, active = true),
        EmotionOption(id = 3, description = "Cansado", blocked = false, active = true),
        EmotionOption(id = 4, description = "Fome", blocked = false, active = true),
        EmotionOption(id = 5, description = "Enérgico", blocked = false, active = true),
        EmotionOption(id = 6, description = "Animado", blocked = false, active = true),
        EmotionOption(id = 7, description = "Confiante", blocked = false, active = true)
    )
}