package io.github.faening.lello.feature.home.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    onDiaryMoodClick: () -> Unit, // Callback para navegação para DiaryMood
) {
    Column(modifier = modifier.padding(16.dp)) {
        Text("Tela Inicial")

        // Botão para navegar para o DiaryMood
        Button(
            onClick = onDiaryMoodClick,
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("Ir para Diário de Humor")
        }

        // Outros botões para outros módulos podem ser adicionados aqui
    }
}

@Composable
private fun HomeNavHost(
    navController: NavHostController,
    mainNavController: NavController,
    onBackClick: () -> Unit
) {
    NavHost(
        navController = navController,
        startDestination = "diary_mood_home"
    ) {
//        composable("diary_mood_home") {
//            DiaryMoodHomeScreen(
//                navController = navController,
//                onBackClick = onBackClick
//            )
//        }

//        composable("diary_mood_create") {
//            DiaryMoodCreateScreen(
//                navController = navController
//            )
//        }
//
//        composable("diary_mood_details/{entryId}") { backStackEntry ->
//            val entryId = backStackEntry.arguments?.getString("entryId") ?: ""
//            DiaryMoodDetailsScreen(
//                entryId = entryId,
//                navController = navController
//            )
//        }
    }
}

@Composable
fun HomeContent(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Meus Diários",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        DiaryCardsGrid(navController = navController)
    }
}

@Composable
fun DiaryCardsGrid(navController: NavController) {
    val diaries= listOf(
        DiaryType("Humor", "diary_mood", "Registre como você está se sentindo"),
        DiaryType("Medicamentos", "diary_medication", "Acompanhe seus medicamentos"),
        DiaryType("Sono", "diary_sleep", "Monitore sua qualidade de sono"),
        DiaryType("Alimentação", "diary_food", "Registre sua alimentação")
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(diaries.size) { index ->
            val diary = diaries[index]
            DiaryCard(
                title = diary.name,
                description = diary.description,
                onClick = { navController.navigate(diary.route) }
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
            .height(160.dp)
            .clickable(onClick = onClick)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                textAlign = TextAlign.Center
            )
        }
    }
}

data class DiaryType(val name: String, val route: String, val description: String)