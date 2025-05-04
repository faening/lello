package io.github.faening.lello.feature.menu.diary.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import io.github.faening.lello.core.designsystem.component.LelloTopAppBar
import io.github.faening.lello.core.designsystem.component.TopAppBarAction
import io.github.faening.lello.core.designsystem.component.TopAppBarTitle
import io.github.faening.lello.core.designsystem.theme.LelloTheme
import io.github.faening.lello.core.model.journal.JournalCategory
import io.github.faening.lello.feature.menu.diary.DiaryViewModel
import io.github.faening.lello.feature.menu.diary.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryScreen(
    onSettingsClick: () -> Unit,
    onEditDiaryClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: DiaryViewModel = hiltViewModel()
) {

    // Coletar os estados
    val diaries by viewModel.diaries.collectAsStateWithLifecycle()
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    
    // SnackbarHostState
    val snackbarHostState = remember { SnackbarHostState() }
    
    // Mostrar erro em um Snackbar se houver
    LaunchedEffect(uiState.error) {
        uiState.error?.let {
            snackbarHostState.showSnackbar(it)
            viewModel.dismissError()
        }
    }
    
    Scaffold(
        topBar = {
            DiaryScreenTopAppBar(
                onSettingsClick = onSettingsClick
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState) { data ->
                Snackbar(
                    modifier = Modifier.padding(16.dp),
                    action = {
                        /* Ação opcional */
                    }
                ) {
                    Text(data.visuals.message)
                }
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(

                )
            }
            
            if (diaries.isNotEmpty()) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(diaries) { diary ->
                        DiaryItem(
                            journal = diary,
                            onToggleActive = { active ->
                                viewModel.toggleDiaryStatus(
                                    diary.id ?: 0,
                                    active
                                )
                            }
                        )
                    }
                }
            }
            
            if (diaries.isEmpty() && !uiState.isLoading) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Nenhum diário encontrado",
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "Adicione novos diários para começar",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DiaryScreenTopAppBar(
    onSettingsClick: () -> Unit
) {
    val title = R.string.toolbar_title
    val actionSettingsDescription = "Settings"

    LelloTopAppBar(
        title = TopAppBarTitle(textRes = title),
        navigateUp = TopAppBarAction(
            icon = Icons.Default.Settings,
            contentDescription = actionSettingsDescription,
            onClick = onSettingsClick
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(
    name = "Diary Home Screen",
    showBackground = true
)
@Composable
fun DiaryHomeScreenPreview() {
    LelloTheme(darkTheme = false) {
        DiaryScreen(
            onSettingsClick = {},
            onEditDiaryClick = {}
        )
    }
}

@Composable
fun DiaryItem(
    journal: JournalCategory,
    onToggleActive: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Imagem do diário (se houver)
//            if (journal.imageUrl.isNotEmpty()) {
//                AsyncImage(
//                    model = journal.imageUrl,
//                    contentDescription = journal.name,
//                    modifier = Modifier.size(48.dp)
//                )
//                Spacer(modifier = Modifier.width(16.dp))
//            }

            // Informações do diário
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = journal.name,
                        style = MaterialTheme.typography.titleMedium
                    )

                    if (journal.blocked) {
                        Spacer(modifier = Modifier.width(4.dp))
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Bloqueado",
                            modifier = Modifier.size(16.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                Text(
                    text = journal.shortDescription,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            // Switch para ativar/desativar
            Switch(
                checked = journal.active,
                onCheckedChange = { if (!journal.blocked) onToggleActive(it) },
                enabled = !journal.blocked
            )
        }
    }
}

@Composable
fun AsyncImage(model: String, contentDescription: String, modifier: Modifier) {
    TODO("Not yet implemented")
}