package com.example.eksamen_h2025.screens.animesearch

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import com.example.eksamen_h2025.components.AnimeItem
import com.example.eksamen_h2025.components.AnimeListItem
import com.example.eksamen_h2025.navigation.NavRoutes

@Composable
fun AnimeSearchScreen(
    animeSearchViewModel: AnimeSearchViewModel,
    navController: NavController
) {
    val idResult = animeSearchViewModel.idResult.collectAsState()
    val nameResult = animeSearchViewModel.nameResult.collectAsState()

    var input by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }
    var searchMode by remember { mutableStateOf("id") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        // Tittel
        Text(
            "Look up an Anime",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            color = Color(0xFF1D1B20)
        )

        // Undertittel
        Text(
            "Tap an anime to view more details",
            color = Color(0xFF49454F)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            // Filter-knapp
            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF625B71),
                    contentColor = Color(0xFFFFFFFF)
                ),
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp),
                shape = RoundedCornerShape(
                    topEnd = 0.dp,
                    topStart = 10.dp,
                    bottomEnd = 0.dp,
                    bottomStart = 10.dp
                ),
                onClick = {
                    expanded = true
                }
            ) {
                Text("Filter")
            }

            // Dropdown (se rapport)
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                if ( searchMode == "id" ) {
                    DropdownMenuItem(
                        text = { Text("name") },
                        onClick = {
                            searchMode = "name"
                            errorMessage = ""
                            expanded = false
                        }
                    )
                } else {
                    DropdownMenuItem(
                        text = { Text("id") },
                        onClick = {
                            searchMode = "id"
                            expanded = false
                        }
                    )
                }
            }

            // Inputfelt
            TextField(
                modifier = Modifier.weight(3f),
                shape = RoundedCornerShape(
                    topEnd = 10.dp,
                    topStart = 0.dp,
                    bottomEnd = 10.dp,
                    bottomStart = 0.dp
                ),
                value = input,
                onValueChange = { input = it },
                label = {
                    if ( searchMode == "id" ) {
                        Text("Type in an id")
                    } else {
                        Text("Type in a name")
                    }
                }
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Feedback-tekst
        if ( errorMessage.isNotEmpty() ) {
            Text(
                errorMessage,
                color = Color.Red
            )
        }

        Spacer(modifier = Modifier.height(4.dp))

        // Søk-knapp
        Button(
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6750A4),
                contentColor = Color(0xFFFFFFFF)
            ),
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                if ( input.isEmpty() ) {
                    errorMessage = "Error: You need to fill out the Textfield above."
                } else if ( searchMode == "id" && !input.isDigitsOnly() ) {
                    errorMessage = "Error: An id can only contain numbers"
                } else {
                    errorMessage = ""

                    if ( searchMode == "id" ) {
                        val id = input.toIntOrNull()
                        if ( id != null ) {
                            animeSearchViewModel.searchById(id)
                        }
                    } else {
                        animeSearchViewModel.searchByName(input)
                    }
                }
                input = ""
            }
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Visning for AnimeItem (id)
        idResult.value?.let {
            AnimeItem(
                anime = it
            )
        }

        // Visning for AnimeListItem (name)
        if (nameResult.value.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(count = 2),
                horizontalArrangement = Arrangement.spacedBy(24.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                items(nameResult.value) { anime ->
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
        }
    }
}