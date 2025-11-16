package io.github.faening.lello.core.model.store

enum class ItemType(val displayName: String) {
    HEAD("Chapéus"),
    WEAPON("Armas"),
    BOOTS("Botas"),
    NECKLACE("Colares"),
    GLOVES("Luvas"),
    ARMOR("Armaduras"),
    PANTS("Calças"),
    CONSUMABLE("Consumíveis");

    companion object {
        fun fromDisplayName(name: String): ItemType? {
            return entries.find { it.displayName == name }
        }
    }
}