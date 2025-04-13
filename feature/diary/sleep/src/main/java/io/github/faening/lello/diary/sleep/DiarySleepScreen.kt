package io.github.faening.lello.diary.sleep

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryMoodScreen(navController: NavController) {
    Scaffold(
    topBar = {
        TopAppBar(
            title = { Box(modifier = Modifier.fillMaxWidth()) {
                Text(
                    text = "Lello",
                    modifier = Modifier.align(Alignment.Center)
                )
            }},
        )
    }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Bem-vindo aos seus diários de saúde",
                style = MaterialTheme.typography.headlineSmall
            )

            Spacer(modifier = Modifier.height(32.dp))

            DiaryCard(
                title = "Diário de Humor",
                description = "Acompanhe seu bem-estar emocional",
                onClick = { navController.popBackStack() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            DiaryCard(
                title = "Diário de Medicamentos",
                description = "Gerencie suas medicações",
                onClick = { navController.popBackStack() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            DiaryCard(
                title = "Diário de Sono",
                description = "Monitore seus padrões de sono",
                onClick = { navController.popBackStack() }
            )

            Spacer(modifier = Modifier.height(16.dp))

            DiaryCard(
                title = "Diário de Alimentação",
                description = "Registre sua alimentação diária",
                onClick = { navController.popBackStack() }
            )
        }
    }
}

@Composable
fun DiaryCard(
    title: String,
    description: String,
    onClick: () -> Unit
) {
    ElevatedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.titleMedium
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_media_next),
                contentDescription = "Ir para $title"
            )
        }
    }
}