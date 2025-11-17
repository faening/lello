package io.github.faening.lello.feature.achievement.screen

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.button.LelloFilledButton
import io.github.faening.lello.core.designsystem.component.card.LelloAchievementItemInventoryCard
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.store.ItemCatalog
import io.github.faening.lello.core.model.store.ItemInventory
import io.github.faening.lello.core.model.store.ItemType
import io.github.faening.lello.core.testing.data.ItemCatalogTestData
import io.github.faening.lello.feature.achievement.AchievementViewModel

@Composable
fun AchievementInventoryScreen(
    viewModel: AchievementViewModel,
    onBack: () -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    val context = LocalContext.current

    AchievementInventoryScreenContent(
        inventoryItems = uiState.inventoryItems,
        storeItems = uiState.allCatalogItems,
        onItemClick = { inventoryItem -> viewModel.onShowInventoryItemSheet(inventoryItem) },
        onBack = onBack
    )

    val selectedInventoryItem = uiState.selectedInventoryItem
    val selectedCatalogItem = selectedInventoryItem?.let { invItem ->
        uiState.allCatalogItems.find { it.id == invItem.itemCatalogId }
    }

    if (selectedInventoryItem != null && selectedCatalogItem != null) {
        InventoryItemDetailsBottomSheet(
            context = context,
            inventoryItem = selectedInventoryItem,
            catalogItem = selectedCatalogItem,
            onDismiss = { viewModel.onDismissInventoryItemSheet() },
            onEquipClick = { viewModel.onEquipItem() } // Chama a nova função
        )
    }
}

@Composable
private fun AchievementInventoryScreenContent(
    inventoryItems: List<ItemInventory>,
    storeItems: List<ItemCatalog>,
    onItemClick: (ItemInventory) -> Unit, // CORREÇÃO: Recebe a lambda
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            AchievementInventoryTopAppBar(onBack = onBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(vertical = Dimension.spacingRegular)
                .fillMaxWidth()
        ) {
            if (inventoryItems.isEmpty()) {
                Text(
                    text = "Você não possui itens no inventário.",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(Dimension.spacingRegular)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            } else {
                ItemType.entries.forEach { itemType ->
                    val itemsOfType = inventoryItems.filter { inventoryItem ->
                        storeItems.find { it.id == inventoryItem.itemCatalogId }?.type == itemType
                    }
                    if (itemsOfType.isNotEmpty()) {
                        InventorySection(
                            title = itemType.displayName,
                            items = itemsOfType,
                            storeItems = storeItems,
                            onClick = onItemClick // CORREÇÃO: Passa a lambda
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun InventorySection(
    title: String,
    items: List<ItemInventory>,
    storeItems: List<ItemCatalog>,
    onClick: (ItemInventory) -> Unit // CORREÇÃO: Recebe a lambda
) {
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(
                start = Dimension.spacingRegular,
                bottom = Dimension.spacingRegular
            )
        )
        LazyRow(
            contentPadding = PaddingValues(
                start = Dimension.spacingRegular,
                end = Dimension.spacingRegular,
                bottom = Dimension.spacingLarge
            ),
            horizontalArrangement = Arrangement.spacedBy(Dimension.spacingRegular)
        ) {
            items(items) { inventoryItem ->
                val catalogItem = storeItems.find { it.id == inventoryItem.itemCatalogId }
                catalogItem?.let {
                    LelloAchievementItemInventoryCard(
                        item = it,
                        inventory = inventoryItem,
                        onClick = { onClick(inventoryItem) }
                    )
                }
            }
        }
    }
}

@Composable
private fun AchievementInventoryTopAppBar(
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Inventário"),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun InventoryItemDetailsBottomSheet(
    context: Context,
    inventoryItem: ItemInventory,
    catalogItem: ItemCatalog,
    onDismiss: () -> Unit,
    onEquipClick: () -> Unit,
) {
    ModalBottomSheet(onDismissRequest = onDismiss) {
        Column(
            modifier = Modifier
                .padding(horizontal = Dimension.spacingRegular)
                .fillMaxWidth(), // Garante que o botão preencha
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val imageResId = remember(catalogItem.imageResourceName) {
                context.resources.getIdentifier(
                    catalogItem.imageResourceName,
                    "drawable",
                    context.packageName
                )
            }

            // Imagem
            if (imageResId != 0) {
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = catalogItem.name,
                    modifier = Modifier.size(80.dp)
                )
            }
            Spacer(Modifier.height(Dimension.spacingMedium))

            // Nome e descrição
            Text(
                text = catalogItem.name,
                style = MaterialTheme.typography.headlineSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(Dimension.spacingLarge))
            Text(
                text = catalogItem.description,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(Dimension.spacingLarge))

            // Botão de ação
            LelloFilledButton(
                // Muda o texto baseado se já está equipado
                label = if (inventoryItem.isEquipped) "Equipado" else "Equipar",
                onClick = onEquipClick,
                enabled = !inventoryItem.isEquipped, // Desativa o botão se já estiver equipado
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(Modifier.height(Dimension.spacingExtraLarge))
        }
    }
}

// region: Previews

@Composable
@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
fun AchievementInventoryScreenPreview_LightMode() {
    val catalogItems = ItemCatalogTestData().list
    val inventoryItems = catalogItems.map {
        ItemInventory(
            id = 1L,
            itemCatalogId = it.id,
            acquisitionDate = System.currentTimeMillis(),
            isEquipped = false
        )
    }

    LelloTheme {
        AchievementInventoryScreenContent(
            inventoryItems = inventoryItems,
            storeItems = catalogItems,
            onItemClick = {},
            onBack = {}
        )
    }
}

// endregion: Previews