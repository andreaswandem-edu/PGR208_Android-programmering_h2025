package com.example.eksamen_h2025.screens.animecreate

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.eksamen_h2025.components.AnimeCreateItem
import com.example.eksamen_h2025.data.room.AnimeEntity
import com.example.eksamen_h2025.navigation.NavRoutes

@Composable
fun AnimeCreateScreen(
    animeCreateViewModel: AnimeCreateViewModel,
    navController: NavController
) {
    val animeEntities by animeCreateViewModel.animeEntities.collectAsState()

    val sortedAnimes = animeEntities.sortedByDescending { it.favorite }

    var title by remember { mutableStateOf("") }
    var genre by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    var errorMessage by remember { mutableStateOf("") }
    var saveMessage by remember { mutableStateOf("") }

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
    ) {
        // Tittel
        Text(
            "Make your own Anime",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(0xFF1D1B20)
        )

        // Undertittel
        Text(
            "Create and view your own animes",
            color = Color(0xFF49454F)
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Column med verticalScroll
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            // Tittel brukerinput
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = title,
                onValueChange = {
                    if ( it.length <= 35 ) {
                        title = it
                    }
                },
                label = {
                    Text("Title")
                }
            )

            // Teller
            Text(
                "${title.length}/35",
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Sjanger brukerinput
            TextField(
                modifier = Modifier.fillMaxWidth(),
                value = genre,
                onValueChange = {
                    if ( it.length <= 25 ) {
                        genre = it
                    }
                },
                label = {
                    Text("Genre")
                }
            )

            // Teller
            Text(
                "${genre.length}/25",
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Beskrivelse brukerinput
            TextField(
                modifier = Modifier.fillMaxWidth(),
                minLines = 2,
                value = description,
                onValueChange = {
                    if ( it.length <= 2000 ) {
                        description = it
                    }
                },
                label = {
                    Text("Description")
                }
            )

            // Teller
            Text(
                "${description.length}/2000",
                fontSize = 12.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            // Error-tekst
            if ( errorMessage.isNotEmpty() ) {
                Text(
                    errorMessage,
                    color = Color.Red
                )
            }

            // Lagre-tekst
            if ( saveMessage.isNotEmpty() ) {
                Text(
                    saveMessage,
                    color = Color.Green
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Lagre-knapp
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF6750A4),
                    contentColor = Color(0xFFFFFFFF)
                ),
                onClick = {
                    if ( title.isBlank() || genre.isBlank() || description.isBlank() ) {
                        errorMessage = "Error: You have to fill in all input areas above"
                        saveMessage = ""
                    } else {
                        animeCreateViewModel.insertAnime(
                            AnimeEntity(
                                title = title,
                                genre = genre,
                                description = description,
                            )
                        )

                        errorMessage = ""
                        saveMessage = "Success: Your anime has been created"

                        title = ""
                        genre = ""
                        description = ""
                    }
                }
            ) {
                Text("Save anime")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Tittel
        Text(
            "Your animes",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(0xFF1D1B20)
        )

        // Hint-tekst
        Text(
            "Tap an anime to edit.",
            color = Color(0xFF49454F)
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Items
        LazyColumn {
            items(sortedAnimes) { anime ->
                Column(
                    modifier = Modifier.clickable {
                        navController.navigate(NavRoutes.AnimeEditRoute(anime.id))
                    }
                ) {
                    AnimeCreateItem(
                        anime,
                        onFavoriteToggle = {
                            animeCreateViewModel.setFavoriteStatus(
                                anime,
                                !anime.favorite
                            )
                        }
                    )
                }
            }
        }
    }
}