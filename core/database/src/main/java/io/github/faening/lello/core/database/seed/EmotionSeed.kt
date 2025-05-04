package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.EmotionEntity

internal object EmotionSeed : Seed<EmotionEntity> {
    override val data = listOf(
        EmotionEntity(id = 1, word = "Amado", blocked = true, active = true),
        EmotionEntity(id = 2, word = "Apático", blocked = true, active = true),
        EmotionEntity(id = 3, word = "Confiante", blocked = true, active = true),
        EmotionEntity(id = 4, word = "Confuso", blocked = true, active = true),
        EmotionEntity(id = 5, word = "Culpado", blocked = true, active = true),
        EmotionEntity(id = 6, word = "Decepcionado", blocked = true, active = true),
        EmotionEntity(id = 7, word = "Desanimado", blocked = true, active = true),
        EmotionEntity(id = 8, word = "Desconfiado", blocked = true, active = true),
        EmotionEntity(id = 9, word = "Entediado", blocked = true, active = true),
        EmotionEntity(id = 10, word = "Enjoado", blocked = true, active = true),
        EmotionEntity(id = 11, word = "Estressado", blocked = true, active = true),
        EmotionEntity(id = 12, word = "Frustrado", blocked = true, active = true),
        EmotionEntity(id = 13, word = "Grato", blocked = true, active = true),
        EmotionEntity(id = 14, word = "Incomodado", blocked = true, active = true),
        EmotionEntity(id = 15, word = "Inseguro", blocked = true, active = true),
        EmotionEntity(id = 16, word = "Irritado", blocked = true, active = true),
        EmotionEntity(id = 17, word = "Melancólico", blocked = true, active = true),
        EmotionEntity(id = 18, word = "Nervoso", blocked = true, active = true),
        EmotionEntity(id = 19, word = "Otimista", blocked = true, active = true),
        EmotionEntity(id = 20, word = "Orgulhoso", blocked = true, active = true),
        EmotionEntity(id = 21, word = "Preocupado", blocked = true, active = true),
        EmotionEntity(id = 22, word = "Sentimental", blocked = true, active = true),
        EmotionEntity(id = 23, word = "Solitário", blocked = true, active = true),
        EmotionEntity(id = 24, word = "Stressado", blocked = true, active = true),
        EmotionEntity(id = 25, word = "Tímido", blocked = true, active = true),
        EmotionEntity(id = 26, word = "Triste", blocked = true, active = true)
    )
}