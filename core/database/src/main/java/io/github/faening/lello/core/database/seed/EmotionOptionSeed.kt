package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.EmotionOptionEntity

internal object EmotionOptionSeed : Seed<EmotionOptionEntity> {
    override val data = listOf(
        EmotionOptionEntity(id = 1, description = "Amado", blocked = true, active = true),
        EmotionOptionEntity(id = 2, description = "Apático", blocked = true, active = true),
        EmotionOptionEntity(id = 3, description = "Confiante", blocked = true, active = true),
        EmotionOptionEntity(id = 4, description = "Confuso", blocked = true, active = true),
        EmotionOptionEntity(id = 5, description = "Culpado", blocked = true, active = true),
        EmotionOptionEntity(id = 6, description = "Decepcionado", blocked = true, active = true),
        EmotionOptionEntity(id = 7, description = "Desanimado", blocked = true, active = true),
        EmotionOptionEntity(id = 8, description = "Desconfiado", blocked = true, active = true),
        EmotionOptionEntity(id = 9, description = "Entediado", blocked = true, active = true),
        EmotionOptionEntity(id = 10, description = "Enjoado", blocked = true, active = true),
        EmotionOptionEntity(id = 11, description = "Estressado", blocked = true, active = true),
        EmotionOptionEntity(id = 12, description = "Frustrado", blocked = true, active = true),
        EmotionOptionEntity(id = 13, description = "Grato", blocked = true, active = true),
        EmotionOptionEntity(id = 14, description = "Incomodado", blocked = true, active = true),
        EmotionOptionEntity(id = 15, description = "Inseguro", blocked = true, active = true),
        EmotionOptionEntity(id = 16, description = "Irritado", blocked = true, active = true),
        EmotionOptionEntity(id = 17, description = "Melancólico", blocked = true, active = true),
        EmotionOptionEntity(id = 18, description = "Nervoso", blocked = true, active = true),
        EmotionOptionEntity(id = 19, description = "Otimista", blocked = true, active = true),
        EmotionOptionEntity(id = 20, description = "Orgulhoso", blocked = true, active = true),
        EmotionOptionEntity(id = 21, description = "Preocupado", blocked = true, active = true),
        EmotionOptionEntity(id = 22, description = "Sentimental", blocked = true, active = true),
        EmotionOptionEntity(id = 23, description = "Solitário", blocked = true, active = true),
        EmotionOptionEntity(id = 24, description = "Stressado", blocked = true, active = true),
        EmotionOptionEntity(id = 25, description = "Tímido", blocked = true, active = true),
        EmotionOptionEntity(id = 26, description = "Triste", blocked = true, active = true)
    )
}