package io.github.faening.lello.core.testing.data

import io.github.faening.lello.core.model.store.ItemCatalog
import io.github.faening.lello.core.model.store.ItemType
import io.github.faening.lello.core.testing.repository.TestDataResources

class ItemCatalogTestData : TestDataResources<ItemCatalog> {
    override val list = listOf(
        ItemCatalog(
            id = 1L,
            name = "Amendoim",
            description = "Fonte natural de proteínas e gorduras boas. Ao consumir este item, a energia do Lello será restaurada em 20.",
            price = 40,
            imageResourceName = "ic_item_consumable_peanut_custom",
            backgroundImageResourceName = "ic_item_consumable_peanut_custom",
            type = ItemType.CONSUMABLE,
            vitalityGain = 20,
            isActive = true
        ),
        ItemCatalog(
            id = 2L,
            name = "Amêndoa",
            description = "Rica em vitamina A, ótima para a saúde e visão. Ao consumir este item, a energia do Lello será restaurada em 10.",
            price = 30,
            imageResourceName = "ic_item_consumable_almond_custom",
            backgroundImageResourceName = "ic_item_consumable_almond_custom",
            type = ItemType.CONSUMABLE,
            vitalityGain = 10,
            isActive = true
        ),
        ItemCatalog(
            id = 3L,
            name = "Girassol",
            description = "Sementes crocantes, ricas em vitaminas e minerais. Ao consumir este item, a energia do Lello será restaurada em 15.",
            price = 35,
            imageResourceName = "ic_item_consumable_sunflower_custom",
            backgroundImageResourceName = "ic_item_consumable_sunflower_custom",
            type = ItemType.CONSUMABLE,
            vitalityGain = 15,
            isActive = true
        ),

        // Hats
        ItemCatalog(
            id = 4L,
            name = "Chapéu da Lua Cantante",
            description = "Dizem que este chapéu foi trançado com acordes de uma canção esquecida. Quem o usa sente os pés leves e o coração mais alegre ou talvez, seja só vontade de sair dançando por aí.",
            price = 250,
            imageResourceName = "ic_item_hat_singing_moon_custom",
            backgroundImageResourceName = "img_item_hat_singing_moon_foreground",
            type = ItemType.HEAD,
            vitalityGain = null,
            isActive = true
        ),
        ItemCatalog(
            id = 5L,
            name = "Chapéu do Capiter Pan",
            description = "Reza a lenda que este chapéu nasceu do primeiro sopro de vento livre. Quem o veste sente o coração mais leve e a coragem renovada, ou talvez, seja só a pena balançando ao lado.",
            price = 200,
            imageResourceName = "ic_item_hat_capiter_pan_custom",
            backgroundImageResourceName = "img_item_hat_capiter_pan_foreground",
            type = ItemType.HEAD,
            vitalityGain = null,
            isActive = true
        ),
        ItemCatalog(
            id = 6L,
            name = "Chapéu da Noite Estrelada",
            description = "Dizem que o chapéu foi tecido de fragmentos de uma noite estrelada. Quem o usa sente a mente mais clara e os pensamentos mais brilhantes, ou talvez, seja só a fita apertando a cabeça.",
            price = 250,
            imageResourceName = "ic_item_hat_starry_night_custom",
            backgroundImageResourceName = "img_item_hat_starry_night_foreground",
            type = ItemType.HEAD,
            vitalityGain = null,
            isActive = true
        ),

        // Necklaces
        ItemCatalog(
            id = 7L,
            name = "Colar da Turmalina Radiante",
            description = "Dizem que este colar guarda o fragmento de um antigo feitiço oceânico. Quando usado, seu azul irradia um brilho hipnótico, capaz de dispersar sombras e confundir até o olhar da própria noite — ou talvez, seja só o encanto de se sentir especial.",
            price = 150,
            imageResourceName = "ic_item_neck_radiant_tourmaline_custom",
            backgroundImageResourceName = "img_item_neck_radiant_tourmaline_foreground",
            type = ItemType.NECKLACE,
            vitalityGain = null,
            isActive = true
        ),
        ItemCatalog(
            id = 8L,
            name = "Cachecol da Brisa Suave",
            description = "Tecido com fios de crepúsculo, este cachecol é tão longo que vira capa nas costas do Lello. Quem o usa sente a coragem de um herói e vontade de abraçar o mundo — ou talvez, só goste de se enrolar em algo macio.",
            price = 130,
            imageResourceName = "ic_item_neck_gentle_breeze_custom",
            backgroundImageResourceName = "img_item_neck_gentle_breeze_foreground",
            type = ItemType.NECKLACE,
            vitalityGain = null,
            isActive = true
        ),
    )
}