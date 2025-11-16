package io.github.faening.lello.core.database.seed

import io.github.faening.lello.core.database.model.store.ItemCatalogEntity
import io.github.faening.lello.core.model.store.ItemType

object ItemCatalogSeed : Seed<ItemCatalogEntity> {
    override val data = listOf(
        // Consumables
        ItemCatalogEntity(
            itemCatalogId = 0L,
            name = "Girassol",
            description = "Sementes crocantes, ricas em vitaminas e minerais. Ao consumir este item, a energia do Lello será restaurada em 15.",
            price = 35,
            imageResourceName = "ic_item_consumable_sunflower_custom",
            type = ItemType.CONSUMABLE,
            vitalityGain = 15,
            isActive = true
        ),

        // Hats
        ItemCatalogEntity(
            itemCatalogId = 0L,
            name = "Chapéu da Lua Cantante",
            description = "Dizem que este chapéu foi trançado com acordes de uma canção esquecida. Quem o usa sente os pés leves e o coração mais alegre — ou talvez, seja só vontade de sair dançando por aí.",
            price = 250,
            imageResourceName = "ic_item_hat_singing_moon_custom",
            type = ItemType.HEAD,
            vitalityGain = null,
            isActive = true
        ),
        ItemCatalogEntity(
            itemCatalogId = 0L,
            name = "Chapéu do Capiter Pan",
            description = "Reza a lenda que este chapéu nasceu do primeiro sopro de vento livre. Quem o veste sente o coração mais leve e a coragem renovada, ou talvez, seja só a pena balançando ao lado.",
            price = 200,
            imageResourceName = "ic_hat_capiter_pan_custom",
            type = ItemType.HEAD,
            vitalityGain = null,
            isActive = true
        ),
        ItemCatalogEntity(
            itemCatalogId = 0L,
            name = "Elmo do Bravo Teimoso",
            description = "Forjado do orgulho de mil batalhas, dizem que este elmo transforma qualquer um em cabeça-dura. Protege dos golpes e das dúvidas, ou talvez, seja só o peso fazendo a teimosia aumentar.",
            price = 200,
            imageResourceName = "ic_item_hat_brave_stubborn_custom",
            type = ItemType.HEAD,
            vitalityGain = null,
            isActive = true
        ),

        // Necklaces
        ItemCatalogEntity(
            itemCatalogId = 0L,
            name = "Colar da Turmalina Radiante",
            description = "Dizem que este colar guarda o fragmento de um antigo feitiço oceânico. Quando usado, seu azul irradia um brilho hipnótico, capaz de dispersar sombras e confundir até o olhar da própria noite — ou talvez, seja só o encanto de se sentir especial.",
            price = 150,
            imageResourceName = "ic_item_neck_radiant_tourmaline_custom",
            type = ItemType.NECKLACE,
            vitalityGain = null,
            isActive = true
        ),
        ItemCatalogEntity(
            itemCatalogId = 0L,
            name = "Cachecol da Brisa Suave",
            description = "Tecido com fios de crepúsculo, este cachecol é tão longo que vira capa nas costas do Lello. Quem o usa sente a coragem de um herói e vontade de abraçar o mundo — ou talvez, só goste de se enrolar em algo macio.",
            price = 130,
            imageResourceName = "ic_item_neck_gentle_breeze_custom",
            type = ItemType.NECKLACE,
            vitalityGain = null,
            isActive = true
        ),
    )
}