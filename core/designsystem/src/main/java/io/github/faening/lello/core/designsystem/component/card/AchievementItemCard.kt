package io.github.faening.lello.core.designsystem.component.card

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.core.designsystem.theme.Dimension
import io.github.faening.lello.core.designsystem.theme.LelloShape
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.store.ItemCatalog
import io.github.faening.lello.core.testing.data.ItemCatalogTestData

@Composable
fun LelloAchievementItemCard(
    item: ItemCatalog,
    onClick: () -> Unit,
    @SuppressLint("ModifierParameter") modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    val imageResId = remember(item.imageResourceName) {
        context.resources.getIdentifier(item.imageResourceName, "drawable", context.packageName)
    }

    Box(
        modifier
            .size(148.dp)
            .padding(end = Dimension.spacingSmall, bottom = Dimension.spacingSmall)
    ) {
        // Fake Shadow
        Box(
            Modifier
                .matchParentSize()
                .offset(Dimension.spacingSmall, Dimension.spacingSmall)
                .background(
                    color = AchievementItemCardDefaults.shadowColor(),
                    shape = LelloShape.cardShape
                )
        )

        Card(
            modifier = Modifier
                .matchParentSize()
                .clickable { onClick() },
            shape = LelloShape.cardShape,
            colors = AchievementItemCardDefaults.containerColor(),
            elevation = AchievementItemCardDefaults.elevation(),
            border = BorderStroke(
                width = Dimension.borderWidthThick,
                color = AchievementItemCardDefaults.borderColor()
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(Dimension.spacingRegular),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                // Imagem do item
                if (imageResId != 0) {
                    Image(
                        painter = painterResource(id = imageResId),
                        contentDescription = item.name,
                        modifier = Modifier.size(80.dp)
                    )
                } else {
                    // Placeholder simples caso o recurso não seja encontrado
                    Icon(
                        imageVector = LelloIcons.Graphic.Coin.imageVector,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.secondary,
                        modifier = Modifier.size(80.dp)
                    )
                }

                // Valor do item (ícone de moeda + preço)
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = LelloIcons.Graphic.Coin.imageVector,
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                    Spacer(Modifier.width(Dimension.spacingSmall))
                    Text(
                        text = item.price.toString(),
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
        }
    }
}

private object AchievementItemCardDefaults {
    @Composable
    fun borderColor(): Color {
        return MaterialTheme.colorScheme.outline
    }

    @Composable
    fun shadowColor(): Color {
        return MaterialTheme.colorScheme.scrim.copy(alpha = Dimension.alphaStateNormal)
    }

    @Composable
    fun elevation(): CardElevation {
        return CardDefaults.cardElevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp, 0.dp)
    }

    @Composable
    fun containerColor(): CardColors {
        return CardDefaults.cardColors(MaterialTheme.colorScheme.surfaceContainerLowest)
    }

    @Composable
    fun primaryTextColor(): Color {
        return MaterialTheme.colorScheme.onPrimary
    }

    @Composable
    fun secondaryTextColor(): Color {
        return MaterialTheme.colorScheme.secondaryContainer
    }
}

// region: Previews

@Composable
@Preview(
    name = "Default",
    group = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showBackground = true
)
private fun LelloAchievementItemCardPreview_LightMode() {
    val items = ItemCatalogTestData().list

    LelloTheme {
        LelloAchievementItemCard(
            item = items.first(),
            onClick = { }
        )
    }
}

// endregion: Previews
