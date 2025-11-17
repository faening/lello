package io.github.faening.lello.feature.achievement.screen

import android.content.Context
import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import io.github.faening.lello.core.designsystem.component.card.LelloAchievementItemCard
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.store.ItemCatalog
import io.github.faening.lello.core.model.store.ItemType
import io.github.faening.lello.core.testing.data.ItemCatalogTestData
import io.github.faening.lello.feature.achievement.AchievementViewModel

@Composable
fun AchievementStoreScreen(
    viewModel: AchievementViewModel,
    onBack: () -> Unit = {}
) {
    val context = LocalContext.current
    val uiState by viewModel.uiState.collectAsState()

    AchievementStoreScreenContent(
        items = uiState.storeItems,
        onItemClick = { viewModel.onShowStoreItemSheet(it) },
        onBack = onBack
    )

    ItemDetailsBottomSheet(
        context = context,
        item = uiState.selectedStoreItem,
        onDismiss = { viewModel.onDismissStoreItemSheet() },
        onPurchaseClick = { item ->
            viewModel.onPurchaseItem(item)
            viewModel.onDismissStoreItemSheet()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AchievementStoreScreenContent(
    items: List<ItemCatalog>,
    onItemClick: (ItemCatalog) -> Unit,
    onBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            AchievementStoreTopAppBar(onBack = onBack)
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(paddingValues)
                .padding(vertical = Dimension.spacingRegular)
                .fillMaxSize()
        ) {
            ItemType.entries.forEach { itemType ->
                val itemsOfType = items.filter { it.type == itemType }
                if (itemsOfType.isNotEmpty()) {
                    ItemCatalogSection(
                        title = itemType.displayName,
                        items = itemsOfType,
                        onClick = onItemClick
                    )
                }
            }
        }
    }
}

@Composable
private fun AchievementStoreTopAppBar(
    onBack: () -> Unit
) {
    LelloTopAppBar(
        title = TopAppBarTitle(text = "Loja"),
        navigateUp = TopAppBarAction(onClick = onBack),
    )
}

@Composable
private fun ItemCatalogSection(
    title: String,
    items: List<ItemCatalog>,
    onClick: (ItemCatalog) -> Unit = {}
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
            items(items) { item ->
                LelloAchievementItemCard(
                    item = item,
                    onClick = { onClick(item) },
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ItemDetailsBottomSheet(
    context: Context,
    item: ItemCatalog?,
    onDismiss: () -> Unit,
    onPurchaseClick: (ItemCatalog) -> Unit,
) {
    item?.let {
        ModalBottomSheet(onDismissRequest = onDismiss) {
            Column(
                modifier = Modifier.padding(horizontal = Dimension.spacingRegular),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val imageResId = remember(item.imageResourceName) {
                    context.resources.getIdentifier(item.imageResourceName, "drawable", context.packageName)
                }

                // Imagem
                Image(
                    painter = painterResource(id = imageResId),
                    contentDescription = item.name,
                    modifier = Modifier.size(80.dp)
                )
                Spacer(Modifier.height(Dimension.spacingMedium))

                // Nome e descrição
                Text(
                    text = it.name,
                    style = MaterialTheme.typography.headlineSmall,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(Dimension.spacingLarge))
                Text(
                    text = it.description,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(Dimension.spacingLarge))

                // Aviso para itens consumíveis
                if (item.type == ItemType.CONSUMABLE) {
                    Text(
                        text = "*Itens consumíveis são são aplicados imediatamente após a compra.",
                        style = MaterialTheme.typography.bodySmall.copy(
                            color = MaterialTheme.colorScheme.onPrimaryContainer
                        ),
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(Modifier.height(Dimension.spacingExtraLarge))
                }

                // Botão de ação
                LelloFilledButton(
                    label = "Comprar por ${item.price} moedas",
                    onClick = { onPurchaseClick(it) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(Dimension.spacingExtraLarge))
            }
        }
    }
}

// region: Previews

@Preview(
    name = "Light Mode",
    showBackground = true,
    backgroundColor = 0xFFFFFBF0,
    uiMode = Configuration.UI_MODE_NIGHT_NO
)
@Composable
fun AchievementStoreScreenPreview_LightMode() {
    val items = ItemCatalogTestData().list

    LelloTheme {
        AchievementStoreScreenContent(
            items = items,
            onItemClick = {},
            onBack = {}
        )
    }
}

// endregion: Previews