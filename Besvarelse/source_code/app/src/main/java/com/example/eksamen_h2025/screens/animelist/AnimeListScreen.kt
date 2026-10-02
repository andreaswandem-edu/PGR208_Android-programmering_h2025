package com.example.eksamen_h2025.screens.animelist

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eksamen_h2025.components.AnimeListItem
import com.example.eksamen_h2025.navigation.NavRoutes

@Composable
fun AnimeListScreen(
    animeListViewModel: AnimeListViewModel,
    navController: NavController
) {

    val animes by animeListViewModel.animes.collectAsState()
    val currentPage by animeListViewModel.currentPage.collectAsState()

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        // Tittel
        Text(
            "Explore the Library",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(0xFF1D1B20)
        )

        // Undertittel
        Text(
            "Tap an anime to view more details",
            modifier = Modifier.padding(bottom = 24.dp),
            color = Color(0xFF49454F)
        )

        // Grid (se rapport)
        LazyVerticalGrid(
            columns = GridCells.Fixed(count = 2),
            horizontalArrangement = Arrangement.spacedBy(24.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            modifier = Modifier.weight(1f)

        ) {
            items(animes) { anime ->
                AnimeListItem(
                    anime = anime,
                    showDetails = {
                        navController.navigate(
                            NavRoutes.AnimeDetailsRoute(anime.id)
                        )
                    }
                )
            }
        }

        // Sidemeny
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    animeListViewModel.previousPage()
                }
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Gå videre pil",
                    tint = Color(0xFF1D1B20),
                )
            }

            Text(
                "Side $currentPage",
                color = Color(0xFF1D1B20)
            )

            IconButton(
                onClick = {
                    animeListViewModel.nextPage()
                }
            ) {
                Icon(
                    Icons.AutoMirrored.Filled.KeyboardArrowRight,
                    contentDescription = "Gå tilbake pil",
                    tint = Color(0xFF1D1B20),
                )
            }
        }
    }
}