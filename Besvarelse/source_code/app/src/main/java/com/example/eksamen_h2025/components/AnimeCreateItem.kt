package com.example.eksamen_h2025.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.eksamen_h2025.data.room.AnimeEntity

@Composable
fun AnimeCreateItem(
    animeEntity: AnimeEntity,
    onFavoriteToggle: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(Color(0xFFECE6F0))
            .padding(8.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                animeEntity.title,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1D1B20)
            )

            IconButton(
                onClick = {
                    onFavoriteToggle()
                },
            ) {
                if (animeEntity.favorite) {
                    Icon(
                        imageVector = Icons.Filled.Favorite,
                        contentDescription = "Heart filled",
                        tint = Color.Red
                    )
                } else {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Heart empty",
                        tint = Color.Red
                    )
                }
            }
        }

        Text(
            "Genre: ${animeEntity.genre}",
            color = Color(0xFF1D1B20)
        )

        Text(
            "Description: ${animeEntity.description}",
            color = Color(0xFF1D1B20)
        )
    }
}