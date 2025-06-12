package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.option.EmotionOptionEntity

internal object EmotionOptionSeed : Seed<EmotionOptionEntity> {
    override val data = listOf(
        EmotionOptionEntity(emotionOptionId = 1, description = "Amado", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 2, description = "Apático", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 3, description = "Confiante", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 4, description = "Confuso", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 5, description = "Culpado", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 6, description = "Decepcionado", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 7, description = "Desanimado", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 8, description = "Desconfiado", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 9, description = "Entediado", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 10, description = "Enjoado", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 11, description = "Estressado", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 12, description = "Frustrado", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 13, description = "Grato", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 14, description = "Incomodado", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 15, description = "Inseguro", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 16, description = "Irritado", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 17, description = "Melancólico", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 18, description = "Nervoso", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 19, description = "Otimista", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 20, description = "Orgulhoso", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 21, description = "Preocupado", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 22, description = "Sentimental", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 23, description = "Solitário", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 24, description = "Stressado", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 25, description = "TímemotionOptionIdo", blocked = true, active = true),
        EmotionOptionEntity(emotionOptionId = 26, description = "Triste", blocked = true, active = true)
    )
}