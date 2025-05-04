package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.SocialOptionEntity

object SocialOptionSeed : Seed<SocialOptionEntity> {
    override val data = listOf(
        SocialOptionEntity(id = 1, description = "Amigo(a)", blocked = true, active = true),
        SocialOptionEntity(id = 2, description = "Família", blocked = true, active = true),
        SocialOptionEntity(id = 3, description = "Namorado(a)", blocked = true, active = true),
        SocialOptionEntity(id = 4, description = "Esposo(a)", blocked = true, active = true),
        SocialOptionEntity(id = 5, description = "Colega de trabalho", blocked = true, active = true),
        SocialOptionEntity(id = 6, description = "Colega de estudo", blocked = true, active = true),
        SocialOptionEntity(id = 7, description = "Desconhecido(a)", blocked = true, active = true),
        SocialOptionEntity(id = 8, description = "Pet", blocked = true, active = true),
        SocialOptionEntity(id = 9, description = "Vizinho(a)", blocked = true, active = true),
        SocialOptionEntity(id = 10, description = "Professor(a)", blocked = true, active = true),
        SocialOptionEntity(id = 11, description = "Chefe", blocked = true, active = true),
        SocialOptionEntity(id = 12, description = "Grupo de amigos", blocked = true, active = true),
        SocialOptionEntity(id = 13, description = "Parceiro(a) de treino", blocked = true, active = true),
        SocialOptionEntity(id = 14, description = "Crianças", blocked = true, active = true),
        SocialOptionEntity(id = 15, description = "Idoso(a)", blocked = true, active = true),
        SocialOptionEntity(id = 16, description = "Sozinho(a)", blocked = true, active = true),
        SocialOptionEntity(id = 17, description = "Técnico(a)/Treinador(a)", blocked = true, active = true),
        SocialOptionEntity(id = 18, description = "Mentor(a)", blocked = true, active = true),
        SocialOptionEntity(id = 19, description = "Psicólogo(a)", blocked = true, active = true),
        SocialOptionEntity(id = 20, description = "Companheiro(a) de viagem", blocked = true, active = true)
    )
}