package com.example.eksamen_h2025.screens.animedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eksamen_h2025.R
import com.example.eksamen_h2025.components.AnimeItem

@Composable
fun AnimeDetailsScreen(
    animeDetailsViewModel: AnimeDetailsViewModel,
    navController: NavController,
    animeId: Int
) {
    val anime = animeDetailsViewModel.anime.collectAsState()

    LaunchedEffect(Unit) {
        animeDetailsViewModel.setAnime(animeId)
    }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Tilbakeknapp
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.arrow_circle_left),
                    contentDescription = "Tilbake knapp",
                    tint = Color(0xFF1D1B20),
                    modifier = Modifier.size(40.dp)
                )
            }

            // Tittel
            Text(
                "Anime information",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 40.dp),
                textAlign = TextAlign.Center,
                color = Color(0xFF1D1B20)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Item
        anime.value?.let {
            AnimeItem(
                anime = it
            )
        }

    }
}

