package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.option.SocialOptionEntity

object SocialOptionSeed : Seed<SocialOptionEntity> {
    override val data = listOf(
        SocialOptionEntity(socialOptionId = 1, description = "Amigo(a)", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 2, description = "Família", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 3, description = "Namorado(a)", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 4, description = "Esposo(a)", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 5, description = "Colega de trabalho", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 6, description = "Colega de estudo", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 7, description = "DesconhecsocialOptionIdo(a)", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 8, description = "Pet", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 9, description = "Vizinho(a)", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 10, description = "Professor(a)", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 11, description = "Chefe", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 12, description = "Grupo de amigos", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 13, description = "Parceiro(a) de treino", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 14, description = "Crianças", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 15, description = "socialOptionIdoso(a)", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 16, description = "Sozinho(a)", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 17, description = "Técnico(a)/Treinador(a)", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 18, description = "Mentor(a)", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 19, description = "Psicólogo(a)", blocked = true, active = true),
        SocialOptionEntity(socialOptionId = 20, description = "Companheiro(a) de viagem", blocked = true, active = true)
    )
}