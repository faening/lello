package io.github.faening.lello.core.domain.usecase.item.purchase

import io.github.faening.lello.core.domain.usecase.item.catalog.GetItemCatalogByIdUseCase
import io.github.faening.lello.core.domain.usecase.item.inventory.GetAllItemInventoryUseCase
import io.github.faening.lello.core.domain.usecase.item.inventory.UpdateItemInventoryUseCase
import io.github.faening.lello.core.model.store.ItemInventory
import javax.inject.Inject

class EquipItemUseCase @Inject constructor(
    // Injetamos os use cases que já existem
    private val getItemCatalogByIdUseCase: GetItemCatalogByIdUseCase,
    private val getAllItemInventoryUseCase: GetAllItemInventoryUseCase,
    private val updateItemInventoryUseCase: UpdateItemInventoryUseCase
) {
    suspend operator fun invoke(itemToEquip: ItemInventory) {
        // 1. Descobrir a categoria (tipo) do item que queremos equipar
        val catalogItem = getItemCatalogByIdUseCase(itemToEquip.itemCatalogId)
            ?: throw Exception("Item do catálogo não encontrado")

        val categoryToEquip = catalogItem.type

        // 2. Buscar TODOS os itens do inventário
        val allInventoryItems = getAllItemInventoryUseCase.invoke()

        // 3. Desequipar todos os outros itens da MESMA categoria
        allInventoryItems
            .filter {
                // Encontra itens que SÃO da mesma categoria E estão equipados
                val otherCatalogItem = getItemCatalogByIdUseCase(it.itemCatalogId)
                otherCatalogItem?.type == categoryToEquip && it.isEquipped
            }
            .forEach { itemToUnequip ->
                // Desequipa
                updateItemInventoryUseCase(itemToUnequip.copy(isEquipped = false))
            }

        // 4. Equipar o item que o usuário selecionou
        updateItemInventoryUseCase(itemToEquip.copy(isEquipped = true))
    }
}