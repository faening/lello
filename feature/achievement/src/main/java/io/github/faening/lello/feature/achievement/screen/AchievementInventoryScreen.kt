package io.github.faening.lello.feature.achievement.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import io.github.faening.lello.core.designsystem.component.appbar.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.appbar.TopAppBarTitle
import io.github.faening.lello.core.designsystem.component.card.LelloAchievementItemCard
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

    AchievementInventoryScreenContent(
        inventoryItems = uiState.inventoryItems,
        storeItems = uiState.allCatalogItems,
        onBack = onBack
    )
}

@Composable
private fun AchievementInventoryScreenContent(
    inventoryItems: List<ItemInventory>,
    storeItems: List<ItemCatalog>,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            AchievementInventoryTopAppBar(onBack = onBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(vertical = Dimension.spacingRegular)
                .fillMaxSize()
        ) {
            if (inventoryItems.isEmpty()) {
                Text(
                    text = "Você ainda não possui itens",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(Dimension.spacingRegular)
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
                            storeItems = storeItems
                        )
                    }
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

@Composable
private fun InventorySection(
    title: String,
    items: List<ItemInventory>,
    storeItems: List<ItemCatalog>
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
            horizontalArrangement = Arrangement.spacedBy(Dimension.spacingRegular),
        ) {
            items(items) { inventoryItem ->
                val catalogItem = storeItems.find { it.id == inventoryItem.itemCatalogId }
                catalogItem?.let {
                    LelloAchievementItemCard(
                        item = it,
                        onClick = { },
                    )
                }
            }
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
            onBack = {}
        )
    }
}

// endregion: Previews