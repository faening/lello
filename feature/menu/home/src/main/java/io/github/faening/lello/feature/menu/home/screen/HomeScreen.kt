package io.github.faening.lello.feature.menu.home.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.faening.lello.core.designsystem.icon.LelloIcons
import io.github.faening.lello.feature.menu.home.R

@Composable
fun HomeScreen(
    onFeatureClick: (String) -> Unit,
    onDetailClick: (String) -> Unit,
    onNavigateToModule: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // Título da tela
        item {
            Text(
                text = "Welcome to Lello",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        // Seção de módulos (que também podem ser acessados pelo menu)
        item {
            Text(
                text = "Modules",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // Aqui seriam os módulos principais que também estão no menu
                val modules = listOf(
                    ModuleItem(
                        "diaries",
                        R.string.home_title,
                        LelloIcons.Diary
                    ),
                    ModuleItem(
                        "achievements",
                        R.string.home_title,
                        LelloIcons.Achievements
                    ),
                    ModuleItem(
                        "dashboard",
                        R.string.home_title,
                        LelloIcons.Dashboard
                    )
                )

                items(modules) { module ->
                    ModuleCard(
                        title = stringResource(id = module.titleRes),
                        icon = module.icon,
                        onClick = { onNavigateToModule(module.route) }
                    )
                }
            }
        }

        // Seção de recursos (features) específicas dentro do Home
        item {
            Text(
                text = stringResource(id = R.string.home_title),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Cards para recursos específicos do Home
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                FeatureCard(
                    title = "Recurso 1",
                    description = "Descrição do recurso 1",
                    onClick = { onFeatureClick("feature1") }
                )

                FeatureCard(
                    title = "Recurso 2",
                    description = "Descrição do recurso 2",
                    onClick = { onFeatureClick("feature2") }
                )
            }
        }

        // Seção de itens recentes ou importantes
        item {
            Text(
                text = stringResource(id = R.string.home_title),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(vertical = 8.dp)
            )

            // Lista de itens recentes
            Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
                for (i in 1..3) {
                    ItemRow(
                        title = "Item $i",
                        subtitle = "Descrição do item $i",
                        onClick = { onDetailClick("item$i") }
                    )
                }
            }
        }
    }
}

// Representação de um módulo acessível
data class ModuleItem(
    val route: String,
    val titleRes: Int,
    val icon: ImageVector
)

// Card para módulos principais
@Composable
fun ModuleCard(
    title: String,
    icon: ImageVector,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .size(100.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                imageVector = icon,
                contentDescription = title,
                modifier = Modifier.size(32.dp)
            )

            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

// Card para recursos específicos
@Composable
fun FeatureCard(
    title: String,
    description: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Text(
                text = description,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

// Linha para itens de lista
@Composable
fun ItemRow(
    title: String,
    subtitle: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp, horizontal = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Icon(
            imageVector = LelloIcons.Diary,
            contentDescription = null
        )
    }
}

// Tela de detalhes (simplificada)
@Composable
fun HomeDetailScreen(itemId: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Detalhes do item: $itemId",
            style = MaterialTheme.typography.headlineSmall
        )

        // Conteúdo da tela de detalhes...
    }
}

// Tela de feature específica (simplificada)
@Composable
fun HomeFeatureScreen(featureId: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text(
            text = "Feature: $featureId",
            style = MaterialTheme.typography.headlineSmall
        )

        // Conteúdo da feature...
    }
}
