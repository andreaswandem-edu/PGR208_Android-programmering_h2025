package com.example.eksamen_h2025.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.eksamen_h2025.R
import com.example.eksamen_h2025.data.api.Anime

@Composable
fun AnimeItem(
    anime: Anime,
) {
    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(scrollState)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFECE6F0))
            .padding(16.dp)
    ) {
        // Tittel
        Text(
            anime.title ?: "Unknown title",
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1D1B20)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Bilde
        AsyncImage(
            model = anime.images?.jpg?.image_url,
            contentDescription = "Anime bilde",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(10.dp)),
            placeholder = painterResource(R.drawable.image_placeholder)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Beskrivelse
        Text(
            anime.synopsis ?: "Sorry. No description is available for this anime",
            color = Color(0xFF1D1B20)
        )
    }
}
