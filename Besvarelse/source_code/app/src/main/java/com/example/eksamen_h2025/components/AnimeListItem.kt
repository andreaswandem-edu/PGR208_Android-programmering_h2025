package com.example.eksamen_h2025.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.eksamen_h2025.data.api.Anime

@Composable
fun AnimeListItem(
    anime: Anime,
    showDetails: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .clickable { showDetails() }
    ) {
        AsyncImage(
            model = anime.images?.jpg?.image_url ?: "",
            contentDescription = anime.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .aspectRatio(0.66f)
                .clip(RoundedCornerShape(10.dp))
        )

        Box(
            modifier = Modifier
                .padding(8.dp)
                .background(
                    Color.Black.copy(alpha = 0.8f),
                    shape = RoundedCornerShape(10.dp)
                )
        ) {
            Row(
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    modifier = Modifier.size(16.dp),
                    imageVector = Icons.Default.Star,
                    contentDescription = "Favoritt ikon",
                    tint = Color.Yellow,
                )

                Text(
                    modifier = Modifier.padding(start = 4.dp),
                    text = "${anime.score}",
                    color = Color.White,
                )
            }
        }
    }
}